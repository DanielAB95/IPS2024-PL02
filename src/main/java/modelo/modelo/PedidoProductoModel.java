package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.PedidoProductoDTO;

public class PedidoProductoModel {
	private Database2 db = new Database2();
	private static final String SQL_PRODUCTOS_PEDIDO = "select pp.idPedido, pp.idProducto, pp.cantidad, p.descripcion from PedidoProducto pp join Producto p ON pp.idProducto = p.id where pp.idPedido = ?";
	
	public List<PedidoProductoDTO> getProductosPorPedido(int idPedido){
		List<PedidoProductoDTO> list = new ArrayList<PedidoProductoDTO>();
		List<Object[]> listDb = db.executeQueryArray(SQL_PRODUCTOS_PEDIDO, idPedido);
		
		for(int i = 0; i<listDb.size();i++) {
			PedidoProductoDTO p = new PedidoProductoDTO((int)listDb.get(i)[0],(int)listDb.get(i)[1],(int)listDb.get(i)[2],(String)listDb.get(i)[3]);
			list.add(p);
		}
		return list;
	}
}
