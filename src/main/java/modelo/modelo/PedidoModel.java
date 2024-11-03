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

public class PedidoModel {
	
	private static final String SQL_LISTA_PEDIDO = "select * from Pedido where estado = 'Pendiente' order by fecha";
	private static final String SQL_RECUPERAR_WO = "select w.idWorkorder, idAlmacenero, workorderEstado, sum(cantidad) from Workorder w "
												+ "inner join WorkorderProducto wp on w.idWorkorder = wp.idWorkorder "
												+ "where workorderEstado = 'Pendiente' "
												+ "group by w.idWorkorder";
	private final static String SQL_FIND_PRODUCTOS_FROM_PEDIDO = "select * from PedidoProducto pp "
														+ "inner join Producto p on pp.idProducto = p.id "
														+ "where idPedido = ?";
	private static final String SQL_INSERTAREN_WO = "insert into Workorder(idWorkorder, idAlmacenero, workorderEstado) values (?,?,?)";
	private static final String SQL_INSERTAREN_WOPE = "insert into WorkorderPedido(idWorkorder, idPedido) values (?,?)";
	private static final String SQL_INSERTAREN_WOPR = "insert into WorkorderProducto(idWorkorder, idPedido, idProducto, cantidad, recogidos) "
													+ "values (?,?,?,?,0)";
	private static final String SQL_UPDATE_ESTADO_LISTO = "update Pedido set estado = 'Listo' where idPedido = ? ";
	private static final String SQL_ALMACENERO = "select * from Almacenero where idAlmacenero = ?";
	private static final String SQL_MAX_WO_ID = "select max(idWorkorder) from Workorder";
	
	private AlmaceneroDto almacenero = new AlmaceneroDto();
	
	private Database2 db;
	private List<PedidoDto> pedidos;
	
	public PedidoModel(Database2 database, int idAlmacenero) {
		this.db = database;
		almacenero.idAlmacenero = idAlmacenero;
		setAlmacenero();
		pedidos = getPedidos();
	}
	
	private void setAlmacenero() {
		List<Object[]> o = db.executeQueryArray(SQL_ALMACENERO, almacenero.idAlmacenero);
		almacenero.nombre = (String)o.get(0)[1];
		almacenero.apellido = (String)o.get(0)[2];
	}
	
	public AlmaceneroDto getAlmacenero() {
		return almacenero;
	}

	private List<PedidoDto> getPedidos() {
		List<PedidoDto> list = new ArrayList<PedidoDto>();
		List<Object[]> listDb = db.executeQueryArray(SQL_LISTA_PEDIDO);
		for(Object[] o : listDb) {
			PedidoDto pedido = new PedidoDto();
			pedido.idPedido = (int)o[0];
			pedido.idCliente = (String)o[1];
			pedido.fecha = LocalDate.parse((String)o[2]);
			pedido.estadoPedido = (String)o[3];
			pedido.productos = new HashMap<>(getProductosPorPedido(pedido.idPedido));
			list.add(pedido);
		}
		return list;
	}
	
	private Map<ProductoDto, Integer> getProductosPorPedido(int idPedido) {
		Map<ProductoDto, Integer> resultado = new HashMap<>();
		List<Object[]> productos = db.executeQueryArray(SQL_FIND_PRODUCTOS_FROM_PEDIDO, idPedido);
		for (Object[] o : productos) {
			ProductoDto dto = new ProductoDto();
			dto.idProducto = (int)o[3];
			dto.nombre = (String)o[4];
			dto.categoria = (String)o[5];
			dto.descripcion = (String)o[6];
			dto.precio = (double)o[7];
			dto.pasillo = (int)o[8];
			dto.estanteria = (int)o[9];
			dto.balda = (int)o[10];
			int cantidad = (int)o[2];
			resultado.put(dto, cantidad);
		}
		return resultado;
	}
	
	public List<PedidoDto> obtainPedidos(){
		return pedidos;
	}
	
