package persistence.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PedidoDto {

	public int idPedido;
	public String idCliente; //nif
	public LocalDate fecha;
	public String estadoPedido;
	public Map<ProductoDto, Integer> productos; //mapa producto cantidad
	public List<WorkorderDto> workorders; //puede estar solo en una
	
	
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
	
	@Override
	public int hashCode() {
		return Objects.hash(idPedido);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoDto other = (PedidoDto) obj;
		return idPedido == other.idPedido;
	}
	
}
