package almacen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WorkOrder {
	
	private List<String> incidencias = new ArrayList<>();
	private List<Pedido> pedidos = new ArrayList<>();
	private boolean incidencia = false;
	
	
	
	public void addIncidencia(String incidenciaStr) {
		if (incidenciaStr == null) throw new IllegalArgumentException();
		if (incidencia) incidencia = true;
		incidencias.add(incidenciaStr);
	}
	
	

}
