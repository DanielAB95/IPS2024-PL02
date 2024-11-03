package modelo.dto;

public class Producto {
	
	
	private String nombre, categoria, descripcion;
	private int id;
	private double precio;
	private int pasillo, estanteria, balda;
	
	public Producto(int id, String nombre, String categoria, String descripcion, double precio,int pasillo, int estanteria, int balda) {
		this.id =  id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.precio =  precio;
		this.pasillo = pasillo;
		this.estanteria = estanteria;
		this.balda = balda;
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
	
	@Override
	public String toString() {
		return nombre + " - " + id;
	}
	
	public int getPasillo() {
		return pasillo;
	}
	public void setPasillo(int pasillo) {
		this.pasillo = pasillo;
	}
	public int getEstanteria() {
		return estanteria;
	}
	public void setEstanteria(int estanteria) {
		this.estanteria = estanteria;
	}
	public int getBalda() {
		return balda;
	}
	public void setBalda(int balda) {
		this.balda = balda;
	}
	
	
}
