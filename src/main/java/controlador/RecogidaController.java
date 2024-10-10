package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.modelo.RecogidaModel;
import vista.RecogidaView;

public class RecogidaController {
	
	private RecogidaModel rm;
	private RecogidaView rw;
	
	public RecogidaController(RecogidaView rw) {
		this.rm = new RecogidaModel();
		this.rw = rw;
	}
	
	public void initController() {
		addExit();
	}
	
	private void addExit() {
		rw.getBtCancelar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
