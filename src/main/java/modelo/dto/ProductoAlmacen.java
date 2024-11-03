package modelo.dto;

public class ProductoAlmacen {
	
	private int idProducto;
	private int cantidad;
	private String descripcion;
	private int pasillo;
	private int estanteria;
	private int balda;
	
	public ProductoAlmacen(int idProducto, int cantidad, String descripcion, int pasillo, int estanteria,
			int balda) {
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
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

	public int getBalda() {
		return balda;
	}

	public void setBalda(int balda) {
		this.balda = balda;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("IdProducto: ");
		sb.append(getIdProducto());
		sb.append("\n");
		sb.append(" Cantidad: ");
		sb.append(getCantidad());
		sb.append("\n");
		sb.append("Descripcion: ");
		sb.append(getDescripcion());
		sb.append("\n");
		sb.append("Ubicacion: ");
		sb.append("\n");
		sb.append("\t");
		sb.append("pasillo: ");
		sb.append(getPasillo());
		sb.append("\n");
		sb.append("\t");
		sb.append(" estanteria: ");
		sb.append(getEstanteria());
		sb.append("\n");
		sb.append("\t");
		sb.append(" posicion en estanteria: ");
		sb.append(getBalda());
		return sb.toString();
		
	}
	
}
