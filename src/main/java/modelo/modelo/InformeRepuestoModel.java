package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.ProductoDto;

public class InformeRepuestoModel {
	private Database2 db;
	private final static String FIND_PRODUCTOS_REPONER = "select id, nombre, descripcion, stock, stockReposicion from Producto where stock < minStock";
	
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
		List<Object[]> result = db.executeQueryArray(FIND_PRODUCTOS_REPONER);
		
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
