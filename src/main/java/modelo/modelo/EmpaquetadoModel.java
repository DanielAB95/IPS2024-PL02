package modelo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import giis.demo.util.Database2;
import persistence.dto.AlmaceneroDto;
import persistence.dto.PedidoDto;
import persistence.dto.ProductoDto;
import persistence.dto.Queries;
import persistence.dto.WorkorderDto;

public class EmpaquetadoModel {
	
	List<WorkorderDto> workorders = new ArrayList<>();
	AlmaceneroDto almacenero = new AlmaceneroDto();
	private int idPaquete;
	private Database2 db;
	
	private final static String SQL_FIND_WOLISTAS = "select * from Workorder where workorderEstado = 'Listo'";
	private final static String SQL_FIND_PEDIDOS_FROM_WO = "select * from WorkorderPedido wp "
											+ "inner join Pedido p on wp.idPedido = p.idPedido "
											+ "where idWorkorder = ? and p.estado = 'Listo'";
	private final static String SQL_FIND_PRODUCTOS_FROM_PEDIDOYWO = "select * from WorkorderProducto wp "
											+ "inner join Producto p on wp.idProducto = p.id "
											+ "where idWorkorder = ? and idPedido = ?";
	private final static String SQL_FIND_PRODUCTOS_EMPAQUETADOS = "select cantidad from PaqueteProducto "
															    + "where idPaquete = ? and idProducto = ?";
	private final static String SQL_INSERT_PAQUETEPROD = "insert into PaqueteProducto(idPaquete, idProducto, cantidad) values (?,?,1)";
	private final static String SQL_INSERT_WOPAQ = "insert into workorderPaquete(idWorkorder, idPaquete) values (?,?)";
	private final static String SQL_UPDATE_PAQUETEPROD = "update PaqueteProducto set cantidad = cantidad + 1 where idPaquete = ? and idProducto = ?";
	private final static String SQL_MAX_NUM_PAQUETE = "select max(idPaquete) from Paquete";
	private final static String SQL_RECUPERARPAQUETE = "select wp.idPaquete from workorderPaquete wp "
													 + "inner join Paquete p on wp.idPaquete = p.idPaquete "
													 + "where wp.idWorkorder = ? and p.paqueteEstado = 'En Curso'";
	private final static String SQL_RECUPERARPRODUCTO = "select * from paqueteProducto where idPaquete = ? and idProducto = ?";
	private final static String SQL_UPDATE_ESTADO_PEDIDO = "update Pedido set estado = ? where idPedido = ?";
	private final static String SQL_FIND_ESTADO_PEDIDO = "select estado from Pedido where idPedido = ?";
	
	public EmpaquetadoModel(Database2 db2, int idAlmacenero) {
		db = db2;
		almacenero.idAlmacenero = idAlmacenero;
		setAlmacenero();
		workordersListas();
	}
	
