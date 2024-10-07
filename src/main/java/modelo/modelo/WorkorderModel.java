package modelo.modelo;




import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.WorkorderDTO;

public class WorkorderModel {
	private static final String SQL_ADD_WORKORDER = "insert into Workorder (idAlmacenero, idPedido) values (?, ?)";
	private static final String SQL_WORKORDERS = "select * from Workorder";
	private static final String SQL_WORKORDER_ALMACENERO = "select idAlmacenero from Workorder where idWorkorder = ?";
	private static final String SQL_WORKORDER_PEDIDO= "select idAlmacenero from Workorder where idWorkorder = ?";
	private static final String SQL_WORKORDERID = "select idWorkorder from Workorder where idPedido = ? amd idAlmacenero = ?";
	private Database2 db=new Database2();
	private int idAlmacenero;
	private int idPedido;
	private int idWorkorder;
	
	public WorkorderModel() {
		
	}
	
	public void crearWorkorder(int idAlmacenero, int idPedido) {
		this.idAlmacenero = idAlmacenero;
		this.idPedido = idPedido;
		db.executeUpdate(SQL_ADD_WORKORDER, idAlmacenero, idPedido);
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
	
	public int getWorkOrderId() {
		List<Object[]> workorders = db.executeQueryArray(SQL_WORKORDERID, idPedido,idAlmacenero);
		return (int)workorders.get(0)[0];
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
