package modelo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.Producto;
import modelo.dto.ProductoWrapper;
import persistence.dto.AlmaceneroDto;
import persistence.dto.PedidoDto;
import persistence.dto.WorkorderDto;

public class EmpaquetadoModel {
	
	List<WorkorderDto> workorders = new ArrayList<>();
	AlmaceneroDto almacenero = new AlmaceneroDto();
	private Database2 db;
	
	private final static String SQL_WOLISTAS = "select * from Workorder where workorderEstado = 'Listo'";
	private final static String SQL_PEDIDOS = "select * from WorkorderPedido wp "
											+ "inner join Pedido p on wp.idPedido = p.idPedido "
											+ "where idWorkorder = ?";
	private final static String SQL_PRODUCTS_ID = "select idProducto, cantidad from pedidoproducto where idPedido = ?";
	private final static String SQL_PRODUCTS = "select * from producto where id = ?";
	private final static String SQL_PAQUETE = "insert into Paquete(idPaquete, idWorkorder, paqueteEstado) values (?,?,'Listo')";
	
	public EmpaquetadoModel(Database2 db2, int idAlmacenero) {
		db = db2;
		almacenero.idAlmacenero = idAlmacenero;
		workordersListas();
	}
	
	public EmpaquetadoModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
		almacenero.idAlmacenero = 1;
		workordersListas();
	}
	
	private void workordersListas(){
		List<Object[]> result = db.executeQueryArray(SQL_WOLISTAS);
		
		for (Object[] o : result) {
			WorkorderDto wo = new WorkorderDto();
			wo.idWorkorder = (int)o[0];
			wo.idAlmacenero = (int)o[1];
			wo.estado = (String)o[2];
			System.out.println(wo.idWorkorder + " " + wo.idAlmacenero + " " + wo.estado);
			wo.pedidos = getPedidos(wo.idWorkorder);
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
			System.out.println(pedido.idPedido + " " + pedido.idCliente + " " + pedido.fecha + " " + pedido.estadoPedido);
		}	
		return pedidos;
	}

	public List<ProductoWrapper> productosPorWorkorder(int idPedido){
		List<ProductoWrapper> resultado = new ArrayList<>();
		List<Object[]> idProductos = db.executeQueryArray(SQL_PRODUCTS_ID, idPedido);
		
		List<Object[]> productos;
		for (int i = 0; i < idProductos.size(); i++) {
			productos = db.executeQueryArray(SQL_PRODUCTS, idProductos.get(i)[0]);
			Producto p = new Producto((int)productos.get(i)[0], (String)productos.get(i)[1], (String)productos.get(i)[2], (String)productos.get(i)[3], (double)productos.get(i)[4],(int)productos.get(i)[5],(int)productos.get(i)[6],(int)productos.get(i)[7]);
			ProductoWrapper pw = new ProductoWrapper(p, (int)idProductos.get(i)[1]);
			resultado.add(pw);
		}
		
		return resultado;
	}

	public int checkID(int id, List<ProductoWrapper> productos) {
		for (int i = 0; i<productos.size(); i++) {
			if (id == productos.get(i).getID()) {
				return i;
			}
		}
		return -1;
	}
	
	public void empaquetar(int idPaquete, int idWorkorder) {
		db.executeUpdate(SQL_PAQUETE, idPaquete, idWorkorder);
	}
	
	public Database2 getDB() {
		return db;
	}

}
