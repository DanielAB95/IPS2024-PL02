package modelo.dto;

import java.util.List;

public class WorkorderDTO {
	private int idWorkorder;
	private int idAlmacenero;
	private List<Integer> pedidos;
	
	public WorkorderDTO(int idWorkorder, int idAlmacenero, List<Integer> idPedido) {
		this.idWorkorder = idWorkorder;
		this.idAlmacenero = idAlmacenero;
		this.pedidos = idPedido;
	}
	
	public int getIdAlmacenero() {
		return idAlmacenero;
	}

	public void setIdAlmacenero(int idAlmacenero) {
		this.idAlmacenero = idAlmacenero;
	}

	public List<Integer> getIdPedido() {
		return pedidos;
	}

	public void setIdPedido(List<Integer> idPedido) {
		pedidos = List.copyOf(idPedido);
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
