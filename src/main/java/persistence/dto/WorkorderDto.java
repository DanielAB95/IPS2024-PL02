package persistence.dto;

import java.util.List;
import java.util.Map;

public class WorkorderDto {
	
	public int idWorkorder;
	public int idAlmacenero;
	public String estado;
	public List<PedidoDto> pedidos; //puede ser solo uno y estar partido
	public Map<ProductoDto, Integer> productos; //si el pedido esta partido estos son esos productos
	public static final int MAXIMO_PRODUCTOS_POR_WORKORDER = 3;

}
