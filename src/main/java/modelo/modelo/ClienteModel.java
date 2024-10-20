package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.demo.util.Database2;
import modelo.dto.Carrito;
import modelo.dto.ClienteDTO;
import modelo.dto.Producto;

public class ClienteModel {
	
	public static final String SQL_LISTA_PRODUCTO = "select * from producto";
	
	private Database2 db;
	private List<Producto> productosPosibles;
	private ClienteDTO dto;
	private Carrito carrito;
	
	public ClienteModel(Database2 db, ClienteDTO dto, Carrito c) {
		this.db = db;
		this.productosPosibles = getProductos();
		this.dto = dto;
		this.carrito = c;
	}
	
	public boolean checkProductoYaEnCarrito(String nombre) {
		List<Object[]> productos = carrito.getCarrito();
		for (Object[] o: productos) {
			if ( ((Producto)o[0]).getNombre().equals(nombre)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Database2 getDatabase() {
		return this.db;
	}
	
	public Carrito getCarrito() {
		return this.carrito;
	}
	
	public List<Producto> getProductos() {
		List<Producto> resultado = new ArrayList<Producto>();
		List<Object[]> productos = db.executeQueryArray(SQL_LISTA_PRODUCTO); 
		
		for (int i = 0; i < productos.size(); i++) {
			Producto p = new Producto(productos.get(i)[0], productos.get(i)[1], productos.get(i)[2], productos.get(i)[3], productos.get(i)[4]);
			resultado.add(p);
		}
		
		return resultado;
	}
	
	public void rellenaTablaProductos(DefaultTableModel modelo) {
		
		for (int i = 0; i <  productosPosibles.size(); i++) {
			Object[] fila = {productosPosibles.get(i).getNombre(), productosPosibles.get(i).getPrecio(), productosPosibles.get(i).getDescripcion()};
			modelo.addRow(fila);
		}
	}
	
	public void rellenaComboCantidad(JComboBox<Integer> combo) { 
		
		for (int i = 1; i <= 100; i++) {
			combo.addItem(i);
		}
	}
	
	public String getPrecioPorNombre(String nombre, int cantidad) {
		double precio = 0;
		for (Producto p: this.productosPosibles) {
			if (p.getNombre().equals(nombre)) precio = p.getPrecio() * cantidad;
		}
		String precioFormateado = String.format("%.2f", precio); //que tenga solo 2 decimales
		return precioFormateado;
	}
	
	public String getCategoriaPorNombre(String nombre) {
		for (Producto p: this.productosPosibles) {
			if (p.getNombre().equals(nombre)) return p.getCategoria();
		}
		return "";
	}
	
	public String getDescripcionPorNombre(String nombre) {
		for (Producto p: this.productosPosibles) {
			if (p.getNombre().equals(nombre)) return p.getDescripcion();
		}
		return "";
	}

	public Producto getProductoPorNombre(String nombre) {
		for (Producto p: this.productosPosibles) {
			if (p.getNombre().equals(nombre)) return p;
		}
		return null;
	}

	public ClienteDTO getDto() {
		return dto;
	}
	
}
