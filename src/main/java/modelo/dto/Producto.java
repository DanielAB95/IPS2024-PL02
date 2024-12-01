package modelo.dto;

public class Producto {
	
	
	private String nombre, categoria, descripcion;
	private int id;
	private double precio;
	private int pasillo, estanteria, balda;
	private int iva, stock, minStock, stockReposicion;
	private double precioEmpresa;
	
	public Producto(int id, String nombre, String categoria, String descripcion, double precio,int pasillo, int estanteria, int balda,
			double precioEmpresa, int iva, int stock,int  minStock, int stockReposicion) {
		this.id =  id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.precio =  precio;
		this.pasillo = pasillo;
		this.estanteria = estanteria;
		this.balda = balda;
		
		
		this.precioEmpresa = precioEmpresa;
		this.iva = iva;
		this.stock = stock;
		this.minStock = minStock;
		this.stockReposicion = stockReposicion;
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

	public double getPrecioEmpresa() {
		return precioEmpresa;
	}

	public int getIva() {
		return iva;
	}

	public int getStock() {
		return stock;
	}

	public int getMinStock() {
		return minStock;
	}

	public int getStockReposicion() {
		return stockReposicion;
	}
	
	
	
	
}
