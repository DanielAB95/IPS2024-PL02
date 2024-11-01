                                                                                                                                                                                                                                                                                                                                                                                        package modelo.dto;

import java.util.ArrayList;
import java.util.List;

import persistence.dto.ProductoDto;

public class Carrito {
	
	//El Object[] contendra un Producto y su cantidad pedida
	List<Object[]> productosCarrito;
	
	public Carrito() {
		this.productosCarrito = new ArrayList<Object[]>();
	}
	
	
	public void addToCarrito(ProductoDto producto, int cantidad) {
		Object[] result = new Object[2];
		result[0] = producto;
		result[1] = cantidad;
		productosCarrito.add(result);
	}
	
	
	public void removeFromCarrito(String producto) {
		boolean found = false;
		for (int i = 0; i < productosCarrito.size() && !found; i++) {
			if ( ((ProductoDto) productosCarrito.get(i)[0]).getNombre().equals(producto)) {
				productosCarrito.remove(i);
				found = true;
			}
		}
	}
	
	public double getTotal() {
		double res  = 0;
		for (Object[] o: productosCarrito) {
			res += ((ProductoDto)o[0]).getPrecio() * (int)o[1];
		}
		return res;
	}
	
	public boolean isEmpty() {
		if (this.productosCarrito.size() > 0) return false; else return true;
	}
	
	public void printCarrito() {
		System.out.println();
		System.out.println("------------ Estado del Carrito ------------");
		for (int i = 0; i < productosCarrito.size(); i++) {
			System.out.println( ((ProductoDto) productosCarrito.get(i)[0]).getNombre() + ", cantidad: " + productosCarrito.get(i)[1]);
		}
	}
	
	public List<Object[]> getCarrito(){
		return this.productosCarrito;
	}
	
	public void cambiaCantidadCarrito(String nombre, int cantidad) {
		for (Object[] o: productosCarrito) {
			if ( ((ProductoDto)o[0]).getNombre().equals(nombre) ) 
				o[1] = cantidad;
		}
	}

}
