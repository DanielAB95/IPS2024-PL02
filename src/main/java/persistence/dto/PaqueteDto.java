package persistence.dto;

import java.util.Map;

public class PaqueteDto {
	
	public int idPaquete;
	public int idPedido;
	public String paqueteEstado;
	public Map<ProductoDto, Integer> productos;

}
