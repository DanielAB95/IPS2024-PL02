package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.modelo.CargaPaqueteModel;
import vista.CargaPaqueteView;

public class CargaPaqueteController {
	private CargaPaqueteView view;
	private CargaPaqueteModel model;
	
	public CargaPaqueteController() {
		this.model = new CargaPaqueteModel();
	}
	
	public CargaPaqueteController(CargaPaqueteModel model) {
		this.model = model;
	}
	
	public void setView(CargaPaqueteView view) {
		this.view = view;
	}
	
	public void init() {
		
		accionPaquetesTabla();
		accionRecepcionVehiculo();
		accionEscanear();
		accionFinalizar();
	}
	
	

	private void accionFinalizar() {
		view.getButtonFinalizar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});;
		
	}

	private void accionEscanear() {
		view.getButtonEscanear().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
				 
			}
		});
		
	}

	private void accionRecepcionVehiculo() {
		view.getButtonRecepcionVehiculos().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkVehiculo()) {
					RecepcionVehiculo();	
				}
			}
		});;
	}

	private void accionPaquetesTabla() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean checkVehiculo() {
		String text = view.getTextMatricula().getText().trim();
		if(text.length() != 7) {
			 System.out.println("7");
			 return false;
		}else if (!checkNumber(text.substring(0,4).toCharArray())) {
			System.out.println("Los primeros 4 caracteres deben ser números.");
			return false;
	    }else if (!checkLetras(text.substring(4,7).toCharArray())) {	 
	    	 System.out.println("Los últimos 3 caracteres deben ser letras");
	        return false;
	    }else {
	    	 System.out.println("La matrícula es válida.");
	    	 return true;
	    }			
		
	}
	
	private boolean checkLetras(char[] chars) {
		  for (char c : chars) {
		        if (!Character.isLetter(c)) {	
		            return false;
		        }
		  }
		  return true;
	}

	private boolean checkNumber(char[] chars) {
	    for (char c : chars) {
	        if (!Character.isDigit(c)) {           
	            return false;
	        }
	    }
	    return true;
	}

	private void RecepcionVehiculo() {	
		model.addVehiculo(view.getTextMatricula().getText(), view.getComboBoxZonaReparto().getSelectedItem().toString());	
		//seleccionar paquetes por zona reparto
		
	}
}
