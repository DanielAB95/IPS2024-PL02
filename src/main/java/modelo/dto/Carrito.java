                                                                                                                                                                                                                                                                                                                                                                                        package modelo.dto;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	
	//El Object[] contendra un Producto y su cantidad pedida
	List<Object[]> productosCarrito;
	
	public Carrito() {
		this.productosCarrito = new ArrayList<Object[]>();
	}
	
	
	public void addToCarrito(Producto producto, int cantidad) {
		Object[] result = new Object[2];
		result[0] = producto;
		result[1] = cantidad;
		productosCarrito.add(result);
	}
	
	
	public void printCarrito() {
		System.out.println();
		System.out.println("------------ Estado del Carrito ------------");
		for (int i = 0; i < productosCarrito.size(); i++) {
			System.out.println( ((Producto) productosCarrito.get(i)[0]).getNombre() + ", cantidad: " + productosCarrito.get(i)[1]);
		}
	}

}
