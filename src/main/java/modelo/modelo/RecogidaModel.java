package modelo.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import giis.demo.util.ApplicationException;
import giis.demo.util.Database2;
import modelo.dto.Producto;
import modelo.dto.ProductoWrapper;
import modelo.dto.WorkorderDTO;

public class RecogidaModel {
	
	private WorkorderDTO dto = new WorkorderDTO();
	private Database2 db = new Database2();
	private final static String SQL_WORKORDER = "select * from workorder where idWorkorder = ?";
	private final static String SQL_PRODUCTS_ID = "select idProducto, cantidad from pedidoproducto where idPedido = ?";
	private final static String SQL_PRODUCTS = "select * from producto where id = ?";
	private final static String SQL_WOLISTA = "update workorder set workorderEstado = 'Listo' where idWorkorder = ?";
	
	
	public RecogidaModel(int idWorkorder) {
		if (idWorkorder < 1) throw new IllegalArgumentException();
		dto.idWorkorder = idWorkorder;
		extractWorkorder();
	}
	
	public RecogidaModel() {
		dto.idWorkorder = 1;
		extractWorkorder();
	}
	
	private void extractWorkorder() {
		List<Object[]> workorder = db.executeQueryArray(SQL_WORKORDER, dto.idWorkorder);
		dto = new WorkorderDTO((int)workorder.get(0)[0], (int)workorder.get(0)[1], (int)workorder.get(0)[2]);
		System.out.println((String)workorder.get(0)[3]);
	}
	
	public Map<Producto,Integer> extractProducts() {
		Map<Producto,Integer> resultado = new HashMap<>();
		List<Object[]> idProductos = db.executeQueryArray(SQL_PRODUCTS_ID, dto.idPedido);
		
		List<Object[]> productos;
		for (int i = 0; i < idProductos.size(); i++) {
			productos = db.executeQueryArray(SQL_PRODUCTS, idProductos.get(i)[0]);
			Producto p = new Producto(productos.get(0)[0], productos.get(0)[1], productos.get(0)[2], productos.get(0)[3], productos.get(0)[4]);
			System.out.println(p);
			resultado.put(p,(int)idProductos.get(i)[1]);
		}
		
		return resultado;
	}
	
	public boolean checkProduct(ProductoWrapper p, int id, int units) {
		try {
		checkID(p, id);
		checkUnits(p, units);
		return true;
		} catch (ApplicationException e) {
			return false;
		}
	}
	
	public int getWOID() {
		return dto.idWorkorder;
	}

	private void checkUnits(ProductoWrapper p, int units) {
		if (units != p.getUnits()) throw new ApplicationException("La cantidad de unidades no concuerda");
		
	}

	private void checkID(ProductoWrapper p, int id) {
		if (id != p.getID()) throw new ApplicationException("Producto no encontrado en el pedido");
	}

	public void pasarAListo() {
		db.executeUpdate(SQL_WOLISTA, dto.idWorkorder);
	}

}
