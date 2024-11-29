package modelo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import giis.demo.util.Database2;
import persistence.dto.PaqueteDto;
import persistence.dto.ProductoDto;
import persistence.dto.Queries;

public class InformePaquetesListosModel {
	
	private Database2 db;
	List<PaqueteDto> paquetes = new ArrayList<>();
	
	public InformePaquetesListosModel(Database2 db) {
		this.db = db;
		findPaquetesListos();
	}
	
	private void findPaquetesListos() {
		List<Object[]> result = db.executeQueryArray(Queries.Paquete.FIND_READY);
		
		for (Object[] o : result) {
			PaqueteDto pq = new PaqueteDto();
			pq.idPaquete = (int)o[0];
			pq.idPedido = (int)o[1];
			pq.paqueteEstado = "Listo";
			pq.fecha = LocalDate.parse((String)o[2]);
			pq.productos = new HashMap<>(getProductosFromPaquete(pq.idPaquete));
			paquetes.add(pq);
		}
	}

	private Map<ProductoDto, Integer> getProductosFromPaquete(int idPaquete) {
		Map<ProductoDto, Integer> resultado = new HashMap<>();
		List<Object[]> productos = db.executeQueryArray(Queries.Paquete.FIND_PRODUCTOS, idPaquete);
		for (Object[] o : productos) {
			ProductoDto dto = new ProductoDto();
			dto.idProducto = (int)o[0];
			resultado.put(dto, (int)o[1]);
			
		}
		return resultado;
	}
	
	public List<PaqueteDto> getPaquetes(){
		return paquetes;
	}
	
	public int getCantidadDeProductos(PaqueteDto paquete) {
		int cantidad = 0;
		for (ProductoDto producto : paquete.productos.keySet()) {
			cantidad += paquete.productos.get(producto);
		}
		return cantidad;
	}

}
