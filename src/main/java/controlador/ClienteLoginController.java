package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.dto.ClienteDTO;
import modelo.modelo.LoginModel;
import persistence.dto.ClienteDto;
import vista.ClienteLoginView;
import vista.ClienteView;

public class ClienteLoginController {
	
	
	private ClienteLoginView vista;//asd
	private LoginModel model;
	
	public ClienteLoginController(ClienteLoginView vista){
		this.vista = vista;
		this.model = new LoginModel(vista.getDb());
	}
	
	public void init() {
		
		vista.getBtnInvitado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//hacer proceso como cliente invitado
				ClienteDto dto = new ClienteDto();
				dto.nombreUsusario = "Invitado";
				
				ClienteView compra = new ClienteView(vista.getDb(), dto);
				compra.getLblNombreUsuario().setText(dto.nombreUsusario);
				compra.setLocationRelativeTo(vista);
				vista.dispose();
				compra.setVisible(true);
			}
		});
		
		vista.getBtnSiguiente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!vista.getTextNombreUsuario().getText().isEmpty()) {
					
					if (model.doesClientExist(vista.getTextNombreUsuario().getText())) {

						//ClienteDTO dto = new ClienteDTO(vista.getTextNombreUsuario().getText());
						ClienteDto dto = new ClienteDto();
						dto.nombreUsusario = vista.getTextNombreUsuario().getText();
						model.rellenaDto(dto);
						
						ClienteView compra = new ClienteView(vista.getDb(), dto);
						compra.getLblNombreUsuario().setText(vista.getTextNombreUsuario().getText());
						compra.setLocationRelativeTo(vista);
						vista.dispose();
						compra.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(vista, "Usuario inexistente");
					}
					
					
				} else {
					JOptionPane.showMessageDialog(vista, "Debe proporcionar su nombre de usuario");
				}
			}
		});
	}
	
	

}
