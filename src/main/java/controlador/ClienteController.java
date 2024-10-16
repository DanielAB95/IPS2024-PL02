package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
					
					String formattedNumber = String.format("%.2f", view.getCarrito().getTotal());
					view.getTextPrecioTotal().setText( formattedNumber);
					view.getTableModel().addRow(nuevaFila);
					System.out.println("Número de filas en el modelo: " + view.getTableModel().getRowCount());
					
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
		
	}

}
