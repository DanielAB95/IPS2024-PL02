package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

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
				if (checkTodosLosCamposRellenados() && checkFecha() && checkNumeroTarjeta() && checkCVV()) {
					JOptionPane.showMessageDialog(view, "¡Gracias por tu compra!" +
		                       " Hemos recibido tu pedido y se enviará a la dirección proporcionada");
					
					
					model.confirmarPedido();
					view.dispose();
				}
			}


		});
	}
	
	//MOVER TODO LO DE ABAJO A CLASE MODEL NUEVA, NO PUEDE ESTAR EN CONOTROLLER
	public boolean checkTodosLosCamposRellenados() {
		if (view.getTextCvv().getText().isEmpty()
				|| view.getTextTarjeta().getText().isEmpty()
				|| view.getTextVencimiento().getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(view, "Por favor, rellene todos los campos");
			return false;
		}
		
		return true;
	}
	

	private boolean checkFecha() {
		String date = view.getTextVencimiento().getText();
		
		// Expresión regular para el formato MM/YY
			// ^(0[1-9]|1[0-2]) --> asegura que el mes esta entre 01 y 12
			// /\\d{2}$ --> verifica que hay una barra segurida de exactamente dos digitos
		if (!date.matches("^(0[1-9]|1[0-2])/\\d{2}$")) {
			JOptionPane.showMessageDialog(view, "Formato de fecha incorrecta");
            return false;
        }
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
		
		YearMonth inputDate = YearMonth.parse(date, formatter);
        YearMonth currentDate = YearMonth.now();
        
        if (!inputDate.isAfter(currentDate)) {
        	JOptionPane.showMessageDialog(view, "Fecha Incorrecta");
        	return false;
        }
        return true;
	}
	
	private boolean checkNumeroTarjeta() {
		String numero = view.getTextTarjeta().getText();
		
		//verificar que tenga solo dígitos y longitud:  13 >= longitud <= 19
	    if (!numero.matches("\\d{13,19}")) {
	    	JOptionPane.showMessageDialog(view, "Número de tarjeta Incorrecto");
	        return false;
	    }
	    
	    return true;
	}
	

	private boolean checkCVV() {
		String cvv = view.getTextCvv().getText();
		
		if (!cvv.matches("\\d{3}")) { // solo digitos y longitud 3
			JOptionPane.showMessageDialog(view, "CVV Incorrecto");
			return false;
		}
		return true;
	}
}
