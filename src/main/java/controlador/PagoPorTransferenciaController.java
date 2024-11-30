package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.modelo.CarritoModel;
import vista.PagoPorTransferenciaView;

public class PagoPorTransferenciaController {
	
	private CarritoModel model;
	private PagoPorTransferenciaView view;
	
	public PagoPorTransferenciaController (CarritoModel c, PagoPorTransferenciaView v) {
		this.view = v;
		this.model = c;
	}
	
	
	public void initController() {
		view.getBtnFinalizarCompra().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(view, "¡Gracias por tu compra!" +
	                       " Hemos recibido tu pedido y se enviará a la dirección proporcionada");
				model.confirmarPedido("Transferencia");
				view.dispose();
			}
		});
	}
	

}
