package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import modelo.modelo.CargaPaqueteModel;
import modelo.modelo.EmpaquetadoModel;
import modelo.modelo.RecogidaModel;
import persistence.dto.AlmaceneroDto;
import persistence.dto.ClienteDto;
import persistence.dto.PaqueteDto;
import vista.CargaPaqueteView;
import vista.EmpaquetadoView;
import vista.PedidoView;
import vista.RecogidaView;

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
		almaceneroSesion();
		comboBoxValores();
		accionRecepcionVehiculo();
		accionEscanear();
		accionFinalizar();
		accionWorkOrder();
		accionEmpaquetado();
		accionRecogida();
	}

	private void almaceneroSesion() {
		AlmaceneroDto dto = model.getAlmacenero();
		view.getTextAlmacenero().setText(dto.idAlmacenero + " - " + dto.nombre + " " + dto.apellido);
		
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
	
	private void accionRecogida() {
		view.getButtonRecogida().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarRecogida();
			}
		});
	}

	private void accionEmpaquetado() {
		view.getButtonEmpaquetado().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarEmpaquetado();
				
			}		
		});
		
	}

	private void accionWorkOrder() {
		view.getButtonWorkOrder().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostarWorkOrder();
				
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

	
	private void RecepcionVehiculo() {	
		model.addVehiculo(view.getTextMatricula().getText(), view.getComboBoxZonaReparto().getSelectedItem().toString());
		JOptionPane.showMessageDialog(null, "Vehiculo registrado");
		
		
		mostrarPaquetes();
		
	}

	private void iniciarCarga() {
		view.getButtonRecepcionVehiculos().setEnabled(false);
		view.getComboBoxZonaReparto().setEnabled(false);
		view.getTextMatricula().setEditable(false);
		view.getButtonFinalizar().setEnabled(true);
		view.getButtonEscanear().setEnabled(true);
		
	}

	private void mostrarPaquetes() {
		PaqueteDto paquete;
		ClienteDto cliente;
		filtrarPaquetes();
		if(model.getPaquetes().size() == 0 && view.getButtonFinalizar().isEnabled() == false) {
			JOptionPane.showMessageDialog(null, "No hay paquetes para cargar");
			return;
		}else {
			for(int i = 0; i<model.getPaquetes().size();i++) {
				paquete = model.getPaquetes().get(i);
				cliente = model.getClientes().get(i);
				Object[] filaNueva = {paquete.idPaquete, paquete.fecha.toString(), cliente.nombre};
				view.getTablePaquetesModel().addRow(filaNueva);
			}
			iniciarCarga();
		}
	}  

	private void filtrarPaquetes() {
		if(view.getComboBoxZonaReparto().getSelectedItem().equals("Regional")) {
			model.paquetesZonaRegional();
		}else {
			model.paquetesZonaNacional();
		}
	}
	 
	

	private boolean checkVehiculo() {
		String text = view.getTextMatricula().getText().trim();
		if(text.length() != 7) {
			JOptionPane.showMessageDialog(null, "La matricula debe tener 4 numeros y 3 letras"
					, "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}else if (!checkNumber(text.substring(0,4).toCharArray())) {
			JOptionPane.showMessageDialog(null, "Los primeros 4 caracteres deben ser números."
					, "Error", JOptionPane.ERROR_MESSAGE);
			return false;
	    }else if (!checkLetras(text.substring(4,7).toCharArray())) {	 
	    	JOptionPane.showMessageDialog(null, "Los últimos 3 caracteres deben ser letras"
	    			, "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }else {
	    	JOptionPane.showMessageDialog(null, "Registrando Vehiculo...");
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
		accionPaquetesTabla();
	}
	
	private void accionPaquetesTabla() {
		actualizarPaquetesCargados();
		limpiarModelo();
		mostrarPaquetes();
	}
	
	private void actualizarPaquetesCargados(){
		int[] filas = view.getTablePaquetes().getSelectedRows();
		for(int i : filas) {
			model.paqueteReparto((int)view.getTablePaquetes().getValueAt(i, 0));
		}
	}
	
	private void finalizaCarga() {
		JOptionPane.showMessageDialog(null, "Se ha finalizado el proceso de carga");
		limpiarDatos();
		view.getButtonRecepcionVehiculos().setEnabled(true);
		view.getComboBoxZonaReparto().setEnabled(true);
		view.getTextMatricula().setEditable(true);
		view.getButtonFinalizar().setEnabled(false);
		view.getButtonEscanear().setEnabled(false);
	}

	private void limpiarDatos() {
		limpiarModelo();
		view.getTextMatricula().setText("");
		view.getComboBoxZonaReparto().setSelectedIndex(0);
		
	}

	private void limpiarModelo() {
		model.cleanList();
		for(int i = view.getTablePaquetesModel().getRowCount()-1 ; i>=0; i--) {
			view.getTablePaquetesModel().removeRow(i);
		}
	}
	

	private void mostrarRecogida() {
		RecogidaModel rm = new RecogidaModel(model.getDb(), model.getAlmacenero().idAlmacenero);
		RecogidaController rc = new RecogidaController(rm);
		RecogidaView rView = new RecogidaView(rc);
		view.dispose();
		rView.setVisible(true);
	}
	
	private void mostarWorkOrder() {
		PedidoView pView = new PedidoView(model.getDb(), model.getAlmacenero().idAlmacenero);
		view.dispose();
		pView.setVisible(true);
		
	}
	
	private void mostrarEmpaquetado() {
		EmpaquetadoModel em = new EmpaquetadoModel(model.getDb(), model.getAlmacenero().idAlmacenero);
		EmpaquetadoController ec = new EmpaquetadoController(em);
		EmpaquetadoView eView = new EmpaquetadoView(ec);
		view.dispose();
		eView.setVisible(true);	
	}
	
}
