package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

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
		view.getTextCategoria().setText(model.getCategoriaPorNombre((String) view.getComboBoxProductos().getSelectedItem()));
		view.getTextPrecio().setText(model.getPrecioPorNombre((String) view.getComboBoxProductos().getSelectedItem(), (int) view.getComboBoxCantidad().getSelectedItem() ));
		view.getTextDescripcion().setText(model.getDescripcionPorNombre((String) view.getComboBoxProductos().getSelectedItem()));
		view.getLblNombreUsuario().setText(model.getDto().getName());
	}
	
	public void initController() {
		//añado controlador al comboBox de productos
		view.getComboBoxProductos().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				view.getTextCategoria().setText(model.getCategoriaPorNombre((String) view.getComboBoxProductos().getSelectedItem()));
				view.getTextPrecio().setText(model.getPrecioPorNombre((String) view.getComboBoxProductos().getSelectedItem(), (int) view.getComboBoxCantidad().getSelectedItem() ));
				view.getTextDescripcion().setText(model.getDescripcionPorNombre((String) view.getComboBoxProductos().getSelectedItem()));
			}
		});
		
		
		//añado controlador al comboBox de cantidad de productos
		view.getComboBoxCantidad().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getTextPrecio().setText(model.getPrecioPorNombre((String) view.getComboBoxProductos().getSelectedItem(), (int) view.getComboBoxCantidad().getSelectedItem() ));
			}
		});
		
		//añado controlador al boton de añadir productos al carrito
		view.getBtnAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!model.checkProductoYaEnCarrito((String) view.getComboBoxProductos().getSelectedItem())) {
					
					int cantidad = (int) view.getComboBoxCantidad().getSelectedItem();
					view.getCarrito().addToCarrito(model.getProductoPorNombre((String) view.getComboBoxProductos().getSelectedItem()), cantidad);
					view.getListModel().addElement(view.getComboBoxProductos().getSelectedItem() +": "+ view.getComboBoxCantidad().getSelectedItem());

					
					Object[] nuevaFila = {view.getComboBoxProductos().getSelectedItem(), 
											cantidad,
											model.getProductoPorNombre((String) view.getComboBoxProductos().getSelectedItem()).getPrecio()*cantidad};
					
					actualizaLblTotal();
					
					view.getTableModel().addRow(nuevaFila);
					
					
				} else {
					JOptionPane.showMessageDialog(view, "Este producto ya ha sido añadido, puede modificar su cantidad o eliminarlo.");
				}
				
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
				String nombreProducto = (String) view.getTable().getValueAt(filaSeleccionada, 0);
				view.getTableModel().removeRow(filaSeleccionada);
				view.getCarrito().removeFromCarrito(nombreProducto);
				
				actualizaLblTotal();
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
                    String formattedNumber = String.format("%.2f", view.getCarrito().getTotal());
                    view.getTextPrecioTotal().setText( formattedNumber );
                    
                    
                }
            }
        });
		
	}
	
	private void actualizaLblTotal() {
		String formattedNumber = String.format("%.2f", view.getCarrito().getTotal());
		view.getTextPrecioTotal().setText( formattedNumber );
	}

}
