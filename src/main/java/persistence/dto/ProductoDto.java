package persistence.dto;

public class ProductoDto {
	
	public int idProducto;
	public String nombre;
	public String categoria;
	public String descripcion;
	public double precio;
	public int pasillo, estanteria, balda; //(x,y,z) almacen
	public ProductoDto(int idProducto, String nombre, String categoria, String descripcion, double precio, int pasillo,
			int estanteria, int balda) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.precio = precio;
		this.pasillo = pasillo;
		this.estanteria = estanteria;
		this.balda = balda;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
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

