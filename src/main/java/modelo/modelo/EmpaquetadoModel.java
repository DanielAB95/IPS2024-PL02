package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.Producto;
import modelo.dto.ProductoWrapper;
import modelo.dto.WorkorderDTO;

public class EmpaquetadoModel {
	
	private Database2 db;
	private final static String SQL_WOLISTAS = "select * from Workorder where workorderEstado = 'Listo'";
	private final static String SQL_PRODUCTS_ID = "select idProducto, cantidad from pedidoproducto where idPedido = ?";
	private final static String SQL_PRODUCTS = "select * from producto where id = ?";
	private final static String SQL_PAQUETE = "insert into Paquete(idPaquete, idWorkorder, paqueteEstado) values (?,?,'Listo')";
	
	public EmpaquetadoModel(Database2 db2) {
		db = db2;
	}
	
	public EmpaquetadoModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
	}
	
	public List<WorkorderDTO> workordersListas(){
		List<WorkorderDTO> workorders = new ArrayList<>();
		List<Object[]> result = db.executeQueryArray(SQL_WOLISTAS);
		
		for (Object[] o : result) {
			WorkorderDTO wo = new WorkorderDTO((int)o[0], (int)o[1], (int)o[2]);
			workorders.add(wo);
		}
		return workorders;
	}
	
	public List<ProductoWrapper> productosPorWorkorder(int idPedido){
		List<ProductoWrapper> resultado = new ArrayList<>();
		List<Object[]> idProductos = db.executeQueryArray(SQL_PRODUCTS_ID, idPedido);
		
		List<Object[]> productos;
		for (int i = 0; i < idProductos.size(); i++) {
			productos = db.executeQueryArray(SQL_PRODUCTS, idProductos.get(i)[0]);
			Producto p = new Producto(productos.get(0)[0], productos.get(0)[1], productos.get(0)[2], productos.get(0)[3], productos.get(0)[4]);
			ProductoWrapper pw = new ProductoWrapper(p, (int)idProductos.get(i)[1]);
			resultado.add(pw);
		}
		
		return resultado;
	}

	public int checkID(int id, List<ProductoWrapper> productos) {
		for (int i = 0; i<productos.size(); i++) {
			if (id == productos.get(i).getID()) {
				return i;
			}
		}
		return -1;
	}
	
	public void empaquetar(int idPaquete, int idWorkorder) {
		db.executeUpdate(SQL_PAQUETE, idPaquete, idWorkorder);
	}
	
	public Database2 getDB() {
		return db;
	}

}
