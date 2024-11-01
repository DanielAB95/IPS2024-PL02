package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import giis.demo.util.Database2;
import modelo.dto.Carrito;
import modelo.dto.ClienteDTO;
import modelo.dto.Producto;
import persistence.dto.CategoriaDto;
import persistence.dto.ProductoDto;

public class ClienteModel {

	public static final String SQL_SUBCATEGORIA = "select * from Categoria where categoriaPadre = ?";
	public static final String SQL_SUBCATEGORIA_SIN_PADRE = "select * from Categoria where categoriaPadre is NULL";
	public static final String SQL_CATEGORIA = "select * from Categoria where nombreCategoria = ?";
	public static final String SQL_LISTA_PRODUCTOS = "select * from Producto p inner join Categoria c on p.categoria = c.nombreCategoria and c.nombreCategoria = ?";
	public static final String SQL_PRODUCTO = "select * from Producto where nombre = ?";
	
	private Database2 db;
	private Carrito carrito;

	public ClienteModel(Database2 db, Carrito carrito) {
		this.db = db;
		this.carrito = carrito;
	}

	public boolean checkProductoYaEnCarrito(String nombre) {
		List<Object[]> productos = carrito.getCarrito();
		for (Object[] o : productos) {
			if (((ProductoDto) o[0]).getNombre().equals(nombre)) {
				return true;
			}
		}

		return false;
	}

	public Database2 getDatabase() {
		return this.db;
	}

	public List<CategoriaDto> getSubCategoria(String categoria) {
		List<CategoriaDto> list = new ArrayList<CategoriaDto>();
		List<Object[]> listDb = db.executeQueryArray(SQL_SUBCATEGORIA,categoria);
		for (int i = 0; i < listDb.size(); i++) {
			CategoriaDto c = new CategoriaDto((String) listDb.get(i)[0], (String) listDb.get(i)[1]);
			list.add(c);
		}
		return list;
	}

	public List<ProductoDto> getProductos(String categoria) {
		List<ProductoDto> list = new ArrayList<ProductoDto>();
		List<Object[]> listDb = db.executeQueryArray(SQL_LISTA_PRODUCTOS, categoria);
		for (int i = 0; i < listDb.size(); i++) {
			ProductoDto p = new ProductoDto((int) listDb.get(i)[0], (String) listDb.get(i)[1],
					(String) listDb.get(i)[2], (String) listDb.get(i)[3], (double) listDb.get(i)[4],
					(int) listDb.get(i)[5], (int) listDb.get(i)[6], (int) listDb.get(i)[7]);
			list.add(p);
		}
		return list;
	}
	
	public List<CategoriaDto> getCategoriasPrincipales() {
		List<CategoriaDto> list = new ArrayList<CategoriaDto>();
		List<Object[]> listDb = db.executeQueryArray(SQL_SUBCATEGORIA_SIN_PADRE);
		for (int i = 0; i < listDb.size(); i++) {
			CategoriaDto c = new CategoriaDto((String) listDb.get(i)[0], (String) listDb.get(i)[1]);
			list.add(c);
		}
		return list;
	}
	
	public CategoriaDto getCategoria(String categoria) {
		List<Object[]> listDb = db.executeQueryArray(SQL_CATEGORIA, categoria);
		CategoriaDto c = new CategoriaDto((String) listDb.get(0)[0], (String) listDb.get(0)[1]);
		return c;
	}
	
	public ProductoDto getProducto(String nombre) {
		List<Object[]> listDb = db.executeQueryArray(SQL_PRODUCTO, nombre);
		if(listDb.isEmpty()) {
			return null;
		}
		ProductoDto p = new ProductoDto((int) listDb.get(0)[0], (String) listDb.get(0)[1],
				(String) listDb.get(0)[2], (String) listDb.get(0)[3], (double) listDb.get(0)[4],
				(int) listDb.get(0)[5], (int) listDb.get(0)[6], (int) listDb.get(0)[7]);
		return p;
	}
	
	public Carrito getCarrito() {
		return this.carrito;
	}

}
