package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.modelo.InformeRepuestoModel;
import persistence.dto.ProductoDto;
import vista.InformeMenuView;
import vista.InformeRepuestoView;

public class InformeRepuestoController {
	
	private InformeRepuestoModel model;
	private InformeRepuestoView view;
	
	public InformeRepuestoController() {
		this.model = new InformeRepuestoModel();
	}
	
	public InformeRepuestoController(InformeRepuestoModel model) {
		this.model = model;
	}
	
	public void init() {
		actionBtInformes();
		activarTabla();
	}
	
	private void actionBtInformes() {
		view.getButtonInforme().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InformeMenuController menucont = new InformeMenuController(model.getDb());
				InformeMenuView menuview = new InformeMenuView(menucont);
				view.dispose();
				menuview.setVisible(true);	
			}
		});
		
	}

	public void setView(InformeRepuestoView view) {
		this.view = view;
	}

	private void activarTabla() {
		limpiarModelo();
		List<ProductoDto> productos = model.productosReponer();
		for(ProductoDto dto : productos) {
			int cantidadRepuesto = dto.stockReposicion - dto.stock;
			Object[] filaNueva = {dto.idProducto,dto.nombre, dto.descripcion, dto.stock, cantidadRepuesto};
			view.getTableProductosModel().addRow(filaNueva);
		}	
	}

	private void limpiarModelo() {
		for(int i = view.getTableProductosModel().getRowCount()-1  ; i>=0; i++) {
			view.getTableProductosModel().removeRow(i);
		}	
	}
}


