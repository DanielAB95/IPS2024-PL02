package modelo.dto;

public class PaqueteWrapper {
	
	private int idPaquete;
	private int idWorkorder;
	
	public PaqueteWrapper(int id, int o) {
		this.idPaquete = id;
		this.idWorkorder = o;
	}
	
	public int getIDPaquete() {
		return idPaquete;
	}
	
	public int getIDWO() {
		return idWorkorder;
	}
	
	@Override
	public String toString() {
		return "ID Paquete: " + idPaquete;
	}

}
