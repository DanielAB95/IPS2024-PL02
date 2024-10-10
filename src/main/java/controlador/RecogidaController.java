package controlador;

import modelo.modelo.RecogidaModel;
import vista.RecogidaView;

public class RecogidaController {
	
	private RecogidaModel rm;
	private RecogidaView rw;
	
	private RecogidaController(RecogidaModel rm, RecogidaView rw) {
		this.rm = rm;
		this.rw = rw;
	}
	
	private void exit() {
		
	}

}
