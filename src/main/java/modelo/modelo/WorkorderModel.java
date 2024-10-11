package modelo.modelo;


import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.Pedido;
import modelo.dto.PedidoDTO;
import modelo.dto.Producto;
import modelo.dto.ProductoAlmacen;
import modelo.dto.WorkorderDTO;

public class WorkorderModel {
	private static final String SQL_ADD_WORKORDER = "insert into Workorder (idAlmacenero, idPedido) values (?, ?)";
	private static final String SQL_WORKORDERS = "select * from Workorder";
	private static final String SQL_WORKORDER_ALMACENERO = "select idAlmacenero from Workorder where idWorkorder = ?";
	private static final String SQL_WORKORDER_PEDIDO= "select idAlmacenero from Workorder where idWorkorder = ?";
	private static final String SQL_PRODUCTOSORDENADOS = "select p.id, pp.cantidad, p.descripcion, a.pasillo, a.estanteria, a.posicionEstanteria from PedidoProducto pp "
			+ "join Producto p on pp.idProducto = p.id "
			+ "join Almacen a on p.id = a.idProducto "
			+ "where pp.idPedido = ? "
			+ "order by a.pasillo asc, a.posicionEstanteria asc, a.estanteria ";
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
	
	public List<ProductoAlmacen> getProductos(int idPedido){
		List<ProductoAlmacen> productos = new ArrayList<ProductoAlmacen>();
		List<Object[]> listDb = db.executeQueryArray(SQL_PRODUCTOSORDENADOS, idPedido);
		for(int i = 0; i<listDb.size();i++) {
			ProductoAlmacen p = new ProductoAlmacen((int)listDb.get(i)[0],(int)listDb.get(i)[1],(String)listDb.get(i)[2],(int)listDb.get(i)[3],(int)listDb.get(i)[4],(int)listDb.get(i)[5]);
			productos.add(p);
		}
		return productos;
	}
	
	
}
