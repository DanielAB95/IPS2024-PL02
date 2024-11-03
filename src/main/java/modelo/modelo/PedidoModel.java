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
	private final static String SQL_FIND_PRODUCTOS_FROM_PEDIDO = "select * from PedidoProducto pp "
														+ "inner join Producto p on pp.idProducto = p.id "
														+ "where idPedido = ?";
	private static final String SQL_UPDATE_ESTADO_LISTO = "update Pedido set Estado = 'Listo' where idPedido = ? ";
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
	
	public void generarWorkorder(int posPedido) {
		PedidoDto pedido = pedidos.get(posPedido);
		int cantidad = getCantidadTotalDeProductos(pedido);	
		int numeroWorkoredrsGeneradas = getNumWorkorders(cantidad);
		System.out.println(getIdWorkorder());
		
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
	
	public void actualizarPedidoListo(int idPedido) {
		db.executeUpdate(SQL_UPDATE_ESTADO_LISTO, idPedido);
	}	

}
