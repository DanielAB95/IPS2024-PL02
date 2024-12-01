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

import modelo.dto.Producto;
import modelo.modelo.ClienteModel;
import persistence.dto.CategoriaDto;
import vista.CarritoView;
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
		view.getLblNombreUsuario().setText(view.getDto().nombreUsusario);
		//view.getLblNombreUsuario().setText(lview.getTextNombreUsuario().getText());
		
		if (!view.getDto().nombreUsusario.equals("Invitado")) {
			model.rellenaTablaCarrito(view.getTableCarritoModel(), view.getDto().nombreUsusario);
		}
		actualizaPrecioTotal();
		getListaProductos(null);
	}
			
	public void initController() {
		//añado controlador al boton de Siguiente
		view.getBtnSiguiente().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!model.getCarrito().isEmpty()) {
					
					if (model.checkStockCarrito()) {
						
						String nombreUsuario = view.getLblNombreUsuario().getText();
						if (!nombreUsuario.equals("Invitado") && model.esClienteDeEmpresa(nombreUsuario)) {
							
							JOptionPane.showMessageDialog(view, "¡Gracias por su compra!" +
				                       " Hemos recibido su pedido y se enviará a la dirección proporcionada por la empresa");
							model.confirmarPedido("Transferencia");
							model.borraCarritoCliente(view.getDto().nombreUsusario); //puede moverse a controlador de la siguiente ventana
						} else {
							
							view.dispose();
							CarritoView frame = new CarritoView(view.getCarrito(), view.getDatabase(), view.getDto());
							//frame.getLblNombreUsuario().setText(lview.getTextNombreUsuario().getText()); mejor usar el dto, 
							//cambiar los dto y databases al model en vez de tenerlo en view
							frame.getLblNombreUsuario().setText(view.getDto().nombreUsusario);
							frame.setLocationRelativeTo(view);
							view.dispose();
							frame.setVisible(true);
						}
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
					
					
					if (!view.getLblNombreUsuario().getText().equals("Invitado")) {
						model.eliminaProductoCarrito(nombreProducto, view.getDto().nombreUsusario);
					}
					
					actualizaPrecioTotal();
				}
			}
		});
		
			
		view.getBtnAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
