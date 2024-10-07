package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.PedidoDTO;

public class PedidoModel {
	
	public static final String SQL_LISTA_PEDIDO = "select idPedido, numProductos, fecha  from Pedido where estado = 'Pendiente' order by fecha";
	public static final String SQL = "select * from Pedido";
	public static final String SQL_PEDIDO_ALMACENERO = "select idAlmacenero from Pedido where idPedido = ?";
	
	private Database2 db = new Database2();
	//private List<PedidoDTO> pedidos;
	
	public PedidoModel() {
		//this.pedidos = getPedidos();
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
}
