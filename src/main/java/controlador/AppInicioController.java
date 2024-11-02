package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.AlmaceneroInicioView;
import vista.AppInicioView;
import vista.ClienteLoginView;
import vista.ClienteView;

public class AppInicioController {
	
	private AppInicioView view;
	
	
	public AppInicioController (AppInicioView view) {
		this.view = view;
	}
	
	public void initController() {
		view.getBtnCliente().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ClienteView cliente = new ClienteView(view.getDatabase());
				ClienteLoginView login = new ClienteLoginView(view.getDatabase());
				login.setLocationRelativeTo(view);
				view.dispose();
				
				login.setVisible(true);
			}
		});
		
		view.getBtnAlmacen().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AlmaceneroInicioView almacenero = new AlmaceneroInicioView(view.getDatabase());
				view.dispose();
				almacenero.setVisible(true);
				
			}
		});
	}
}
