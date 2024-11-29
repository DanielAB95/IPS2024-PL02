package modelo.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.demo.util.Database2;
import modelo.dto.Carrito;
import modelo.dto.ClienteDTO;
import modelo.dto.Producto;

import vista.AppInicioView;
import vista.CarritoView;

public class CarritoModel {
	
	//wer
	private static final String SQL_GET_PEDIDOs_Producto = "select * from pedidoproducto";
	private static final String SQL_CREA_CLIENTE_NUEVO = "INSERT INTO Cliente (idCliente, nombreUsuario, nombre, telefono, pais, region, ciudad, calle, tipoCliente) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_GET_CLIENTE = "select * from cliente where nombreUsuario = ?";
	private static final String SQL_INSERTAR_PEDIDO = "insert into Pedido(idPedido, idCliente, fecha, estado) values (?, ?, ?, ?)";
	private static final String SQL_INSERTAR_PRODUCTOS_PEDIDO = "insert into PedidoProducto(idPedido, idProducto, cantidad) values (?, ?, ?)";
	public static final String SQL_LISTA_PRODUCTO = "select * from producto";
	private static final String SQL_GET_PEDIDOS = "select * from pedido";
	//private static final String SQL_GET_CLIENTES = "select * from cliente";
	private static final String SQL_GET_CARRITO_FROM_CLIENTE = "select * from carrito where id_cliente = ?";
	private static final String SQL_GET_PRODUCTO_CARRITO = "select * from producto_carrito";
	private static final String SQL_REDUCE_STOCK_PRODUCTO = "UPDATE producto SET stock = ? WHERE id = ?";
	
	private Carrito carrito;
	private Database2 db;
	private CarritoView v;
	private ClienteDTO dto;
	private List<Producto> productosPosibles;
	
	public CarritoModel (Carrito c, CarritoView v, Database2 db, ClienteDTO dto) {
		this.carrito = c;
		this.v = v;
		this.db = db;
		this.dto = dto;
		this.productosPosibles = getProductos();
	}
	
	private List<Producto> getProductos() {
		List<Producto> resultado = new ArrayList<Producto>();
		List<Object[]> productos = db.executeQueryArray(SQL_LISTA_PRODUCTO); 
		
		for (int i = 0; i < productos.size(); i++) {
			Producto p = new Producto((int)productos.get(i)[0], (String)productos.get(i)[1], 
					(String)productos.get(i)[2], (String)productos.get(i)[3], (double)productos.get(i)[4], 
					(int)productos.get(i)[5],(int)productos.get(i)[6],(int)productos.get(i)[7],
					(int)productos.get(i)[8],(int)productos.get(i)[9],(int)productos.get(i)[10],
					(int)productos.get(i)[11],(int)productos.get(i)[12]);
			
			resultado.add(p);
		}
		
		return resultado;
	}

	public Database2 getDatabase() {
		return this.db;
	}
	
	
	private String getClientIDfromName(String name) {
		List<Object[]> usuario = db.executeQueryArray(SQL_GET_CLIENTE, name);
		
		return (String) usuario.get(0)[0];
		
	}
	
	public Object[] getClient(String name) {
		List<Object[]> usuario = db.executeQueryArray(SQL_GET_CLIENTE, name);
		
		return usuario.get(0);
		
	}
	
	public boolean doesClientExist(String name) {
		List<Object[]> usuario = db.executeQueryArray(SQL_GET_CLIENTE, name);
		
		if (usuario.isEmpty())
			return false;
		
		return true;
		
	}
	
	public void createNewClient(String[] clientData) {
		
		db.executeUpdate(SQL_CREA_CLIENTE_NUEVO, clientData[0], clientData[1],
				clientData[2],clientData[3],clientData[4],clientData[5],clientData[6], clientData[7], clientData[8]);
		
	}
	
	public void confirmarPedido() {
		if (checkHayProductos()) {
			System.out.println("-- ANTES de confirmar compra -- ");
			mostrarPedidos();
			
			int nuevoID = getNuevoID();
			//int numeroProductos = carrito.getCarrito().size(); ya no se utiliza
			String fecha = getFechaDeHoy();
			String estado = "Pendiente";
			
			db.executeUpdate(SQL_INSERTAR_PEDIDO, nuevoID, getClientIDfromName(dto.getName()), fecha, estado);
				
			insertarProductosPedido(nuevoID);
			
			reduceStockProductos();
			
			//JOptionPane.showMessageDialog(this.v, "Gracias por ");
			
			System.out.println("-- DESPUES de confirmar compra --");
			mostrarPedidos();
			
			//--------------------------------
			// método limpiarCarritoUsuario
			//--------------------------------
			
			
			//vuelvo al inicio
			AppInicioView vista = new AppInicioView(getDatabase());
			vista.setVisible(true);
			this.v.dispose();
			
		} else {
			JOptionPane.showMessageDialog(this.v, "No hay productos en su carrito");
			//vuelvo al inicio
			AppInicioView vista = new AppInicioView(getDatabase());
			this.v.dispose();
			vista.setVisible(true);
		}
	}
	
	

