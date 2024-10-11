package modelo.dto;

public class AlmacenDTO {
	private int idProducto;
	private int estanteria;
	private int posicionEstanteria;
	private int pasillo;
	
	
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
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
	public int getPasillo() {
		return pasillo;
	}
	public void setPasillo(int pasillo) {
		this.pasillo = pasillo;
	}
	
	
}