	public EmpaquetadoModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
		almacenero.idAlmacenero = 1;
		setAlmacenero();
		workordersListas();
	}
	
	private void setAlmacenero() {
		List<Object[]> o = db.executeQueryArray(Queries.Almacenero.FIND_FROM_ID, almacenero.idAlmacenero);
		almacenero.nombre = (String)o.get(0)[1];
		almacenero.apellido = (String)o.get(0)[2];
	}
	
	public AlmaceneroDto getAlmacenero() {
		return almacenero;
	}
	
	private void workordersListas(){
		List<Object[]> result = db.executeQueryArray(SQL_FIND_WOLISTAS);
		
		for (Object[] o : result) {
			WorkorderDto wo = new WorkorderDto();
			wo.idWorkorder = (int)o[0];
			wo.idAlmacenero = (int)o[1];
			wo.estado = (String)o[2];
			wo.pedidos = new ArrayList<>(getPedidosFromWorkorder(wo.idWorkorder));
			workorders.add(wo);
		}
	}
	
	private List<PedidoDto> getPedidosFromWorkorder(int idWorkorder) {
		List<Object[]> result = db.executeQueryArray(SQL_FIND_PEDIDOS_FROM_WO, idWorkorder);
		List<PedidoDto> pedidos = new ArrayList<>();
		for (Object[] o : result) {
			PedidoDto pedido = new PedidoDto();
			pedido.idPedido = (int)o[1];
			pedido.idCliente = (String)o[3];
			pedido.fecha = LocalDate.parse((String)o[4]);
			pedido.estadoPedido = (String)o[5];
			pedido.productos = new HashMap<>(getProductosPorPedido(idWorkorder, pedido.idPedido));
			pedidos.add(pedido);
		}	
		return pedidos;
	}
	
	private Map<ProductoDto, Integer> getProductosPorPedido(int idWorkorder, int idPedido) {
		Map<ProductoDto, Integer> resultado = new HashMap<>();
		List<Object[]> productos = db.executeQueryArray(SQL_FIND_PRODUCTOS_FROM_PEDIDOYWO, idWorkorder, idPedido);
		for (Object[] o : productos) {
			ProductoDto dto = new ProductoDto();
			dto.idProducto = (int)o[5];
			dto.nombre = (String)o[6];
			dto.categoria = (String)o[7];
			dto.descripcion = (String)o[8];
			dto.precio = (double)o[9];
			dto.pasillo = (int)o[10];
			dto.estanteria = (int)o[11];
			dto.balda = (int)o[12];
			int cantidad = (int)o[3] - getCantidad(idWorkorder, dto.idProducto);
			if (cantidad > 0) resultado.put(dto, cantidad);
		}
		return resultado;
	}
	
	private int getCantidad(int idWorkorder, int idProducto) {
		List<Object[]> result = db.executeQueryArray(SQL_RECUPERARPAQUETE, idWorkorder);
		if (result.size() == 0) {
			return 0;
		}
		int idPaquete = (int)result.get(0)[0];
		result = db.executeQueryArray(SQL_FIND_PRODUCTOS_EMPAQUETADOS, idPaquete, idProducto);
		if (result.size() == 0) {
			return 0;
		}
		return (int)result.get(0)[0];
	}
	
	public List<WorkorderDto> getWorkorders(){
		return workorders;
	}

	public int checkID(WorkorderDto wdto, PedidoDto pdto, int idProducto) {
		ProductoDto prod = new ProductoDto();
		prod.idProducto = idProducto;
		if (pdto.productos.containsKey(prod)) {
			guardarEnModelo(wdto, pdto, prod);	
			guardarEnBaseDeDatos(wdto, pdto, prod);
			if (isWoFinished(wdto)) {
				workorders.remove(wdto);
				db.executeUpdate(Queries.Workorder.UPDATE, "Empaquetada", wdto.idWorkorder);
			}
			return idPaquete;
		}
		return 0;
	}

	private void guardarEnBaseDeDatos(WorkorderDto wo, PedidoDto ped, ProductoDto prod) {
		if (getPaqueteNuevo(wo.idWorkorder)) {
			insertarNuevo(ped.idPedido, prod.idProducto);
		} else {
			if (isInserted(prod.idProducto)) {
				actualizar(prod.idProducto);
			} else {
				insertar(prod.idProducto);
			}
		}
		actualizarRegistro();
	}

	private void guardarEnModelo(WorkorderDto wo, PedidoDto ped, ProductoDto prod) {
		int cantidad = ped.productos.get(prod) - 1;
		if (cantidad > 0) {
			ped.productos.replace(prod, cantidad);
		} else {
			ped.productos.remove(prod);
			if (isPedidoFinished(ped)) {
				wo.pedidos.remove(ped);
				compruebaPedidoEstado(ped);
			}
		}
	}
	
	private void compruebaPedidoEstado(PedidoDto pdto) {
		for (WorkorderDto wo : workorders) {
			if (wo.pedidos.contains(pdto)) {
				return;
			}
		}
		db.executeUpdate(SQL_UPDATE_ESTADO_PEDIDO, "Empaquetado", pdto.idPedido);
	}
	
	private boolean isInserted(int idProducto) {
		List<Object[]> result = db.executeQueryArray(SQL_RECUPERARPRODUCTO, idPaquete, idProducto);
		if (result.size() == 0) {
			return false;
		}
		return true;
	}
	
	private void actualizarRegistro() {
		LocalDate date = LocalDate.now();
		List<Object[]> result = db.executeQueryArray(Queries.Paquete.FIND_REGISTRO, almacenero.idAlmacenero, date.toString());
		if (result.size() != 0) {
			db.executeUpdate(Queries.Paquete.INCREMENT_REGISTRO, almacenero.idAlmacenero, date.toString());
		} else {
			db.executeUpdate(Queries.Paquete.INSERT_REGISTRO, almacenero.idAlmacenero, date.toString());
		}
	}

	private void insertar(int idProducto) {
		db.executeUpdate(SQL_INSERT_PAQUETEPROD, idPaquete, idProducto);
	}

	private void actualizar(int idProducto) {
		db.executeUpdate(SQL_UPDATE_PAQUETEPROD, idPaquete, idProducto);
	}

	private void insertarNuevo(int idPedido,int idProducto) {
		db.executeUpdate(Queries.Paquete.INSERT, idPaquete, idPedido, almacenero.idAlmacenero);
		db.executeUpdate(SQL_INSERT_PAQUETEPROD, idPaquete, idProducto);
	}

	private boolean getPaqueteNuevo(int idWorkorder){
		List<Object[]> result = db.executeQueryArray(SQL_RECUPERARPAQUETE, idWorkorder);
		if (result.size() == 0) {
			result = db.executeQueryArray(SQL_MAX_NUM_PAQUETE);
			idPaquete = (int)result.get(0)[0] + 1;
			db.executeUpdate(SQL_INSERT_WOPAQ, idWorkorder, idPaquete);
			return true;
		}
		idPaquete = (int)result.get(0)[0];
		return false;
	}
	
	public int cerrarCaja(int idWorkorder) {
		if (!getPaqueteNuevo(idWorkorder)) {
			db.executeUpdate(Queries.Paquete.UPDATE, "Listo", LocalDate.now().toString() ,idPaquete);
			return idPaquete;
		}
		return 0;
	}
	
	public boolean albaranDisponible(PedidoDto pdto) {
		List<Object[]> result = db.executeQueryArray(SQL_FIND_ESTADO_PEDIDO, pdto.idPedido);
		String estado = (String)result.get(0)[0];
		if (estado.equals("Empaquetado")) {
			return true;
		}
		return false;
	}
	
	public Database2 getDB() {
		return db;
	}
	
	private boolean isWoFinished(WorkorderDto dto) {
		return dto.pedidos.size() == 0;
	}
	
	private boolean isPedidoFinished(PedidoDto dto) {
		return dto.productos.size() == 0;
	}

}
