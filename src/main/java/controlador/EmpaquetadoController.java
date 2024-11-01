package controlador;

import modelo.modelo.EmpaquetadoModel;
import vista.EmpaquetadoView;

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

	}

	
}
