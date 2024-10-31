package persistence.dto;

import java.util.Map;

public class PaqueteDto {
	
	public int idPaquete;
	public int idPedido;
	public String paqueteEstado;
	public Map<ProductoDto, Integer> productos;
	
	public String productosToString() {
		StringBuilder sb = new StringBuilder();
		for (ProductoDto producto : productos.keySet()) {
			sb.append("\t");
			sb.append(producto.nombre + " - ");
			sb.append(producto.precio + "€ - x ");
			sb.append(productos.get(producto) + "\n");
		}
		return sb.toString();
	}

}