	public List<Integer> generarWorkorder(int posPedido) {
		List<Integer> workordersIDs = new ArrayList<>();
		PedidoDto pedido = pedidos.get(posPedido);
		int cantidad = getCantidadTotalDeProductos(pedido);	
		int numeroWorkoredrsGeneradas = getNumWorkorders(cantidad);
		
		if (numeroWorkoredrsGeneradas == 1) {
			int ret = añadirPedidoAWorkorderExistente(pedido, cantidad);
			if (ret != -1) {
				actualizarPedidoListo(pedido.idPedido);
				pedidos.remove(pedido);
				workordersIDs.add(ret);
				return workordersIDs;
			}
		}
		for (int i = 0; i<numeroWorkoredrsGeneradas; i++) {
			PedidoDto copia = new PedidoDto();
			copia.idPedido = pedido.idPedido;
			WorkorderDto workorder = new WorkorderDto();
			workorder.idWorkorder = getIdWorkorder();
			workorder.idAlmacenero = almacenero.idAlmacenero;
			workorder.estado = "Pendiente";
			if (numeroWorkoredrsGeneradas > 1) workorder.estado = "En Curso";
			db.executeUpdate(SQL_INSERTAREN_WO, workorder.idWorkorder, workorder.idAlmacenero, workorder.estado);
			copia = fragmentarPedido(pedido, copia, new ArrayList<>(pedido.productos.keySet()));
			workorder.pedidos.add(copia);
			añadirPedido(workorder, copia);
			workordersIDs.add(workorder.idWorkorder);
		}
		actualizarPedidoListo(pedido.idPedido);
		pedidos.remove(pedido);
		return workordersIDs;
	}
	
	private PedidoDto fragmentarPedido(PedidoDto pedido, PedidoDto fragmento, List<ProductoDto> productos) {
		if (productos.size() == 0) return fragmento;
		int cantEnFrag = getCantidadTotalDeProductos(fragmento);
		int cantidad = pedido.productos.get(productos.get(0));
		ProductoDto producto = productos.get(0);
		if (cantEnFrag + cantidad > WorkorderDto.MAXIMO_PRODUCTOS_POR_WORKORDER) {
			cantidad -= (WorkorderDto.MAXIMO_PRODUCTOS_POR_WORKORDER - cantEnFrag);
			fragmento.productos.put(producto, WorkorderDto.MAXIMO_PRODUCTOS_POR_WORKORDER - cantEnFrag);
			pedido.productos.put(producto, cantidad);
			return fragmento;
		} else if (cantEnFrag + cantidad == WorkorderDto.MAXIMO_PRODUCTOS_POR_WORKORDER){
			fragmento.productos.put(producto, cantidad);
			pedido.productos.remove(producto);
			productos.remove(producto);
			return fragmento;
		} else {
			fragmento.productos.put(producto, cantidad);
			pedido.productos.remove(producto);
			productos.remove(producto);
			fragmento = fragmentarPedido(pedido, fragmento, productos);
		}
		return fragmento;
	}

	private int añadirPedidoAWorkorderExistente(PedidoDto pedido, int numProd) {
		List<Object[]> result = db.executeQueryArray(SQL_RECUPERAR_WO);
		if (result.size() == 0) return -1;
		for (Object[] o : result) {
			if ((int)o[3] + numProd > WorkorderDto.MAXIMO_PRODUCTOS_POR_WORKORDER) continue;
			WorkorderDto wo = new WorkorderDto();
			wo.idWorkorder = (int)o[0];
			wo.idAlmacenero = (int)o[1];
			wo.estado = (String)o[2];
			añadirPedido(wo, pedido);
			return wo.idWorkorder;
		}		
		return -1;

	}

	private void añadirPedido(WorkorderDto wo, PedidoDto pedido) {
		db.executeUpdate(SQL_INSERTAREN_WOPE, wo.idWorkorder, pedido.idPedido);
		for (ProductoDto prod : pedido.productos.keySet()) {
			db.executeUpdate(SQL_INSERTAREN_WOPR, wo.idWorkorder, pedido.idPedido, prod.idProducto, pedido.productos.get(prod));
		}
	}

	private int getIdWorkorder() {
		List<Object[]> id = db.executeQueryArray(SQL_MAX_WO_ID);
		return (int)id.get(0)[0] + 1;
	}
	
	private int getNumWorkorders(int cantidad) {
		int cantWo = cantidad/WorkorderDto.MAXIMO_PRODUCTOS_POR_WORKORDER;
		int aux = cantidad%WorkorderDto.MAXIMO_PRODUCTOS_POR_WORKORDER;
		if (aux > 0) {
			cantWo++;
		}
		return cantWo;
	}

	public int getCantidadTotalDeProductos(PedidoDto dto) {
		int cantidad = 0;
		for (ProductoDto prod : dto.productos.keySet()) {
			cantidad += dto.productos.get(prod);
		}
		return cantidad;
	}
	
	private void actualizarPedidoListo(int idPedido) {
		db.executeUpdate(SQL_UPDATE_ESTADO_LISTO, idPedido);
	}

	public Database2 getDB() {
		return db;
	}	

}
