package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.dto.ClienteDTO;
import vista.ClienteLoginView;
import vista.ClienteView;

public class ClienteLoginController {
	
	
	private ClienteLoginView vista;
	
	public ClienteLoginController(ClienteLoginView vista){
		this.vista = vista;
	}
	
	public void init() {
		vista.getBtnSiguiente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteDTO dto = new ClienteDTO(vista.getTextNombreUsuario().getText());
				
				ClienteView compra = new ClienteView(vista.getDb(), dto);
				vista.dispose();
				compra.setVisible(true);
			}
		});
	}

}
