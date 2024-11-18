package modelo.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import giis.demo.util.Database2;
import persistence.dto.ClienteDto;
import persistence.dto.PaqueteDto;
import persistence.dto.ProductoDto;

public class EtiquetaModel {
	
	private PaqueteDto paquete = new PaqueteDto();
	private Database2 db;
	private ClienteDto cliente = new ClienteDto();
	
	private final static String SQL_GET_CLIENTE = "SELECT idCliente, nombre, pais, region, ciudad, calle "
			+ "FROM Cliente "
			+ "WHERE idCliente in ("
			+ "	SELECT idCliente"
			+ "	FROM pedido"
			+ "	WHERE idPedido like ?"
			+ ")";
	private final static String SQL_PAQUETES = "select * from Paquete where idPaquete = ?";
	private final static String SQL_PRODUCTOS_ID = "select * from PaqueteProducto where idPaquete = ?";
	private final static String SQL_PRODUCTOS = "select * from Producto where id = ?";
	
	public EtiquetaModel(Database2 db, int paquete) {
		this.db = db;
		this.paquete.idPaquete = paquete;
		getPaquete();
		getProductos();
		setCliente();
	}
	// del paquete pillo ipedido y del pedido el id del cliente y los set como en albaran model
	//modificar etiquetamodel, etiqueta controller, para que se vean datos de cliente como en el albaran
	public EtiquetaModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
		paquete.idPaquete = 2;
		getPaquete();
		getProductos();
	}
	

	private void getPaquete(){
		List<Object[]> result = db.executeQueryArray(SQL_PAQUETES, paquete.idPaquete);
		paquete.idPedido = (int)result.get(0)[1];
	}
	
	private void getProductos(){
		Map<ProductoDto, Integer> productosMapa = new HashMap<>();
		List<Object[]> idProductos = db.executeQueryArray(SQL_PRODUCTOS_ID, paquete.idPaquete);
		
		List<Object[]> producto;
		for (int i = 0; i < idProductos.size(); i++) {
			producto = db.executeQueryArray(SQL_PRODUCTOS, idProductos.get(i)[1]);
			ProductoDto p = new ProductoDto();
			p.idProducto = (int)producto.get(0)[0];
			p.nombre = (String)producto.get(0)[1];
			p.categoria = (String)producto.get(0)[2];
			p.descripcion = (String)producto.get(0)[3];
			p.precio = (Double)producto.get(0)[4];
			p.pasillo = (int)producto.get(0)[5];
			p.estanteria = (int)producto.get(0)[6];
			p.balda = (int)producto.get(0)[7];
			productosMapa.put(p, (int)idProductos.get(i)[2]);
		}
		paquete.productos = productosMapa;
	}
	
	private void setCliente() {
		
		
		List<Object[]> result = db.executeQueryArray(SQL_GET_CLIENTE, paquete.idPedido);
		
		cliente.idCliente = (String)result.get(0)[0];
		cliente.nombre = (String)result.get(0)[1];
		cliente.pais = (String)result.get(0)[2];
		cliente.region = (String)result.get(0)[3];
		cliente.ciudad = (String)result.get(0)[4];
		cliente.calle = (String)result.get(0)[5];

	}
	
	public ClienteDto getCliente() {
		return this.cliente;
	}

	public int getIDPedido() {
		return paquete.idPedido;
	}
	
	public int getIDPaquete() {
		return paquete.idPaquete;
	}
	
	public String getProductosString() {
		return paquete.productosToString();
	}
}
