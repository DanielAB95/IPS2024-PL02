package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

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
		comboBoxValores();
		accionPaquetesTabla();
		accionRecepcionVehiculo();
		accionEscanear();
		accionFinalizar();
	}
	
	

	private void comboBoxValores() {
		view.getComboBoxZonaReparto().setModel(new DefaultComboBoxModel<String>(new String[] {"Regional", "Nacional"}));
		
	}

	private void accionFinalizar() {
		view.getButtonFinalizar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				finalizaCarga();
			}	
		});;
		
	}

	private void accionEscanear() {
		view.getButtonEscanear().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarPaquetes();
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
	
	private void RecepcionVehiculo() {	
		model.addVehiculo(view.getTextMatricula().getText(), view.getComboBoxZonaReparto().getSelectedItem().toString());	
		filtrarPaquetes();
		
	}
	
	private void filtrarPaquetes() {
		if(view.getComboBoxZonaReparto().getSelectedItem().equals("Regional")) {
			
		}else {
			
		}
		
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
	
	private void cargarPaquetes() {
		JOptionPane.showMessageDialog(null, "Los paquetes seleccionados se han cargado en el vehiculo");
		
		
	}
	
	private void finalizaCarga() {
		JOptionPane.showMessageDialog(null, "Se ha finalizado el proceso de carga");
		limpiarDatos();
	}

	private void limpiarDatos() {
		view.getTextMatricula().setText("");
		view.getComboBoxZonaReparto().setSelectedIndex(0);
		limpiarModelo();
		
	}

	private void limpiarModelo() {
		for(int i=0 ; i<view.getTablePaquetes().getRowCount(); i++) {
			view.getTablePaquetesModel().removeRow(i);
		}
		
	}

	
}