//				int fila = view.getTablaProductos().getSelectedRow();
//				Producto pSeleccionado = model.getProducto((String) view.getTablaProductos().getValueAt(fila, 0));
//				if (!model.checkProductoYaEnCarrito(pSeleccionado.getNombre())) {
//					int cantidad = (int) view.getSpinnerUnidades().getValue();
//					model.getCarrito().addToCarrito(pSeleccionado, cantidad);
//					
//					Object[] filaNueva = {pSeleccionado.getNombre(), cantidad, pSeleccionado.getPrecio()*cantidad};
//					view.getTableCarritoModel().addRow(filaNueva);
//					actualizaPrecioTotal();
//					view.getSpinnerUnidades().setValue(1);
//					
//					
//					if (!view.getLblNombreUsuario().getText().equals("Invitado")) {
//						model.añadeProductoCarrito(pSeleccionado.getId(), cantidad, view.getDto().getName());
//						model.printProductoCarrito();
//					}
//				} else {
//					JOptionPane.showMessageDialog(view, "Este producto ya ha sido añadido. \nPuede modificar su cantidad o eliminarlo.");
//				}
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
                    
                    if (nuevaCantidad > 0) {
                    	String nuevoPrecio = model.getPrecioPorNombre( view.getDto().tipoCliente, (String) view.getTableCarritoModel().getValueAt(fila, 0), nuevaCantidad);
                        
                        view.getTableCarritoModel().setValueAt(nuevoPrecio, fila, 2);
                        
                        view.getCarrito().cambiaCantidadCarrito((String)view.getTableCarritoModel().getValueAt(fila, 0), nuevaCantidad);
                       
                        if (!view.getLblNombreUsuario().getText().equals("Invitado")) {
                        	model.modificarCantidadCarrito((String)view.getTableCarritoModel().getValueAt(fila, 0), nuevaCantidad, view.getDto().nombreUsusario);
                        }
                        actualizaPrecioTotal();
                        
                    } else {
                    	
                    	JOptionPane.showMessageDialog(view, "La cantidad del Producto debe ser mayor que 0.");
                    	view.getTableCarritoModel().setValueAt("1", fila, 1);
                    }
                    
                                                  
                }
            }
        });		
		
		view.getTablaProductos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
				view.getBtnAdd().setEnabled(true);
				// quitar el btn añadir
				// 
				addProducto();
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
	
	private void rellenarModeloProducto(List<Producto> productos) { //CAMBIAR AQUI LO QUE HAYA QUE PONER EN STOCK(Ej: nº o Bajo Stock, etc)
		view.mostrarPanel("pnTabla");
		for(Producto p : productos) {
			
			String stockMessage = "";
			
			if (p.getStock() < p.getMinStock() && p.getStock() > 0)
				stockMessage = "Bajo Stock";
			else if (p.getStock() <= 0)
				stockMessage = "No Disponible";
			else
				stockMessage = "Disponible";
			
			double precioMostrado = 0;
			
			if (view.getDto().tipoCliente.equals("EMPRESA")) {
				precioMostrado = p.getPrecioEmpresa();
			} else {
				precioMostrado = p.getPrecio() + (p.getIva() / 100.0 * p.getPrecio());
			}
			
			precioMostrado = Math.round(precioMostrado * 100.0) / 100.0;
			
			
			Object[] filaNueva = {p.getNombre(), precioMostrado, p.getDescripcion(), stockMessage};
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
		String formattedNumber = String.format("%.2f", view.getCarrito().getTotalConIVA(view.getDto().tipoCliente));
		view.getTextPrecioTotal().setText(formattedNumber);
		
		formattedNumber = String.format("%.2f", view.getCarrito().getTotalSinIVA(view.getDto().tipoCliente));
		view.getTextPrecioTotalSinIVA().setText(formattedNumber);
		
		formattedNumber = String.format("%.2f", view.getCarrito().getIVAtotalAñadido(view.getDto().tipoCliente));
		view.getTextIVAtotal().setText(formattedNumber);
	}
	
	private void limpiarModeloProductos() {
		for(int i=0 ; i<view.getTableProductosModel().getRowCount(); i++) {
			view.getTableProductosModel().removeRow(i);
		}
		
	}
	
	private void addProducto() {
		int fila = view.getTablaProductos().getSelectedRow();
		Producto pSeleccionado = model.getProducto((String) view.getTablaProductos().getValueAt(fila, 0));
		
		if (pSeleccionado.getStock() > 0) {
			if (!model.checkProductoYaEnCarrito(pSeleccionado.getNombre())) {
				int cantidad = (int) view.getSpinnerUnidades().getValue();
				model.getCarrito().addToCarrito(pSeleccionado, cantidad);
				
				
				double precioMostrado = 0;
				
				if (view.getDto().tipoCliente.equals("EMPRESA")) {
					precioMostrado = pSeleccionado.getPrecioEmpresa();
				} else {
					precioMostrado = pSeleccionado.getPrecio() + (pSeleccionado.getIva() / 100.0 * pSeleccionado.getPrecio());
				}
				
				precioMostrado = Math.round(precioMostrado * 100.0) / 100.0;
				
				
				Object[] filaNueva = {pSeleccionado.getNombre(), cantidad, precioMostrado * cantidad};
				
				
				
				view.getTableCarritoModel().addRow(filaNueva);
				actualizaPrecioTotal();
				view.getSpinnerUnidades().setValue(1);
				
				
				if (!view.getLblNombreUsuario().getText().equals("Invitado")) {
					model.añadeProductoCarrito(pSeleccionado.getId(), cantidad, view.getDto().nombreUsusario);
					model.printProductoCarrito();
				}
			} else {
				JOptionPane.showMessageDialog(view, "Este producto ya ha sido añadido. \nPuede modificar su cantidad o eliminarlo.");
			}
		} else {
			JOptionPane.showMessageDialog(view, "Este producto no está disponible en estos momentos.");
		}
	}


}
