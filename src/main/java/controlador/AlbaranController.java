package controlador;

import java.time.LocalDate;

import modelo.modelo.AlbaranModel;
import persistence.dto.ClienteDto;
import persistence.dto.PedidoDto;
import vista.AlbaranView;

public class AlbaranController {

	private AlbaranView fv;
	private AlbaranModel fm;
	private PedidoDto pedido;
	private ClienteDto cliente;
	
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
		cliente = fm.getCliente();
		StringBuilder factura = new StringBuilder();
        
		factura.append("-Datos del cliente:\n");
		factura.append("\t-Nombre y apellidos: " + cliente.nombre + "\n");
		factura.append("\t-ID: " + cliente.idCliente + "\n");
		factura.append("\t-Direccion: " + cliente.calle + "\n\t\t" + cliente.ciudad + ", ");
		factura.append(cliente.region + ", " + cliente.pais + "\n\n");
		factura.append("-ID del pedido: " + pedido.idPedido + "\n");
		factura.append("-Fecha del pedido: " + pedido.fecha + "\n");
		factura.append("-Fecha de albaran: " + getDate() + "\n\n");
		factura.append("-IDs de los paquetes: " + fm.getIDsPaquete() + "\n");
		factura.append("-Productos: \n");
		factura.append(fm.getProductosString());
		
		fv.getTextArea().setText(factura.toString());
	}
	
	private String getDate() {
		LocalDate date = LocalDate.now();
        return date.toString();
	}
}
