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

public class RecogidaModel {

	List<WorkorderDto> workorders = new ArrayList<>();
	AlmaceneroDto almacenero = new AlmaceneroDto();
	private Database2 db;
	
	private final static String SQL_FIND_WOLISTAS = "select * from Workorder "
												  + "where workorderEstado in ('Pendiente','En Curso') and idAlmacenero = ?";
	private final static String SQL_FIND_PEDIDOS_FROM_WO = "select * from WorkorderPedido wp "
											+ "inner join Pedido p on wp.idPedido = p.idPedido "
											+ "where idWorkorder = ?";
	private final static String SQL_FIND_PRODUCTOS_FROM_PEDIDOYWO = "select * from WorkorderProducto wp "
											+ "inner join Producto p on wp.idProducto = p.id "
											+ "where idWorkorder = ? and idPedido = ?";
	private final static String SQL_FIND_PRODUCTOS_RECOGIDOS = "select recogidos from WorkorderProducto "
		    													+ "where idWorkorder = ? and idPedido = ? and idProducto = ?";
	private final static String SQL_UPDATE_WOPROD = "update WorkorderProducto set recogidos = recogidos + ? "
												  + "where idWorkorder = ? and idPedido = ? and idProducto = ?";
	
	public RecogidaModel(Database2 db2, int idAlmacenero) {
		db = db2;
		almacenero.idAlmacenero = idAlmacenero;
		setAlmacenero();
		workordersListas();
	}
	
	public RecogidaModel() {
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
		List<Object[]> result = db.executeQueryArray(SQL_FIND_WOLISTAS, almacenero.idAlmacenero);
		
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
			int cantidad = (int)o[3] - getCantidad(idWorkorder, idPedido ,dto.idProducto);
			if (cantidad > 0) resultado.put(dto, cantidad);
		}
		return resultado;
	}
	
	private int getCantidad(int idWorkorder, int idPedido, int idProducto) {
		List<Object[]> result = db.executeQueryArray(SQL_FIND_PRODUCTOS_RECOGIDOS, idWorkorder, idPedido ,idProducto);
		if (result.size() == 0) {
			return 0;
		}
		return (int)result.get(0)[0];
	}
	
	public List<WorkorderDto> getWorkorders(){
		return workorders;
	}

	public boolean checkID(WorkorderDto wdto, int idProducto, int cantidad) {
		ProductoDto prod = new ProductoDto();
		if (wdto.estado.equals("Pendiente")) {
			db.executeUpdate(Queries.Workorder.UPDATE, "En Curso", wdto.idWorkorder);
		}
		prod.idProducto = idProducto;
		for (PedidoDto pdto : wdto.pedidos) {
			if (pdto.productos.containsKey(prod)) {
				if (!guardarEnModelo(wdto, pdto, prod, cantidad)) return false;	
				guardarEnBaseDeDatos(wdto, pdto, prod, cantidad);
				if (isWoFinished(wdto)) {
					workorders.remove(wdto);
					db.executeUpdate(Queries.Workorder.UPDATE_FECHA, "Listo", LocalDate.now().toString(), wdto.idWorkorder);
				}
				return true;
			}
		}
		return false;
	}

	private void guardarEnBaseDeDatos(WorkorderDto wo, PedidoDto ped, ProductoDto prod, int cantidad) {
		actualizar(wo.idWorkorder, ped.idPedido, prod.idProducto, cantidad);
	}

	private boolean guardarEnModelo(WorkorderDto wo, PedidoDto ped, ProductoDto prod, int cant) {
		int cantidad = ped.productos.get(prod) - cant;
		if (cantidad > 0) {
			ped.productos.replace(prod, cantidad);
			return true;
		} else if (cantidad == 0){
			ped.productos.remove(prod);
			if (isPedidoFinished(ped)) {
				wo.pedidos.remove(ped);
			}
			return true;
		}
		return false;
	}
	
	public void apuntarIncidencia(WorkorderDto dto) {
		db.executeUpdate(Queries.Workorder.UPDATE, "Incidencia", dto.idWorkorder);
		workorders.remove(dto);
	}

	private void actualizar(int idWorkorder, int idPedido, int idProducto, int cantidad) {
		db.executeUpdate(SQL_UPDATE_WOPROD, cantidad, idWorkorder, idPedido, idProducto);
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
