package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.Producto;
import modelo.dto.WorkorderDTO;

public class RecogidaModel {
	
	private WorkorderDTO dto = new WorkorderDTO();
	private Database2 db = new Database2();
	private final static String SQL_WORKORDER = "select * from Workorder where idWorkorder = ?";
	private final static String SQL_PRODUCTS = "select * from PedidoProducto where idPedido = ?";
	
	public RecogidaModel(int idWorkorder) {
		if (idWorkorder < 1) throw new IllegalArgumentException();
		dto.idWorkorder = idWorkorder;
	}
	
	public RecogidaModel() {
		dto.idWorkorder = 1;
		extractWorkorder();
	}
	
	private void extractWorkorder() {
		System.out.println(dto.idWorkorder + " " + dto.idAlmacenero + " " + dto.idPedido);
		List<Object[]> workorder = db.executeQueryArray(SQL_WORKORDER, Integer.toString(dto.idWorkorder));
		System.out.println(workorder.size());
		dto = new WorkorderDTO((int)workorder.get(0)[0], (int)workorder.get(0)[1], (int)workorder.get(0)[2]);
		System.out.println(dto.idWorkorder + " " + dto.idAlmacenero + " " + dto.idPedido);
	}
	
	public List<Producto> extractProducts() {
		List<Producto> resultado = new ArrayList<Producto>();
		List<Object[]> productos = db.executeQueryArray(SQL_PRODUCTS, Integer.toString(dto.idPedido)); 
		
		for (int i = 0; i < productos.size(); i++) {
			Producto p = new Producto(productos.get(i)[0], productos.get(i)[1], productos.get(i)[2], productos.get(i)[3], productos.get(i)[4]);
			resultado.add(p);
		}
		
		return resultado;
	}
	
	public void checkProduct(int id, int units) {
		checkID(id);
		checkUnits(id, units);
	}
	
	public int getWOID() {
		return dto.idWorkorder;
	}

	private void checkUnits(int id, int units) {
		//if (units != wo.getUnitsFromID(id)) throw new ApplicationException("La cantidad de unidades no concuerda");
		
	}

	private void checkID(int id) {
		//if (!wo.findProduct(id)) throw new ApplicationException("Producto no encontrado en el pedido");
	}

}
