package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import modelo.dto.Producto;
import modelo.modelo.ClienteModel;
import vista.CarritoView;
import vista.ClienteView;

public class ClienteController {
	
	private ClienteView view;
	private ClienteModel model;
	
	public ClienteController(ClienteView view, ClienteModel model) {
		this.model = model;
		this.view = view;
	}
	
	public void initView() {
		
		view.getLblNombreUsuario().setText(model.getDto().getName());
	}
	
	public void initController() {
		
		
		
		//añado controlador al comboBox de cantidad de productos
		view.getComboBoxCantidad().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		
		//añado controlador al boton de Siguiente
		view.getBtnSiguiente().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!model.getCarrito().isEmpty()) {
					
					view.dispose();
					CarritoView frame = new CarritoView(view.getCarrito(), model.getDatabase(), model.getDto());
					frame.setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(view, "No hay ningún producto en el carrito.");
				}
			}
		});
		
		
		//boton eliminar de carrito
		view.getBtnEliminar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int filaSeleccionada = view.getTable().getSelectedRow();
				if (filaSeleccionada != -1) {
					String nombreProducto = (String) view.getTable().getValueAt(filaSeleccionada, 0);
					view.getTableModel().removeRow(filaSeleccionada);
					view.getCarrito().removeFromCarrito(nombreProducto);
					
					actualizaLblTotal();
				}
			}
		});
		
		
		//action listener apra la JTABLE
		view.getTableModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // verificar si el cambio es en la columna 1 (Cantidad)
                if (e.getColumn() == 1 && e.getType() == TableModelEvent.UPDATE) {
                    int fila = e.getFirstRow(); //devuelve la fila que cambio
                    
                    // obtener la cantidad modificada
                    int nuevaCantidad = Integer.valueOf((String) view.getTableModel().getValueAt(fila, 1));
                    
                    String nuevoPrecio = model.getPrecioPorNombre((String) view.getTableModel().getValueAt(fila, 0), nuevaCantidad);
                    
                    view.getTableModel().setValueAt(nuevoPrecio, fila, 2);
                    
                    view.getCarrito().cambiaCantidadCarrito((String)view.getTableModel().getValueAt(fila, 0), nuevaCantidad);
                   
                    actualizaLblTotal();
                    
                    
                }
            }
        });
		
		
		view.getTablaProductos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				int fila = view.getTablaProductos().getSelectedRow();
				String producto = (String) view.getTablaProductos().getValueAt(fila, 0);
				String categoria = model.getCategoriaPorNombre(producto);
				view.getTextCategoria().setText(categoria);
			}
		});
		
		
		view.getBtnAdd().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int fila = view.getTablaProductos().getSelectedRow();
				if (fila != -1) {
					String producto = (String) view.getTablaProductos().getValueAt(fila, 0);
					Producto p = model.getProductoPorNombre(producto);
					int cantidad = (int)view.getComboBoxCantidad().getSelectedItem();
					
					if (!model.checkProductoYaEnCarrito(producto)) {
						model.getCarrito().addToCarrito(p, cantidad);
						
						Object[] filaNueva = {p.getNombre(), cantidad, p.getPrecio()*cantidad};
						view. getTableModel().addRow(filaNueva);
						actualizaLblTotal();
						view.getComboBoxCantidad().setSelectedIndex(0);
					} else {
						JOptionPane.showMessageDialog(view, "Este producto ya ha sido añadido. \nPuede modificar su cantidad o eliminarlo.");
					}
				}
			}
		});
		
	}
	
	private void actualizaLblTotal() {
		String formattedNumber = String.format("%.2f", view.getCarrito().getTotal());
		view.getTextPrecioTotal().setText( formattedNumber );
	}

}
