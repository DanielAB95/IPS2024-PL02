package controlador;

import java.time.LocalDate;

import modelo.modelo.AlbaranModel;
import persistence.dto.PedidoDto;
import vista.AlbaranView;

public class AlbaranController {

	private AlbaranView fv;
	private AlbaranModel fm;
	private PedidoDto pedido;
	
	public AlbaranController() {
		this.fm = new AlbaranModel();
	}
	
	public AlbaranController(AlbaranModel fm) {
		this.fm = fm;
	}
	
	public void setView(AlbaranView fv) {
		this.fv = fv;
	}

	public void init() {
		generarFactura();
	}
	
	private void generarFactura() {
		pedido = fm.getInfoPedido();
		StringBuilder factura = new StringBuilder();
        
		factura.append("Datos del cliente:\n");
		factura.append("\tNombre: " + "\n");
		factura.append("\tApellidos: " + "\n");
		factura.append("\tNIF: " + pedido.idCliente + "\n");
		factura.append("\tDireccion: " + "\n\n");
		factura.append("ID del pedido: " + pedido.idPedido + "\n");
		factura.append("Fecha del pedido: " + pedido.fecha + "\n");
		factura.append("Fecha de albaran: " + getDate() + "\n\n");
		factura.append("IDs de los paquetes: " + fm.getIDsPaquete() + "\n");
		factura.append("Productos: \n");
		factura.append(fm.getProductosString());
		
		fv.getTextArea().setText(factura.toString());
	}
	
	private String getDate() {
		LocalDate date = LocalDate.now();
        return date.toString();
	}
}
