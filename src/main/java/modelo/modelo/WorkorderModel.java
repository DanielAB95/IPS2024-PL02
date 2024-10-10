package modelo.modelo;




import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.Pedido;
import modelo.dto.WorkorderDTO;

public class WorkorderModel {
	private static final String SQL_ADD_WORKORDER = "insert into Workorder (idAlmacenero, idPedido) values (?, ?)";
	private static final String SQL_WORKORDERS = "select * from Workorder";
	private static final String SQL_WORKORDER_ALMACENERO = "select idAlmacenero from Workorder where idWorkorder = ?";
	private static final String SQL_WORKORDER_PEDIDO= "select idAlmacenero from Workorder where idWorkorder = ?";
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
	
	
}
