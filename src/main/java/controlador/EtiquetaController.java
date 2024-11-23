package controlador;

import java.time.LocalDate;

import modelo.modelo.EtiquetaModel;
import persistence.dto.ClienteDto;
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
         
		
		/*
		 * factura.append("-Datos del cliente:\n");
		factura.append("\t-Nombre y apellidos: " + cliente.nombre + "\n");
		factura.append("\t-ID: " + cliente.idCliente + "\n");
		factura.append("\t-Direccion: " + cliente.calle + "\n\t\t" + cliente.ciudad + ", ");
		factura.append(cliente.region + ", " + cliente.pais + "\n\n");
		 */
		
		ClienteDto cliente = fm.getCliente();
		
		// CAMBIOS A REALIZAR: añadir direccion a factura (SIMILAR A ABAJO)
		factura.append("Fecha: " + getDate() + "\n\n");
		
		factura.append("-Datos del cliente:\n");
		factura.append("\t-Nombre y apellidos: " + cliente.nombre + "\n");
		factura.append("\t-ID: " + cliente.idCliente + "\n");
		factura.append("\t-Direccion: " + cliente.calle + "\n\t\t" + cliente.ciudad + ", ");
		factura.append(cliente.region + ", " + cliente.pais + "\n\n");
		
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
