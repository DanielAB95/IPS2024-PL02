package modelo.dto;

public class Producto {
	
	
	private String nombre, categoria, descripcion;
	private int id;
	private double precio;
	
	public Producto(Object id, Object nombre, Object categoria, Object descripcion, Object precio) {
		this.id = (int) id;
		this.nombre = (String) nombre;
		this.categoria = (String) categoria;
		this.descripcion = (String) descripcion;
		this.precio = (double) precio;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCategoria() {
		return categoria;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public int getId() {
		return id;
	}

	public double getPrecio() {
		return precio;
	}
}
