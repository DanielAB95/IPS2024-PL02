package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import giis.demo.util.Database2;
import modelo.modelo.InformePaquetesListosModel;
import persistence.dto.PaqueteDto;
import vista.InformeMenuView;
import vista.InformePaquetesListosView;

public class InformePaqutesListosControler {
	
	private InformePaquetesListosModel model;
	private InformePaquetesListosView view;
	
	public InformePaqutesListosControler() {
		model = new InformePaquetesListosModel(new Database2());
	}
	
	public InformePaqutesListosControler(InformePaquetesListosModel model) {
		this.model = model;
	}
	
	public void setView(InformePaquetesListosView view) {
		this.view = view;
	}
	
	public void init() {
		showPaquetesListos();
		actionBtInformes();
	}
	
	private void actionBtInformes() {
		view.getBtInformes().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InformeMenuController menucont = new InformeMenuController(model.getDB());
				InformeMenuView menuview = new InformeMenuView(menucont);
				view.dispose();
				menuview.setVisible(true);
				
			}
		});
	}

	private void showPaquetesListos() {
		view.getTableModel().addColumn("ID Paquete");
		view.getTableModel().addColumn("ID Pedido");
		view.getTableModel().addColumn("Numero de productos");
		for (PaqueteDto paquete : model.getPaquetes()) {
			Object[] data = {paquete.idPaquete, paquete.idPedido, model.getCantidadDeProductos(paquete)};
			view.getTableModel().addRow(data);
		}
	}

}
