package modelo.modelo;

import giis.demo.util.Database2;
import modelo.dto.WorkorderDTO;

public class RecogidaModel {
	
	private WorkorderDTO dto;
	private Database2 db = new Database2();
	private final static String SQL = "select idWorkorder, idPedido from Workorders where idWorkorder = ?";
	
	public RecogidaModel(WorkorderDTO workOrder) {
		if (workOrder == null) throw new IllegalArgumentException();
		dto = workOrder;
	}
	
	public RecogidaModel() {
		extractWorkorder();
	}
	
	private void extractWorkorder() {
		dto = (WorkorderDTO) db.executeQueryPojo(WorkorderDTO.class, SQL, "1");
	}
	
	public void checkProduct(int id, int units) {
		checkID(id);
		checkUnits(id, units);
	}

	private void checkUnits(int id, int units) {
		//if (units != wo.getUnitsFromID(id)) throw new ApplicationException("La cantidad de unidades no concuerda");
		
	}

	private void checkID(int id) {
		//if (!wo.findProduct(id)) throw new ApplicationException("Producto no encontrado en el pedido");
	}

}
