package controlador;

import java.util.List;

import modelo.modelo.InformeRepuestoModel;
import persistence.dto.ProductoDto;
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
		activarTabla();
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


