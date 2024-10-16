package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.PedidoDTO;
import modelo.dto.PedidoProductoDTO;

public class PedidoModel {
	
	public static final String SQL_LISTA_PEDIDO = "select idPedido, numProductos, fecha  from Pedido where estado = 'Pendiente' order by fecha";
	public static final String SQL = "select * from Pedido";
	public static final String SQL_PEDIDO_ALMACENERO = "select ap.idAlmacenero from AlmaceneroPedido ap join Pedido p on ap.idPedido = p.idPedido and p.idPedido = ?";
	private static final String SQL_PRODUCTOS_PEDIDO = "select pp.idPedido, pp.idProducto, pp.cantidad, p.descripcion from PedidoProducto pp join Producto p ON pp.idProducto = p.id where pp.idPedido = ?";
	private static final String SQL_UPDATE_ESTADO_LISTO = "update Pedido set Estado = 'Listo' where idPedido = ? ";
	
	private Database2 db = new Database2();
	//private List<PedidoDTO> pedidos;
	
	public PedidoModel(Database2 database) {
		//this.pedidos = getPedidos();
		this.db = database;
	}
	

	public List<PedidoDTO> getPedidos() {
		List<PedidoDTO> list = new ArrayList<PedidoDTO>();
		List<Object[]> listDb = db.executeQueryArray(SQL_LISTA_PEDIDO);
		for(int i = 0; i< listDb.size(); i++) {
			PedidoDTO p = new PedidoDTO((int)listDb.get(i)[0], (int)listDb.get(i)[1], (String)listDb.get(i)[2]);//Arreglar
			list.add(p);
		}
		return list;
	}
	
	public int getIdAlmacenero(int idPedido) {
		List<Object[]> listDb = db.executeQueryArray(SQL_PEDIDO_ALMACENERO, idPedido);
		return (int)listDb.get(0)[0];
	}
	
	public List<PedidoProductoDTO> getProductosPorPedido(int idPedido){
		List<PedidoProductoDTO> list = new ArrayList<PedidoProductoDTO>();
		List<Object[]> listDb = db.executeQueryArray(SQL_PRODUCTOS_PEDIDO, idPedido);
		
		for(int i = 0; i<listDb.size();i++) {
			PedidoProductoDTO p = new PedidoProductoDTO((int)listDb.get(i)[0],(int)listDb.get(i)[1],(int)listDb.get(i)[2]);
			list.add(p);
		}
		return list;
	}
	
	public List<PedidoDTO> getPedidoAlmacenreo (int idAlmacenro){
		List<PedidoDTO> list = new ArrayList<PedidoDTO>();
		List<Object[]> listDb = db.executeQueryArray(SQL_PRODUCTOS_PEDIDO, idAlmacenro);
		
		for(int i = 0; i<listDb.size();i++) {
			PedidoDTO p = new PedidoDTO((int)listDb.get(i)[0],(int)listDb.get(i)[1],(String)listDb.get(i)[2]);
			list.add(p);  
		}
		return list;
	}
	
	public void actualizarPedidoListo(int idPedido) {
		db.executeUpdate(SQL_UPDATE_ESTADO_LISTO, idPedido);
	}
}
