package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import modelo.modelo.CarritoModel;
import vista.CarritoView;

public class CarritoController {
	
	
	
	private CarritoView view;
	private CarritoModel modelo;
	
	public CarritoController(CarritoView v, CarritoModel m) {
		this.view = v;
		this.modelo = m;
	}
	
	public void initView() {
		view.getTextPrecioTotal().setText( String.valueOf(modelo.calcularPrecioTotal()) );
	}
	
	public void initController() {
		
		//input text
		view.getTextCantidad().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (view.getTextCantidad().getText() != null && !view.getTextCantidad().getText().isEmpty() && modelo.esNumeroEntero(view.getTextCantidad().getText())) {
					modelo.modificarCantidadProductoPorNombre((String) view.getList_2().getSelectedValue(),Integer.valueOf(view.getTextCantidad().getText()) );
					view.getTextCantidad().setText(String.valueOf( modelo.getCantidadProductoPorNombre( (String) view.getList_2().getSelectedValue() ) ));
					view.getTextProductos().setText(String.valueOf( modelo.getPrecioProductoPorNombre( (String) view.getList_2().getSelectedValue() ) ));
					view.getTextPrecioTotal().setText(modelo.calcularPrecioTotal());
				} else {
					modelo.mensajeInputErroneo();
				}	
				
			}
		});
		
		
		//btn eliminar
		view.getBtnEliminar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.borrarProductoCarrito((String) view.getList_2().getSelectedValue());
				if (view.getList_2().getSelectedIndex() != -1)  view.getListModel().remove(view.getList_2().getSelectedIndex());
				
				
				modelo.modificarCantidadProductoPorNombre((String) view.getList_2().getSelectedValue(), Integer.valueOf(view.getTextCantidad().getText()) );
				view.getTextCantidad().setText(String.valueOf( modelo.getCantidadProductoPorNombre( (String) view.getList_2().getSelectedValue() ) ));
				view.getTextProductos().setText(String.valueOf( modelo.getPrecioProductoPorNombre( (String) view.getList_2().getSelectedValue() ) ));
				view.getTextPrecioTotal().setText(modelo.calcularPrecioTotal());
			}
		});
		
		//btn confirmar
		view.getBtnConfirmar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				modelo.confirmarPedido();
			}
		});
		
		//lista 
		view.getList_2().addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		view.getTextCantidad().setText(String.valueOf( modelo.getCantidadProductoPorNombre( (String) view.getList_2().getSelectedValue() ) ));
        		view.getTextProductos().setText(String.valueOf( modelo.getPrecioProductoPorNombre( (String) view.getList_2().getSelectedValue() ) ));
        	}
        });
	}
}
