package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import giis.demo.util.SwingUtil;
import modelo.dto.Producto;
import modelo.modelo.ClienteModel;
import persistence.dto.CategoriaDto;
import vista.CarritoView;
import vista.ClienteLoginView;
import vista.ClienteView;

public class ClienteController {
	
	private ClienteView view;
	private ClienteModel model;
	//private ClienteLoginView lview;
	private DefaultListModel<String> modeloProducto;
		
	
	
	public ClienteController(ClienteView view, ClienteModel model) {
		this.model = model;
		this.view = view;
		//this.lview = new ClienteLoginView(view.getDatabase());
		this.modeloProducto = new DefaultListModel<String>();
	}
	
	public void initView() {
		//System.out.print(lview.getTextNombreUsuario().getText()); mejor usar dto
		//cambiar los dto y databases al model en vez de tenerlo en view
		view.getTextCategoria().setText("Inicio");
		view.getLblNombreUsuario().setText(view.getDto().getName());
		//view.getLblNombreUsuario().setText(lview.getTextNombreUsuario().getText());
		
		if (!view.getDto().getName().equals("Invitado")) {
			model.rellenaTablaCarrito(view.getTableCarritoModel(), view.getDto().getName());
		}
		
		getListaProductos(null);
	}
			
	public void initController() {
		//añado controlador al boton de Siguiente
		view.getBtnSiguiente().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!model.getCarrito().isEmpty()) {
					
					//si no es invitado, y es cliente de empresa
					String nombreUsuario = view.getLblNombreUsuario().getText();
					if (!nombreUsuario.equals("Invitado") && model.esClienteDeEmpresa(nombreUsuario)) {
						
						JOptionPane.showMessageDialog(view, "¡Gracias por su compra!" +
			                       " Hemos recibido su pedido y se enviará a la dirección proporcionada por la empresa");
						model.confirmarPedido();
					} else {
						
						view.dispose();
						CarritoView frame = new CarritoView(view.getCarrito(), view.getDatabase(), view.getDto());
						//frame.getLblNombreUsuario().setText(lview.getTextNombreUsuario().getText()); mejor usar el dto, 
						//cambiar los dto y databases al model en vez de tenerlo en view
						frame.getLblNombreUsuario().setText(view.getDto().getName());
						frame.setLocationRelativeTo(view);
						view.dispose();
						frame.setVisible(true);
					}
					
					
				} else {
					JOptionPane.showMessageDialog(view, "No hay ningún producto en el carrito.");
				}
			}
		});
		view.getButtonAnterior().addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				volverAtras(view.getTextCategoria().getText());				
			}
		});
		
		view.getButtonInicio().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				volverInicio();
				
			}
		});
		
		view.getListaProductos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CategoriaDto actual = model.getCategoria(view.getListaProductos().getSelectedValue());
				actualizarLista(actual);
			}
		});
	
		
		//boton eliminar de carrito
		view.getBtnEliminar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int filaSeleccionada = view.getTablaCarrito().getSelectedRow();
				if (filaSeleccionada != -1) {
					String nombreProducto = (String) view.getTablaCarrito().getValueAt(filaSeleccionada, 0);
					view.getTableCarritoModel().removeRow(filaSeleccionada);
					view.getCarrito().removeFromCarrito(nombreProducto);
					
					
					model.eliminaProductoCarrito(nombreProducto, view.getDto().getName());
					
					
					actualizaPrecioTotal();
				}
			}
		});
		
			
		view.getBtnAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				int fila = view.getTablaProductos().getSelectedRow();
				Producto pSeleccionado = model.getProducto((String) view.getTablaProductos().getValueAt(fila, 0));
				if (!model.checkProductoYaEnCarrito(pSeleccionado.getNombre())) {
					int cantidad = (int) view.getSpinnerUnidades().getValue();
					model.getCarrito().addToCarrito(pSeleccionado, cantidad);
					
					Object[] filaNueva = {pSeleccionado.getNombre(), cantidad, pSeleccionado.getPrecio()*cantidad};
					view.getTableCarritoModel().addRow(filaNueva);
					actualizaPrecioTotal();
					view.getSpinnerUnidades().setValue(1);
					
					
					
					model.añadeProductoCarrito(pSeleccionado.getId(), cantidad, view.getDto().getName());
					model.printProductoCarrito();
				} else {
					JOptionPane.showMessageDialog(view, "Este producto ya ha sido añadido. \nPuede modificar su cantidad o eliminarlo.");
				}
			}
		});
		
		//action listener para la JTABLE
		view.getTableCarritoModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // verificar si el cambio es en la columna 1 (Cantidad)
                if (e.getColumn() == 1 && e.getType() == TableModelEvent.UPDATE) {
                    int fila = e.getFirstRow(); //devuelve la fila que cambio
                    
                    // obtener la cantidad modificada
                    int nuevaCantidad = Integer.valueOf((String) view.getTableCarritoModel().getValueAt(fila, 1));
                    
                    String nuevoPrecio = model.getPrecioPorNombre((String) view.getTableCarritoModel().getValueAt(fila, 0), nuevaCantidad);
                    
                    view.getTableCarritoModel().setValueAt(nuevoPrecio, fila, 2);
                    
                    view.getCarrito().cambiaCantidadCarrito((String)view.getTableCarritoModel().getValueAt(fila, 0), nuevaCantidad);
                   
                    
                    model.modificarCantidadCarrito((String)view.getTableCarritoModel().getValueAt(fila, 0), nuevaCantidad, view.getDto().getName());
                    
                    actualizaPrecioTotal();                                    
                }
            }
        });		
		
		view.getTablaProductos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
				view.getBtnAdd().setEnabled(true);							
			}
		});
	}


	
	
	public void getListaProductos(String categoria) {
		if(categoria == null) {
			List<CategoriaDto> list = model.getCategoriasPrincipales();
			rellenarModelo(list);
			view.getListaProductos().setModel(modeloProducto);
		}
		List<CategoriaDto> list = model.getSubCategoria(categoria);
		rellenarModelo(list);
		view.getListaProductos().setModel(modeloProducto);
		if(modeloProducto.isEmpty()) {
			List<Producto> listProductos = model.getProductos(categoria);
			rellenarModeloProducto(listProductos);
			view.getListaProductos().setModel(modeloProducto);
		}
	}
	
	private void rellenarModeloProducto(List<Producto> productos) {
		view.mostrarPanel("pnTabla");
		for(Producto p : productos) {
			Object[] filaNueva = {p.getNombre(), p.getPrecio(), p.getDescripcion()};
			view.getTableProductosModel().addRow(filaNueva);
		}
	}

	private void rellenarModelo(List<CategoriaDto> categorias) {
		view.mostrarPanel("pnLista");
		for(CategoriaDto c : categorias) {
			modeloProducto.addElement(c.getNombreCategoria());
		}
	}
	
	private void actualizarLista(CategoriaDto categoria) {
		modeloProducto.clear();
		if(categoria == null) {
			 getListaProductos(null);
			 view.getTextCategoria().setText("Inicio");
			 view.getButtonAnterior().setEnabled(false);
			 view.getButtonInicio().setEnabled(false);
		}else {
			getListaProductos(categoria.getNombreCategoria());
			 view.getTextCategoria().setText(categoria.getNombreCategoria());
			 view.getButtonAnterior().setEnabled(true);
			 view.getButtonInicio().setEnabled(true);
		}
		  
	}
	
	private void volverAtras(String categoria) {
		limpiarModeloProductos();
		view.getBtnAdd().setEnabled(false);
		CategoriaDto actual = model.getCategoria(categoria);
		if(actual.getCategoriaPadre() == null) {
			volverInicio();
		}else {
			actualizarLista(model.getCategoria(actual.getCategoriaPadre()));
		}	
	}
	
	private void volverInicio() {
		limpiarModeloProductos();
		view.getBtnAdd().setEnabled(false);
		actualizarLista(null);
	}
	
	private void actualizaPrecioTotal() {
		String formattedNumber = String.format("%.2f", view.getCarrito().getTotal());
		view.getTextPrecioTotal().setText(formattedNumber);
	}
	
	private void limpiarModeloProductos() {
		for(int i=0 ; i<view.getTableProductosModel().getRowCount(); i++) {
			view.getTableProductosModel().removeRow(i);
		}
		
	}

}
