package modelo.dto;

public class WorkorderDTO {
	private int idWorkorder;
	private int idAlmacenero;
	private int idPedido;
	
	public WorkorderDTO(int idWorkorder, int idAlmacenero, int idPedido) {
		this.idWorkorder = idWorkorder;
		this.idAlmacenero = idAlmacenero;
		this.idPedido = idPedido;
	}
	
	public int getIdAlmacenero() {
		return idAlmacenero;
	}

	public void setIdAlmacenero(int idAlmacenero) {
		this.idAlmacenero = idAlmacenero;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public WorkorderDTO(int idWorkorer) {
		this.idWorkorder = idWorkorer;
	}

	public int getIdWorkorder() {
		return idWorkorder;
	}

	public void setIdWorkorder(int idWorkorder) {
		this.idWorkorder = idWorkorder;
	}
	
	
	
}
