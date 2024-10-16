package modelo.dto;

public class ProductoAlmacen {
	
	private int idProducto;
	private int cantidad;
	private String descripcion;
	private int pasillo;
	private int estanteria;
	private int posicionEstanteria;
	
	public ProductoAlmacen(int idProducto, int cantidad, String descripcion, int pasillo, int estanteria,
			int posicionEstanteria) {
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.pasillo = pasillo;
		this.estanteria = estanteria;
		this.posicionEstanteria = posicionEstanteria;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public int getPosicionEstanteria() {
		return posicionEstanteria;
	}

	public void setPosicionEstanteria(int posicionEstanteria) {
		this.posicionEstanteria = posicionEstanteria;
	}
	
	
}
