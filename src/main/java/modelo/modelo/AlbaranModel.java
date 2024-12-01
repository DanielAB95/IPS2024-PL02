package modelo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.ClienteDto;
import persistence.dto.PaqueteDto;
import persistence.dto.PedidoDto;
import persistence.dto.ProductoDto;

public class AlbaranModel {

	private List<PaqueteDto> paquetes = new ArrayList<>();
	private PedidoDto pedido = new PedidoDto();
	private ClienteDto cliente = new ClienteDto();
	private Database2 db;
	
	private final static String SQL_PAQUETES = "select * from Paquete where idPedido = ?";
	private final static String SQL_PEDIDO = "select * from Pedido where idPedido = ?";
	private final static String SQL_PRODUCTOS_ID = "select * from PedidoProducto where idPedido = ?";
	private final static String SQL_PRODUCTOS = "select * from Producto where id = ?";
	private final static String SQL_CLIENTE = "select * from Cliente where idCliente = ?";
	
	public AlbaranModel(Database2 db, int idPedido) {
		this.db = db;
		pedido.idPedido = idPedido;
		getPedido();
		getPaquetes();
		setCliente();
	}
	
	public AlbaranModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
		pedido.idPedido = 3;
		getPedido();
		getPaquetes();
		setCliente();
	}
	

	private void getPedido() {
		List<Object[]> result = db.executeQueryArray(SQL_PEDIDO, pedido.idPedido);
		pedido.idCliente = (String)result.get(0)[1];
		pedido.fecha = LocalDate.parse((String)result.get(0)[2]);
		pedido.estadoPedido = (String)result.get(0)[3];
		pedido.productos = new HashMap<>();
		getProductos();
	}

	private void getPaquetes(){
		List<Object[]> result = db.executeQueryArray(SQL_PAQUETES, pedido.idPedido);
		for (Object[] o : result) {
			PaqueteDto dto = new PaqueteDto();
			dto.idPaquete = (int)o[0];
			paquetes.add(dto);
		}
	}
	
	private void getProductos(){
		List<Object[]> idProductos = db.executeQueryArray(SQL_PRODUCTOS_ID, pedido.idPedido);
		
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
			pedido.productos.put(p, (int)idProductos.get(i)[2]);
		}
	}

	public PedidoDto getInfoPedido() {
		return pedido;
	}
	
	public String getIDsPaquete() {
		StringBuilder sb = new StringBuilder();
		for (PaqueteDto dto : paquetes) {
			sb.append(dto.idPaquete + "-");
		}
		String str = sb.toString();
		return str.substring(0, str.length() - 1);
	}
	
	private void setCliente() {
		List<Object[]> result = db.executeQueryArray(SQL_CLIENTE, pedido.idCliente);
		
		cliente.idCliente = (String)result.get(0)[0];
		cliente.nombreUsusario = (String)result.get(0)[1];
		cliente.nombre = (String)result.get(0)[2];
		cliente.telefono = (String)result.get(0)[3];
		cliente.pais = (String)result.get(0)[4];
		cliente.region = (String)result.get(0)[5];
		cliente.ciudad = (String)result.get(0)[6];
		cliente.calle = (String)result.get(0)[7];
		cliente.tipoCliente = (String)result.get(0)[8];
	}
	
	public String getProductosString() {
		return pedido.productosToString();
	}
	
	public ClienteDto getCliente() {
		return cliente;
	}
}
