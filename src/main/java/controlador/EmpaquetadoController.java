package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.dto.WorkorderDTO;
import modelo.dto.WorkorderWrapper;
import modelo.modelo.EmpaquetadoModel;
import vista.EmpaquetadoView;

public class EmpaquetadoController {
	
	private EmpaquetadoModel em;
	private EmpaquetadoView ew;
	
	public EmpaquetadoController(EmpaquetadoView ew) {
		this.em = new EmpaquetadoModel();
		this.ew = ew;
	}
	
	public void init() {
		accionCancelar();
		fillComboBox();
	}

	private void accionCancelar() {
		ew.getBtCancelar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void fillComboBox() {
		for (WorkorderDTO dto : em.workordersListas()) {
			WorkorderWrapper wo = new WorkorderWrapper(dto);
			ew.getCbWorkorders().addItem(wo);
		}
	}
}
