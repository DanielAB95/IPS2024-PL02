package modelo.dto;

public class ProductoWrapper {
	
	private Producto producto;
	private int cantidad;
	
	public ProductoWrapper(Producto p, int cant) {
		producto = p;
		cantidad = cant;
	}
	
	public int getID() {
		return producto.getId();
	}
	public int getUnits() {
		return cantidad;
	}
	
	@Override
	public String toString() {
		return producto.getNombre() + " - id: " + producto.getId() + " - cant: " + cantidad;
	}

}
