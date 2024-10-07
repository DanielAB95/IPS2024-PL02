package modelo.dto;

import java.util.HashMap;
import java.util.Map;

public class Pedido {

	private Map<Producto, Integer> pedido = new HashMap<>();
	
	public boolean addProduct(Producto id, int units) {
		if (units < 1) throw new IllegalArgumentException();
		pedido.put(id, units);
		return true;
	}
	
	public boolean findProduct(int id) {
		return pedido.containsKey(id);
	}
	
	public int getUnitsFromID(int id) {
		return pedido.get(id);
	}
}
