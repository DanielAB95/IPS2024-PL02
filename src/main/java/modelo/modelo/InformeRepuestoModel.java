package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.ProductoDto;
import persistence.dto.Queries;

public class InformeRepuestoModel {
	private Database2 db;
	
	public Database2 getDb() {
		return this.db;
	}
	
	public InformeRepuestoModel(Database2 db) {
		this.db = db;
		productosReponer();
	}
	
	public InformeRepuestoModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
		productosReponer();
	}
	
	
	public List<ProductoDto> productosReponer(){
		List<ProductoDto> productos = new ArrayList<>();
		List<Object[]> result = db.executeQueryArray(Queries.Producto.FIND_PRODUCTOS_REPONER);
		
		for(Object[] o : result) {
			ProductoDto dto = new ProductoDto();
			dto.idProducto = (int)o[0];
			dto.nombre = (String)o[1];
			dto.descripcion = (String)o[2];		
			dto.stock = (int)o[3];
			dto.stockReposicion = (int)o[4];
			productos.add(dto);
		}
		return productos;
	}

}
