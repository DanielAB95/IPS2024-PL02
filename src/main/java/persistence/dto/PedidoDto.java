package persistence.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PedidoDto {

	public int idPedido;
	public String idCliente; //nif
	public LocalDate fecha;
	public String estadoPedido;
	public Map<ProductoDto, Integer> productos; //mapa producto cantidad
	public List<WorkorderDto> workorders; //puede estar solo en una
	
}
