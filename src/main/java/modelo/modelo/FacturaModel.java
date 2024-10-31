package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.PaqueteWrapper;
import modelo.dto.Producto;
import modelo.dto.ProductoWrapper;
import persistence.dto.PaqueteDto;

public class FacturaModel {
	
	private final static String SQL_PAQUETES = "select * from Paquete where paqueteEstado = 'Listo'";
	private final static String SQL_PRODUCTOS = "select * from Producto where id = ?";
	private final static String SQL_PRODUCTOS_ID = "select * from PedidoProducto where idPedido = "
												+ "(select idPedido from Workorder where idWorkorder = ?)";
	private PaqueteDto paquete = new PaqueteDto();
	private Database2 db;
	
	public FacturaModel(Database2 db) {
		this.db = db;
	}
	
	public FacturaModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
	}

	public List<PaqueteWrapper> getPaquetes(){
		List<PaqueteWrapper> paquetes = new ArrayList<>();
		List<Object[]> result = db.executeQueryArray(SQL_PAQUETES);
		
		for (Object[] o : result) {
			PaqueteWrapper paquete = new PaqueteWrapper((int)o[0], (int)o[1]);
			paquetes.add(paquete);
		}
		return paquetes;
	}
	
	public List<ProductoWrapper> getProductos(int idWorkorder){
		List<ProductoWrapper> productosWP = new ArrayList<>();
		List<Object[]> idProductos = db.executeQueryArray(SQL_PRODUCTOS_ID, idWorkorder);
		
		List<Object[]> productos;
		for (int i = 0; i < idProductos.size(); i++) {
			productos = db.executeQueryArray(SQL_PRODUCTOS, idProductos.get(i)[1]);
			Producto p = new Producto(productos.get(0)[0], productos.get(0)[1], productos.get(0)[2], productos.get(0)[3], productos.get(0)[4]);
			productosWP.add(new ProductoWrapper(p, (int)idProductos.get(i)[2]));
		}
		return productosWP;
	}

	public int getIDPedido() {
		
		return 0;
	}
}
