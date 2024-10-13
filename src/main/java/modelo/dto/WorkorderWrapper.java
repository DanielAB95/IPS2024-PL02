package modelo.dto;

public class WorkorderWrapper {
	
	private WorkorderDTO info;
	
	public WorkorderWrapper(WorkorderDTO dto) {
		info = dto;
	}
	
	public WorkorderDTO getInfo() {
		return info;
	}
	
	@Override
	public String toString() {
		return "Workorder ID: " + info.idWorkorder + " - Almacenero ID: " + info.idAlmacenero + " - Pedido ID: " + info.idPedido;
	}

}
