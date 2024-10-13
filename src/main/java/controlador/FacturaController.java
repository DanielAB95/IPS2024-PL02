package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import modelo.dto.PaqueteWrapper;
import modelo.dto.ProductoWrapper;
import modelo.modelo.FacturaModel;
import vista.FacturaView;

public class FacturaController {
	
	private FacturaView fv;
	private FacturaModel fm;
	
	public FacturaController(FacturaView fv) {
		this.fv = fv;
		this.fm = new FacturaModel();
	}
	
	public FacturaController(FacturaView fw, FacturaModel fm) {
		this.fv = fw;
		this.fm = fm;
	}

	public void init() {
		fillComboBox();
		accionGenerar();
	}

	private void fillComboBox() {
		for (PaqueteWrapper paquete : fm.getPaquetes()) {
			fv.getComboBox().addItem(paquete);
		}		
	}
	
	private void accionGenerar() {
		fv.getBtGenerar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				generarFactura();
			}
		});
	}
	
	private void generarFactura() {
		StringBuilder factura = new StringBuilder();
         
		factura.append("Fecha: " + getDate() + "\n");
		factura.append("Numero de factura: 00123\n\n");
		factura.append("ID del paquete: " + getIDPaquete() + "\n");
		factura.append("Productos: \n");
		factura.append(getProductosString());
		
		fv.getTextArea().setText(factura.toString());
	}
	
	private String getDate() {
		Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
	}
	
	private int getIDPaquete() {
		PaqueteWrapper pq = (PaqueteWrapper)fv.getComboBox().getSelectedItem();
		return pq.getIDPaquete();
	}
	
	private String getProductosString() {
		int idWorkorder = ((PaqueteWrapper)fv.getComboBox().getSelectedItem()).getIDWO();
		List<ProductoWrapper> productos = fm.getProductos(idWorkorder);
		StringBuilder sb = new StringBuilder();
		for (ProductoWrapper p : productos) {
			sb.append("\t" + p.toString() + "\n");
		}
		
		return sb.toString();
	}

}
