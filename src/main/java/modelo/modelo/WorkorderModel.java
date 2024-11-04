package modelo.modelo;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import giis.demo.util.Database2;
import persistence.dto.PedidoDto;
import persistence.dto.ProductoDto;
import persistence.dto.WorkorderDto;

public class WorkorderModel {
	
	private Database2 db;
	private List<Integer> ids;
	private List<WorkorderDto> workorders = new ArrayList<>();

	private final static String SQL_FIND_WO = "select * from Workorder where idWorkorder = ?";
	private final static String SQL_FIND_PEDIDOS_FROM_WO = "select * from WorkorderPedido wp "
														+ "inner join Pedido p on wp.idPedido = p.idPedido "
														+ "where idWorkorder = ? and p.estado = 'Listo'";
	private final static String SQL_FIND_PRODUCTOS_FROM_PEDIDOYWO = "select * from WorkorderProducto wp "
														+ "inner join Producto p on wp.idProducto = p.id "
														+ "where idWorkorder = ? and idPedido = ?";
	
	public WorkorderModel(Database2 dataBase, List<Integer> ids) {
		this.db = dataBase;
		this.ids = ids;
		getWorkorders();
	}
	
	private void getWorkorders(){
		List<Object[]> o;
		for (int idWorkorder : ids) {
			o = db.executeQueryArray(SQL_FIND_WO, idWorkorder);
			WorkorderDto wo = new WorkorderDto();
			wo.idWorkorder = (int)o.get(0)[0];
			wo.idAlmacenero = (int)o.get(0)[1];
			wo.estado = (String)o.get(0)[2];
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
			int cantidad = (int)o[3];
			if (cantidad > 0) resultado.put(dto, cantidad);
		}
		return resultado;
	}
	
	static class ProductoComparator implements Comparator<ProductoDto> {
	    private int ultimaBaldaVisitada;

	    public ProductoComparator(int ultimaBaldaVisitada) {
	        this.ultimaBaldaVisitada = ultimaBaldaVisitada;
	    }

	    @Override
	    public int compare(ProductoDto p1, ProductoDto p2) {
	        // Comprobar por pasillo
	        int pasilloComp = Integer.compare(p1.pasillo, p2.pasillo);
	        if (pasilloComp != 0) {
	            return pasilloComp; 
	        }

	        // Comprobar estanteria
	        int estanteriaComparison = Integer.compare(p1.estanteria, p2.estanteria);
	        if (estanteriaComparison != 0) {
	            return estanteriaComparison; // Si son diferentes, devolvemos la comparación de estanterías
	        }

	        // comprobar balda
	        if (ultimaBaldaVisitada == 3) {
	            //ordenacion descendente (ultima balda es 3)
	            return Integer.compare(p2.balda, p1.balda);
	        } else {
	            // ordenacion ascendente (resto)
	            return Integer.compare(p1.balda, p2.balda);
	        }
	    }

	    //no hace falta
	    public void setUltimaBaldaVisitada(int balda) {
	        this.ultimaBaldaVisitada = balda;
	    }
	}

	public static List<ProductoDto> ordenarProductos(List<ProductoDto> productos) {
	    ProductoComparator comparator = new ProductoComparator(1); //por defecto primera balda es 1(ascendente)
	    Collections.sort(productos, comparator);
	    // Actualizar ultima balda visitada(no hace falta)
	    for (ProductoDto producto : productos) {
	        comparator.setUltimaBaldaVisitada(producto.balda);
	    }
	    return productos;
	}

	public List<WorkorderDto> obtainWorkorders() {
		return workorders;
	}

	public int getCantidadTotalDeProductos(PedidoDto dto) {
		int cantidad = 0;
		for (ProductoDto prod : dto.productos.keySet()) {
			cantidad += dto.productos.get(prod);
		}
		return cantidad;
	}
}
