package modelo.dto;


public class WorkorderDTO {
	public int idWorkorder;
	public int idAlmacenero;
	public int idPedido;
	
	public WorkorderDTO(int idWorkorder, int idAlmacenero, int idPedido) {
		this.idWorkorder = idWorkorder;
		this.idAlmacenero = idAlmacenero;
		this.idPedido = idPedido;
	}
	
	public WorkorderDTO() {}
}