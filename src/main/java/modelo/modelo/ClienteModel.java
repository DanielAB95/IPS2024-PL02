package modelo.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import giis.demo.util.Database2;
import modelo.dto.Carrito;

import modelo.dto.Producto;
import persistence.dto.CategoriaDto;
import vista.AppInicioView;
import vista.ClienteView;

public class ClienteModel {

	public static final String SQL_SUBCATEGORIA = "select * from Categoria where categoriaPadre = ?";
	public static final String SQL_SUBCATEGORIA_SIN_PADRE = "select * from Categoria where categoriaPadre is NULL";
	public static final String SQL_LISTA_PRODUCTO = "select * from producto";
	public static final String SQL_CATEGORIA = "select * from Categoria where nombreCategoria = ?";
	public static final String SQL_LISTA_PRODUCTOS = "select * from Producto p inner join Categoria c on p.categoria = c.nombreCategoria and c.nombreCategoria = ?";
	public static final String SQL_PRODUCTO = "select * from Producto where nombre = ?";
	private static final String SQL_GET_CLIENTE = "select * from cliente where nombreUsuario = ?";
	private static final String SQL_INSERTAR_PRODUCTOS_PEDIDO = "insert into PedidoProducto(idPedido, idProducto, cantidad) values (?, ?, ?)";
	private static final String SQL_INSERTAR_PEDIDO = "insert into Pedido(idPedido, idCliente, fecha, estado) values (?, ?, ?, ?)";
	private static final String SQL_GET_PEDIDOS = "select * from pedido";
	private static final String SQL_GET_PEDIDOs_Producto = "select * from pedidoproducto";
	
	
	
	private Database2 db;
	private Carrito carrito;
	private List<Producto> productosPosibles;
	private ClienteView vista;

	public ClienteModel(Database2 db, Carrito carrito, ClienteView vista) {
		this.db = db;
		this.carrito = carrito;
		this.productosPosibles = getProductos();
		this.vista = vista;
	}

	public boolean checkProductoYaEnCarrito(String nombre) {
		List<Object[]> productos = carrito.getCarrito();
		for (Object[] o : productos) {
			if (((Producto) o[0]).getNombre().equals(nombre)) {
				return true;
			}
		}

		return false;
	}
	
	private List<Producto> getProductos() {
		List<Producto> resultado = new ArrayList<Producto>();
		List<Object[]> productos = db.executeQueryArray(SQL_LISTA_PRODUCTO); 
		
		for (int i = 0; i < productos.size(); i++) {
			Producto p = new Producto((int)productos.get(i)[0], (String)productos.get(i)[1], (String)productos.get(i)[2], (String)productos.get(i)[3], (double)productos.get(i)[4],(int)productos.get(i)[5],(int)productos.get(i)[6],(int)productos.get(i)[7]);
			resultado.add(p);
		}
		
		return resultado;
	}

	public Database2 getDatabase() {
		return this.db;
	}
	
	public String getPrecioPorNombre(String nombre, int cantidad) {
		double precio = 0;
		for (Producto p: this.productosPosibles) {
			if (p.getNombre().equals(nombre)) precio = p.getPrecio() * cantidad;
		}
		String precioFormateado = String.format("%.2f", precio); //que tenga solo 2 decimales
		return precioFormateado;
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

	public List<Producto> getProductos(String categoria) {
		List<Producto> list = new ArrayList<Producto>();
		List<Object[]> listDb = db.executeQueryArray(SQL_LISTA_PRODUCTOS, categoria);
		for (int i = 0; i < listDb.size(); i++) {
			Producto p = new Producto((int) listDb.get(i)[0], (String) listDb.get(i)[1],
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
	
	public Producto getProducto(String nombre) {
		List<Object[]> listDb = db.executeQueryArray(SQL_PRODUCTO, nombre);
		if(listDb.isEmpty()) {
			return null;
		}
		Producto p = new Producto((int) listDb.get(0)[0], (String) listDb.get(0)[1],
				(String) listDb.get(0)[2], (String) listDb.get(0)[3], (double) listDb.get(0)[4],
				(int) listDb.get(0)[5], (int) listDb.get(0)[6], (int) listDb.get(0)[7]);
		return p;
	}
	
	public Carrito getCarrito() {
		return this.carrito;
	}

	public boolean esClienteDeEmpresa(String nombreUsuario) {
		List<Object[]> usuario = db.executeQueryArray(SQL_GET_CLIENTE, nombreUsuario);
		String tipo = (String) usuario.get(0)[8];
		
		if (tipo.equals("EMPRESA")) return true;
		return false;
	}

	
	// ---------------------- CAMBIAR ---------------------- 
	//DE AQUI PARA ABAJO CODIGO REPETIDO CON CARRITO MODEL, MOVER A OTRA CLASE padre de ambos
	
	public void confirmarPedido() {
		if (checkHayProductos()) {
			System.out.println("-- ANTES de confirmar compra -- ");
			mostrarPedidos();
			
			int nuevoID = getNuevoID();
			//int numeroProductos = carrito.getCarrito().size(); ya no se utiliza
			String fecha = getFechaDeHoy();
			String estado = "Pendiente";
			
			db.executeUpdate(SQL_INSERTAR_PEDIDO, nuevoID, getClientIDfromName(vista.getDto().getName()), fecha, estado);
				
			insertarProductosPedido(nuevoID);
			
			//JOptionPane.showMessageDialog(this.v, "Gracias por ");
			
			System.out.println("-- DESPUES de confirmar compra --");
			mostrarPedidos();
			
			//vuelvo al inicio
			AppInicioView vista = new AppInicioView(getDatabase());
			this.vista.dispose();
			vista.setVisible(true);
			
		} else {
			JOptionPane.showMessageDialog(this.vista, "No hay productos en su carrito");
			//vuelvo al inicio
			AppInicioView vista = new AppInicioView(getDatabase());
			this.vista.dispose();
			vista.setVisible(true);
		}
	}
	
	private String getClientIDfromName(String name) {
		List<Object[]> usuario = db.executeQueryArray(SQL_GET_CLIENTE, name);
		
		return (String) usuario.get(0)[0];
		
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
	
	

}
