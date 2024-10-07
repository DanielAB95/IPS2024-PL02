package almacen;

import giis.demo.util.ApplicationException;
import giis.demo.util.Database2;

public class Service {
	
	private WorkOrder wo;
	private Database2 db = new Database2();
	
	public Service(WorkOrder workOrder) {
		if (workOrder == null) throw new IllegalArgumentException();
		wo = workOrder;
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