	private void reduceStockProductos() {
		//SQL_REDUCE_STOCK_PRODUCTO   UPDATE producto SET stock = ? WHERE id_producto = ? 
		
		List<Object[]> productosCarrito = this.carrito.getCarrito();
		
		for (Object[] o: productosCarrito) {
			
			int oldStock = (int)((Producto) o[0]).getStock();
			int cantidadPedido = (int) o[1];
			
			
			db.executeUpdate(SQL_REDUCE_STOCK_PRODUCTO, oldStock - cantidadPedido, ((Producto) o[0]).getId());
		}
		
	}

	private boolean checkHayProductos() {
		List<Object[]> productosCarrito = this.carrito.getCarrito();
		if (productosCarrito.size() <= 0) {
			return false;
		}
		return true;
	}

	private void insertarProductosPedido(Integer pedidoID) {
		List<Object[]> productosCarrito = this.carrito.getCarrito();
		
		for (Object[] o: productosCarrito) {
			db.executeUpdate(SQL_INSERTAR_PRODUCTOS_PEDIDO, pedidoID, ((Producto) o[0]).getId(), o[1]);
		}
	}


	private String getFechaDeHoy() {

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formato);
		
        return fechaFormateada;  
	}

	
	private int getNuevoID() {
		Random random = new Random();
		boolean encontrado = false;
		int id = -1;
		
		while (!encontrado) {
			id = random.nextInt(1000000) + 1; 
			if (!existeID(id)) encontrado = true;
		} 
		
		return id;
	}
	
	private boolean existeID(int id) {
		List<Object[]> pedidos = db.executeQueryArray(SQL_GET_PEDIDOS);
		for (int i = 0; i < pedidos.size(); i++) {
			if ((Integer)pedidos.get(i)[0] == id) return true;
		}
		return false;
	}
	
	
	public  String[] rellenaListaProductos() {
		List<Object[]> productosCarrito = this.carrito.getCarrito();
		int dimension = productosCarrito.size();
		String[] result = new String[dimension];
		
		for (int i = 0; i < dimension; i++) {
			result[i] = ((Producto) productosCarrito.get(i)[0]).getNombre();
		}
		return result;
	}
	
	public void añadeProductosListModel(DefaultTableModel tabla) {
		List<Object[]> productosCarrito = this.carrito.getCarrito();
		for (Object[] o: productosCarrito) {
			//nombre producto, cantidad Producto, precio producto
			Object[] fila = { ((Producto) o[0]).getNombre(),  o[1],  ((Producto) o[0]).getPrecio() * (int)o[1] };
			tabla.addRow(fila);
		}
	}
	
	public String calcularPrecioTotal() {
		double resultado = 0;
		List<Object[]> productosCarrito = this.carrito.getCarrito();
		
		for (int i = 0; i < productosCarrito.size(); i++) {
			resultado += ((Producto) productosCarrito.get(i)[0]).getPrecio() * ((Integer) productosCarrito.get(i)[1]);
		}
		return String.format("%.2f", resultado);
	}
	
	public Integer getCantidadProductoPorNombre(String nombre) {
		List<Object[]> productosCarrito = this.carrito.getCarrito();
		
		for (int i = 0; i < productosCarrito.size(); i++) {
			if ( ((Producto) productosCarrito.get(i)[0]).getNombre().equals(nombre) ) return ((Integer) productosCarrito.get(i)[1]);
		}
		return 0;
	}
	
	public String getPrecioProductoPorNombre(String nombre) {
		List<Object[]> productosCarrito = this.carrito.getCarrito();
		for (int i = 0; i < productosCarrito.size(); i++) {
			if ( ((Producto) productosCarrito.get(i)[0]).getNombre().equals(nombre) ) {
				//return ((Integer) productosCarrito.get(i)[1]) *  ((Producto) productosCarrito.get(i)[0]).getPrecio();
				return String.format("%.2f", ((Integer) productosCarrito.get(i)[1]) *  ((Producto) productosCarrito.get(i)[0]).getPrecio());
			}
		}
		return "";
	}
	
	
	public void modificarCantidadProductoPorNombre(String nombre, int cantidadNueva) {
		if (cantidadNueva > 0) {
			
			List<Object[]> productosCarrito = this.carrito.getCarrito();
			boolean encontrado = false;
			
			for (int i = 0; i < productosCarrito.size() && !encontrado; i++) {
				if ( ((Producto) productosCarrito.get(i)[0]).getNombre().equals(nombre) ) {
					productosCarrito.get(i)[1] = cantidadNueva;
					encontrado = true;
				}
			}
		} else {
			mensajeInputErroneo();
		}
	}
	
	public void mensajeInputErroneo() {
		JOptionPane.showMessageDialog(this.v, "Por favor, Introduzca una cantidad válida.");
	}
	
	public boolean esNumeroEntero(String texto) {
	    try {
	        Integer.parseInt(texto); 
	        return true; 
	    } catch (NumberFormatException e) {
	        return false; 
	    }
	}

	public void borrarProductoCarrito(String nombre) {
		if (nombre != null) {
			carrito.removeFromCarrito(nombre);
		} else {
			JOptionPane.showMessageDialog(this.v, "Por favor, Seleccione el producto a eliminar.");
		}
	}
	
	
	
	// <------------------------ PRUEBA --------------------------->
	public void printCarrito() {
		List<Object[]> productosCarrito = this.carrito.getCarrito();
		System.out.println();
		System.out.println("------------ Estado del Carrito ------------");
		for (int i = 0; i < productosCarrito.size(); i++) {
			System.out.println( ((Producto) productosCarrito.get(i)[0]).getNombre() + ", cantidad: " + productosCarrito.get(i)[1]);
		}
	}
	
	private void mostrarPedidos() {
		System.out.println("<------------------  INICIO  ------------------->");
		List<Object[]> pedidos = db.executeQueryArray(SQL_GET_PEDIDOS);
		
		for (Object[] p: pedidos) {
			System.out.println("Pedido: " + p[0] + " "+ p[1] + " "+ p[2] + " "+ p[3]);
		}
		System.out.println();
		
		
		List<Object[]> productos = db.executeQueryArray(SQL_GET_PEDIDOs_Producto);
		
		for (Object[] p: productos) {
			System.out.println("PedidoProducto: " + p[0] + " "+ p[1] + " "+ p[2]);
		}
		System.out.println("<-----------------  FIN  -------------------->");
	}

	public ClienteDTO getDto() {
		return dto;
	}

	public String getPrecioPorNombre(String nombre, int cantidad) {
		double precio = 0;
		for (Producto p: this.productosPosibles) {
			if (p.getNombre().equals(nombre)) precio = p.getPrecio() * cantidad;
		}
		String precioFormateado = String.format("%.2f", precio); //que tenga solo 2 decimales
		return precioFormateado;
	}

	public void printProductoCarrito() {
		List<Object[]> carrito = db.executeQueryArray(SQL_GET_PRODUCTO_CARRITO);
		
		for (int i = 0; i < carrito.size(); i++) {
			for (int j = 0; j < carrito.get(i).length; j++) {
				System.out.print(carrito.get(i)[j] + " ");
			}
			System.out.println();
		}
	}

	public void modificarCantidadCarrito(String nombreProducto, int nuevaCantidad, String nombreUsuario) {
		String idCliente = getClientIDfromName(nombreUsuario);
		String idCarrito = getIdCarritoFromCliente(idCliente);
		int idProducto = getIdProductoFromNombre(nombreProducto);
		
		db.executeUpdate("UPDATE producto_carrito SET cantidad = ? WHERE id_producto = ? AND id_carrito = ?", 
				nuevaCantidad, idProducto, idCarrito);
		
		System.out.println();
		printProductoCarrito();
		
	}

	private int getIdProductoFromNombre(String nombreProducto) {
		List<Object[]> producto = db.executeQueryArray( "select * from producto where nombre = ?", nombreProducto);
		return (int) producto.get(0)[0];
	}

	public void eliminaProductoCarrito(String nombreProducto, String nombreUsuario) {
		String idCliente = getClientIDfromName(nombreUsuario);
		
		String idCarrito = getIdCarritoFromCliente(idCliente);
		int idProducto = getIdProductoFromNombre(nombreProducto);
		
		db.executeUpdate("DELETE FROM producto_carrito WHERE id_producto = ? AND id_carrito = ?", 
				idProducto, idCarrito);
		
		System.out.println();
		printProductoCarrito();
	}
	
	private String getIdCarritoFromCliente(String idCliente) {
		
		List<Object[]> carrito = db.executeQueryArray(SQL_GET_CARRITO_FROM_CLIENTE, idCliente);
		
		return (String) carrito.get(0)[1];
	}

	public void borraCarritoCliente(String nombreUsuario) {
		
		String idCliente = getClientIDfromName(nombreUsuario);
		String idCarrito = getIdCarritoFromCliente(idCliente);
		db.executeUpdate("DELETE FROM producto_carrito WHERE id_carrito = ?", idCarrito);
		
		System.out.println();
		printProductoCarrito();
	}
	
	
	//checkea si se compra mas cantidad de la que hay en stock de algun producto del carrito
	public boolean checkStockCarrito() {
		List<Object[]> productosCarrito = this.carrito.getCarrito();
		
		for (Object[] o: productosCarrito) {
			
			if ( ((Producto) o[0]).getStock() < (int) o[1]) {
				
				JOptionPane.showMessageDialog(v, "Del producto: " + ((Producto) o[0]).getNombre() + " quedan " + ((Producto) o[0]).getStock() + " unidades, \nPor favor, modifique la cantidad.");
				return false;
			}
			
		}
		return true;
	}
}
