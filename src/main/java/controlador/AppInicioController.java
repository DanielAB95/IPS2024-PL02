package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.modelo.InformeAlmaceneroModel;
import vista.AlmaceneroInicioView;
import vista.AppInicioView;
import vista.ClienteLoginView;
import vista.InformeAlmaceneroView;

public class AppInicioController {
	
	private AppInicioView view;
	
	
	public AppInicioController (AppInicioView view) {
		this.view = view;
	}
	
	public void initController() {
		view.getBtnCliente().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
		
		view.getBtnReset().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getDatabase().loadDatabase();
				System.out.println(" > Base de Datos Reseteada.");
			}
		});
		
		view.getBtInformes().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InformeAlmaceneroModel infalmo = new InformeAlmaceneroModel(view.getDatabase());
				InformeAlmaceneroController infalco = new InformeAlmaceneroController(infalmo);
				InformeAlmaceneroView infalvi = new InformeAlmaceneroView(infalco);
				view.dispose();
				infalvi.setVisible(true);
				
			}
		});
		
	}
}
