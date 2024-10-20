package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
				
				if (!vista.getTextNombreUsuario().getText().isEmpty()) {
					
					ClienteDTO dto = new ClienteDTO(vista.getTextNombreUsuario().getText());
					
					ClienteView compra = new ClienteView(vista.getDb(), dto);
					vista.dispose();
					compra.setVisible(true);
					
				} else {
					JOptionPane.showMessageDialog(vista, "Debe proporcionar su nombre de usuario");
				}
			}
		});
	}
	
	

}
