package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		view.comboBoxCantidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getTextPrecio().setText(model.getPrecioPorNombre((String) view.getComboBoxProductos().getSelectedItem(), (int) view.getComboBoxCantidad().getSelectedItem() ));
			}
		});
		
		//añado controlador al boton de añadir productos al carrito
		view.btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				view.carrito.addToCarrito(model.getProductoPorNombre((String) view.getComboBoxProductos().getSelectedItem()), (int) view.getComboBoxCantidad().getSelectedItem());
			}
		});
		
		//añado controlador al boton de Siguiente
		view.btnSiguiente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				view.carrito.printCarrito();
			}
		});
		
	}

}
