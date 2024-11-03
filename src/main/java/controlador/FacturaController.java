package controlador;

import java.time.LocalDate;

import modelo.modelo.FacturaModel;
import vista.FacturaView;

public class FacturaController {
	
	private FacturaView fv;
	private FacturaModel fm;
	
	public FacturaController() {
		this.fm = new FacturaModel();
	}
	
	public FacturaController(FacturaModel fm) {
		this.fm = fm;
	}
	
	public void setView(FacturaView fv) {
		this.fv = fv;
	}

	public void init() {
		generarFactura();
	}
	
	private void generarFactura() {
		StringBuilder factura = new StringBuilder();
         
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
