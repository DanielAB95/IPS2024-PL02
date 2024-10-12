package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.AppInicioView;
import vista.ClienteView;

public class AppInicioController {
	
	private AppInicioView view;
	
	
	public AppInicioController (AppInicioView view) {
		this.view = view;
	}
	
	public void initController() {
		view.getBtnCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView cliente = new ClienteView(view.getDatabase());
				view.dispose();
				cliente.setVisible(true);
			}
		});
	}
}
