package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import giis.demo.util.Database2;
import modelo.modelo.InformeAlmaceneroModel;
import modelo.modelo.InformePaquetesListosModel;
import vista.InformeAlmaceneroView;
import vista.InformeMenuView;
import vista.InformePaquetesListosView;

public class InformeMenuController {
	
	private InformeMenuView view;
	private Database2 db;
	
	public InformeMenuController(Database2 db) {
		this.db = db;
	}
	
	public void setView(InformeMenuView view) {
		this.view = view;
	}
	
	public void init() {
		actionBtInformePaquetes();
		actionBtInformeAlmaceneros();
	}

	private void actionBtInformePaquetes() {
		view.getBtInformesPaquetes().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InformePaquetesListosModel infpalimo = new InformePaquetesListosModel(db);
				InformePaqutesListosControler infpalico = new InformePaqutesListosControler(infpalimo);
				InformePaquetesListosView infpalivi = new InformePaquetesListosView(infpalico);
				view.dispose();
				infpalivi.setVisible(true);
			}
		});
	}
	
	private void actionBtInformeAlmaceneros() {
		view.getBtInnformeAlmaceneros().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InformeAlmaceneroModel infpalimo = new InformeAlmaceneroModel(db);
				InformeAlmaceneroController infpalico = new InformeAlmaceneroController(infpalimo);
				InformeAlmaceneroView infpalivi = new InformeAlmaceneroView(infpalico);
				view.dispose();
				infpalivi.setVisible(true);
			}
		});
	}

}
