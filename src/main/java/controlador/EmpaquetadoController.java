package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.modelo.EmpaquetadoModel;
import vista.EmpaquetadoView;
import vista.PedidoView;
import vista.RecogidaView;
import vista.WorkorderView;

public class EmpaquetadoController {
	
	private EmpaquetadoModel em;
	private EmpaquetadoView ew;
	
	public EmpaquetadoController(EmpaquetadoView ew) {
		this.em = new EmpaquetadoModel();
		this.ew = ew;
	}
	
	public EmpaquetadoController(EmpaquetadoView ew, EmpaquetadoModel em) {
		this.em = em;
		this.ew = ew;
	}
	
	public void init() {
		initController();
	}
	
	public void initController() {
		accionWorkOrder();
		accionEmpaquetado();
		accionRecogida();
	}

	private void accionRecogida() {
		ew.getBtRecogida().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostarRecogida();
				
			}

			
		});	
	}

	private void accionEmpaquetado() {
		ew.getBtApuntar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
			}
		});
		
	}

	private void accionWorkOrder() {
		ew.getBtGenerarWorkOrder().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarWorkOrder();
				
			}	
		});	
	}
	
	private void mostarRecogida() {
		RecogidaView rView = new RecogidaView(ew.getDatabase());
		ew.dispose();
		rView.setVisible(true);
		
	}
	
	private void mostrarWorkOrder() {
		PedidoView pView = new PedidoView(ew.getDatabase());
		ew.dispose();
		pView.setVisible(true);
		
	}
	
}
