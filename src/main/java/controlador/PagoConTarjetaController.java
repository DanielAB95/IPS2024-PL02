package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.modelo.CarritoModel;
import vista.PagoConTarjetaView;

public class PagoConTarjetaController {

	
	
	private CarritoModel model;
	private PagoConTarjetaView view;
	
	public PagoConTarjetaController (CarritoModel m, PagoConTarjetaView v) {
		this.model = m;
		this.view = v;
	}
	
	
	public void initController() {
		view.getBtnFinalizar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(view, "¡Gracias por tu compra!" +
	                       " Hemos recibido tu pedido y se enviará a la dirección proporcionada");
				model.confirmarPedido();
				view.dispose();
			}
		});
	}
}
