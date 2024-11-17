package controlador;

import java.time.LocalDate;

import modelo.modelo.EtiquetaModel;
import vista.EtiquetaView;

public class EtiquetaController {
	
	private EtiquetaView fv;
	private EtiquetaModel fm;
	
	public EtiquetaController() {
		this.fm = new EtiquetaModel();
	}
	
	public EtiquetaController(EtiquetaModel fm) {
		this.fm = fm;
	}
	
	public void setView(EtiquetaView fv) {
		this.fv = fv;
	}

	public void init() {
		generarFactura();
	}
	
	private void generarFactura() {
		StringBuilder factura = new StringBuilder();
         
		// CAMBIOS A REALIZAR: añadir direccion a factura (SIMILAR A ABAJO)
		factura.append("Fecha: " + getDate() + "\n\n");
		factura.append("ID del pedido: " + fm.getIDPedido() + "\n");
		factura.append("ID del paquete: " + fm.getIDPaquete() + "\n");
		factura.append("Productos: \n");
		factura.append(fm.getProductosString());
	
		
		fv.getTextArea().setText(factura.toString());
	}
	
	private String getDate() {
		LocalDate date = LocalDate.now();
        return date.toString();
	}

}
