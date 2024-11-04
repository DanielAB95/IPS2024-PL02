package persistence.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkorderDto {
	
	public int idWorkorder;
	public int idAlmacenero;
	public String estado;
	public List<PedidoDto> pedidos = new ArrayList<>(); //puede ser solo uno y estar partido
	@Override
	public int hashCode() {
		return Objects.hash(idWorkorder);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkorderDto other = (WorkorderDto) obj;
		return idWorkorder == other.idWorkorder;
	}
	public static final int MAXIMO_PRODUCTOS_POR_WORKORDER = 3;

}
