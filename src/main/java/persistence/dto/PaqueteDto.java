package persistence.dto;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

public class PaqueteDto {
	
	public int idPaquete;
	public int idPedido;
	public LocalDate fecha;
	public String paqueteEstado;
	public LocalDate fecha;
	
	@Override
	public int hashCode() {
		return Objects.hash(idPaquete);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaqueteDto other = (PaqueteDto) obj;
		return idPaquete == other.idPaquete;
	}

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
