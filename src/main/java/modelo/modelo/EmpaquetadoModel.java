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
import persistence.dto.WorkorderDto;

public class EmpaquetadoModel {
	
	List<WorkorderDto> workorders = new ArrayList<>();
	AlmaceneroDto almacenero = new AlmaceneroDto();
	private int idPaquete;
	private Database2 db;
	
	private final static String SQL_ALMACENERO = "select * from Almacenero where idAlmacenero = ?";
	private final static String SQL_WOLISTAS = "select * from Workorder where workorderEstado = 'Listo'";
	private final static String SQL_PEDIDOS = "select * from WorkorderPedido wp "
											+ "inner join Pedido p on wp.idPedido = p.idPedido "
											+ "where idWorkorder = ?";
	private final static String SQL_PRODUCTOS = "select * from WorkorderProducto wp "
											+ "inner join Producto p on wp.idProducto = p.id "
											+ "where idWorkorder = ? and idPedido = ?";
	
	private final static String SQL_PAQUETE_INSERT = "insert into PaqueteProducto(idPaquete, idProducto, cantidad) values (?,?,1)";
	private final static String SQL_PAQUETE_UPDATE = "update PaqueteProducto set cantidad = cantidad + 1 where idPaquete = ? and idProducto = ?";
	private final static String SQL_MAX_NUM_PAQUETE = "select max(idPaquete) from Paquete";
	private final static String SQL_PRODUCTOS_PAQUETE = "select p.idPaquete from Paquete p " 
													  + "join PaqueteProducto pp on p.idPaquete = pp.idPaquete "
													  + "join WorkorderProducto wp on pp.idProducto = wp.idProducto "
													  +	"where wp.idPedido = ? and wp.idProducto IN ("
													  + "select idProducto from PaqueteProducto where idPaquete IN ("
													  + "select idPaquete from Paquete where idPedido = ? and paqueteEstado = 'En Curso'))";
	
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
		List<Object[]> o = db.executeQueryArray(SQL_ALMACENERO, almacenero.idAlmacenero);
		almacenero.nombre = (String)o.get(0)[1];
		almacenero.apellido = (String)o.get(0)[2];
	}
	
	public AlmaceneroDto getAlmacenero() {
		return almacenero;
	}
	
	private void workordersListas(){
		List<Object[]> result = db.executeQueryArray(SQL_WOLISTAS);
		
		for (Object[] o : result) {
			WorkorderDto wo = new WorkorderDto();
			wo.idWorkorder = (int)o[0];
			wo.idAlmacenero = (int)o[1];
			wo.estado = (String)o[2];
			System.out.println(wo.idWorkorder + " " + wo.idAlmacenero + " " + wo.estado);
			wo.pedidos = new ArrayList<>(getPedidos(wo.idWorkorder));
			workorders.add(wo);
		}
	}
	
	private List<PedidoDto> getPedidos(int idWorkorder) {
		List<Object[]> result = db.executeQueryArray(SQL_PEDIDOS, idWorkorder);
		List<PedidoDto> pedidos = new ArrayList<>();
		for (Object[] o : result) {
			PedidoDto pedido = new PedidoDto();
			pedido.idPedido = (int)o[1];
			pedido.idCliente = (String)o[3];
			pedido.fecha = LocalDate.parse((String)o[4]);
			pedido.estadoPedido = (String)o[5];
			System.out.println("\t" + pedido.idPedido + " " + pedido.idCliente + " " + pedido.fecha + " " + pedido.estadoPedido);
			pedido.productos = new HashMap<>(getProductosPorPedido(idWorkorder, pedido.idPedido));
			pedidos.add(pedido);
		}	
		return pedidos;
	}
	
	private Map<ProductoDto, Integer> getProductosPorPedido(int idWorkorder, int idPedido) {
		Map<ProductoDto, Integer> resultado = new HashMap<>();
		List<Object[]> productos = db.executeQueryArray(SQL_PRODUCTOS, idWorkorder, idPedido);
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
			System.out.println("\t\t" + dto.idProducto + " " + dto.nombre + " " + dto.categoria + " " + dto.precio + " " + (int)o[3]);
			resultado.put(dto, (int)o[3]);
		}
		return resultado;
	}
	
	public List<WorkorderDto> getWorkorders(){
		return workorders;
	}

	public int checkID(int posWorkorder, int posPedido, int idProducto) {
		WorkorderDto wo = workorders.get(posWorkorder);
		PedidoDto ped = wo.pedidos.get(posPedido);
		ProductoDto prod = new ProductoDto();
		prod.idProducto = idProducto;
		
		if (ped.productos.containsKey(prod)) {
			int cantidad = ped.productos.get(prod) - 1;
			if (cantidad > 0) {
				ped.productos.replace(prod, cantidad);
			} else {
				ped.productos.remove(prod);
			}			
			if (getIdsWorkordersForPackaging(posPedido)) {
				actualizar(prod.idProducto);
			} else {
				insertar(prod.idProducto);
			}
			System.out.println(idPaquete);
			return idPaquete;
		}
		return 0;
	}
	
	private void actualizar(int idProducto) {
		db.executeUpdate(SQL_PAQUETE_UPDATE, idPaquete, idProducto);
	}

	private void insertar(int idProducto) {
		db.executeUpdate(SQL_PAQUETE_INSERT, idPaquete, idProducto);
	}

	private boolean getIdsWorkordersForPackaging(int idPedido){
		List<Object[]> result = db.executeQueryArray(SQL_PRODUCTOS_PAQUETE, idPedido, idPedido);
		if (result.size() == 0) {
			result = db.executeQueryArray(SQL_MAX_NUM_PAQUETE);
			idPaquete = (int)result.get(0)[0] + 1;
			return true;
		}
		idPaquete = (int)result.get(0)[0];
		return false;
	}
	
	public Database2 getDB() {
		return db;
	}

}
