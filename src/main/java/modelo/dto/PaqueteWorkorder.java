package modelo.dto;

public class PaqueteWorkorder {
	private int idWorkorder;
	private int idPaquete;
	
	public PaqueteWorkorder(int idWorkorder, int idPaquete) {
		this.idWorkorder = idWorkorder;
		this.idPaquete = idPaquete;
	}

	public int getIdWorkorder() {
		return idWorkorder;
	}

	public void setIdWorkorder(int idWorkorder) {
		this.idWorkorder = idWorkorder;
	}

	public int getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}
	
	
}
