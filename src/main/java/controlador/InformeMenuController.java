package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import giis.demo.util.Database2;
import modelo.modelo.InformeAlmaceneroModel;
import modelo.modelo.InformePaquetesListosModel;
import modelo.modelo.InformeRepuestoModel;
import modelo.modelo.InformeVentasTipoMinoristaModel;
import modelo.modelo.InformeVentasTipoPagoModel;
import modelo.modelo.InformeVentasTipoUsuarioModel;
import vista.InformeAlmaceneroView;
import vista.InformeMenuView;
import vista.InformePaquetesListosView;
import vista.InformeRepuestoView;
import vista.InformeVentasTipoMinoristaView;
import vista.InformeVentasTipoPagoView;
import vista.InformeVentasTipoUsuarioView;

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
		actionBtInformeProductosBajoStock();
		actionBtInformeVentasTipoUsuario();
		actionBtInformeVentasTipoPago();
		actionBtInformeVentasMinorista();
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
	
	private void actionBtInformeProductosBajoStock() {
		view.getBtInformeProductosReponer().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InformeRepuestoModel infProductosMod = new InformeRepuestoModel(db);
				InformeRepuestoController infProductosCont = new InformeRepuestoController(infProductosMod);
				InformeRepuestoView infProductosView = new InformeRepuestoView(infProductosCont);
				view.dispose();
				infProductosView.setVisible(true);	
			}
		});
		
	}
	
	private void actionBtInformeVentasMinorista() {
		view.getBtInformeVentasMinorista().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InformeVentasTipoMinoristaModel infVMinoristaMod = new InformeVentasTipoMinoristaModel(db);
				InformeVentasTipoMinoristaController infVMinoristaCont = new InformeVentasTipoMinoristaController(infVMinoristaMod);
				InformeVentasTipoMinoristaView infVMinoristaView  = new InformeVentasTipoMinoristaView(infVMinoristaCont);
				view.dispose();
				infVMinoristaView.setVisible(true);
			}
		});
		
	}

	private void actionBtInformeVentasTipoPago() {
		view.getBtInformeVentasTipoPago().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InformeVentasTipoPagoModel infVTipoPagoMod = new InformeVentasTipoPagoModel(db);
				InformeVentasTipoPagoController infVTipoPagoCont = new InformeVentasTipoPagoController(infVTipoPagoMod);
				InformeVentasTipoPagoView infVTipoPagoView  = new InformeVentasTipoPagoView(infVTipoPagoCont);
				view.dispose();
				infVTipoPagoView.setVisible(true);		
			}
		});	
	}

	private void actionBtInformeVentasTipoUsuario() {
		view.getBtInformeVentasTipoUsuario().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InformeVentasTipoUsuarioModel infVTipoUsuarioMod = new InformeVentasTipoUsuarioModel(db);
				InformeVentasTipoUsuarioController infVTipoUsuarioCont = new InformeVentasTipoUsuarioController(infVTipoUsuarioMod);
				InformeVentasTipoUsuarioView infVTipoUsuarioView  = new InformeVentasTipoUsuarioView(infVTipoUsuarioCont);
				view.dispose();
				infVTipoUsuarioView.setVisible(true);	
			}
		});
	}


}
