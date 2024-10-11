package modelo.modelo;




import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.Pedido;
import modelo.dto.PedidoDTO;
import modelo.dto.Producto;
import modelo.dto.WorkorderDTO;

public class WorkorderModel {
	private static final String SQL_ADD_WORKORDER = "insert into Workorder (idAlmacenero, idPedido) values (?, ?)";
	private static final String SQL_WORKORDERS = "select * from Workorder";
	private static final String SQL_WORKORDER_ALMACENERO = "select idAlmacenero from Workorder where idWorkorder = ?";
	private static final String SQL_WORKORDER_PEDIDO= "select idAlmacenero from Workorder where idWorkorder = ?";
	private static final String SQL_PRODUCTOSORDENADOS = "SELECT p.id, a.estanteria, a.posicionEstanteria, a.pasillo, p.descripcion\r\n"
			+ "FROM PedidoProducto pp\r\n"
			+ "JOIN Producto p ON pp.idProducto = p.id\r\n"
			+ "JOIN Almacen a ON p.id = a.idProducto\r\n"
			+ "WHERE pp.idPedido = ?\r\n"
			+ "ORDER BY a.pasillo ASC, a.posicionEstanteria ASC, a.estanteria";
	private Database2 db=new Database2();
	private int idWorkorder;
	private List<String> incidencias = new ArrayList<>();
	private List<Integer> pedidos = new ArrayList<>();
	private boolean incidencia = false;
	
	public void addIncidencia(String incidenciaStr) {
		if (incidenciaStr == null) throw new IllegalArgumentException();
		if (!incidencia) incidencia = true;
		incidencias.add(incidenciaStr);
	}
	
	public void crearWorkorder(int idAlmacenero, int idPedido) {
		addPedido(idPedido);
		db.executeUpdate(SQL_ADD_WORKORDER, idAlmacenero, idPedido);
	}
	
	public void addPedidos(List<Integer> pedidosIn) {
		if (pedidosIn == null) throw new IllegalArgumentException();
		for (int p : pedidosIn) {
			pedidos.add(p);
		}
	}
	
	public void addPedido(Integer pedido) {
		if (pedido == null) throw new IllegalArgumentException();
		pedidos.add(pedido);
	}
	
	public List<WorkorderDTO> getWorkorders(){
		List<WorkorderDTO> list = new ArrayList<WorkorderDTO>();
		List<Object[]> workorders = db.executeQueryArray(SQL_WORKORDERS);
		
		for (int i = 0; i < workorders.size(); i++) {
			WorkorderDTO w = new WorkorderDTO((int)workorders.get(i)[0], (int)workorders.get(i)[1], (int)workorders.get(i)[2]);
			list.add(w);	
		}
		return list;
	}
	
	public int getWorkOrderAlmacenero() {
		List<Object[]> workorders = db.executeQueryArray(SQL_WORKORDER_ALMACENERO, idWorkorder);
		return (int)workorders.get(0)[0];
	}
	 
	public int getWorkOrderPedido(String idWorkorder) {
		List<Object[]> workorders = db.executeQueryArray(SQL_WORKORDER_PEDIDO,idWorkorder);
		return (int)workorders.get(0)[0];
	}
	
	public List<Producto> getProductos(int idPedido){
		List<Producto> productos = new ArrayList<Producto>();
		List<Object[]> listDb = db.executeQueryArray(SQL_PRODUCTOSORDENADOS, idPedido);
		for(int i = 0; i<listDb.size();i++) {
			Producto p = new Producto((int)listDb.get(i)[0],(String)listDb.get(i)[1],(String)listDb.get(i)[2],(String)listDb.get(i)[3],(double)listDb.get(i)[4]);
			productos.add(p);
		}
		return productos;
	}
	
	
}
