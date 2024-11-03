package modelo.modelo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import giis.demo.util.Database2;
import modelo.dto.Pedido;
import modelo.dto.PedidoDTO;
import modelo.dto.Producto;
import modelo.dto.ProductoAlmacen;
import modelo.dto.WorkorderDTO;

public class WorkorderModel {
	private static final String SQL_ADD_WORKORDER = "insert into Workorder (idWorkOrder, idAlmacenero, workorderEstado) values (?, ?, ?)";
	private static final String SQL_WORKORDERS = "select * from Workorder";
	private static final String SQL_WORKORDER_ALMACENERO = "select idAlmacenero from Workorder where idWorkorder = ?";
	private static final String SQL_WORKORDER_PEDIDO= "select idAlmacenero from Workorder where idWorkorder = ?";
	private static final String SQL_PRODUCTOSORDENADOS = "select p.id, pp.cantidad, p.descripcion, p.pasillo, p.estanteria, p.balda from Producto p inner join PedidoProducto pp on p.id = pp.idProducto where pp.idPedido = ?";
	private Database2 db=new Database2();
	private int idWorkorder;
	private List<String> incidencias = new ArrayList<>();
	private List<Integer> pedidos = new ArrayList<>();
	private boolean incidencia = false;
	
	public WorkorderModel(Database2 dataBase) {
		this.db = dataBase;
	}
	
	public void addIncidencia(String incidenciaStr) {
		if (incidenciaStr == null) throw new IllegalArgumentException();
		if (!incidencia) incidencia = true;
		incidencias.add(incidenciaStr);
	}
	
	public void crearWorkorder(int idAlmacenero) {
		int idWorkorder = generarIdWorkorder();
		db.executeUpdate(SQL_ADD_WORKORDER, idWorkorder,idAlmacenero,"Pendiente");
	}
	
	public void addPedidos(List<Integer> pedidosIn) {
		if (pedidosIn == null) throw new IllegalArgumentException();
		for (int p : pedidosIn) {
			pedidos.add(p);
		}
	}
	
	public void addPedido(Integer pedido) {
		if (pedido == null) throw new IllegalArgumentException();
		pedidos.add(pedido);
	}
	
	public List<WorkorderDTO> getWorkorders(){
		List<WorkorderDTO> list = new ArrayList<WorkorderDTO>();
		List<Object[]> workorders = db.executeQueryArray(SQL_WORKORDERS);
		
		for (int i = 0; i < workorders.size(); i++) {
			WorkorderDTO w = new WorkorderDTO((int)workorders.get(i)[0], (int)workorders.get(i)[1]);
			list.add(w);	
		}
		return list;
	}
	
	public int getWorkOrderAlmacenero() {
		List<Object[]> workorders = db.executeQueryArray(SQL_WORKORDER_ALMACENERO, idWorkorder);
		return (int)workorders.get(0)[0];
	}
	 
	public int getWorkOrderPedido(String idWorkorder) {
		List<Object[]> workorders = db.executeQueryArray(SQL_WORKORDER_PEDIDO,idWorkorder);
		return (int)workorders.get(0)[0];
	}
	
	public List<ProductoAlmacen> getProductos(int idPedido){
		List<ProductoAlmacen> productos = new ArrayList<ProductoAlmacen>();
		List<Object[]> listDb = db.executeQueryArray(SQL_PRODUCTOSORDENADOS, idPedido);
		for(int i = 0; i<listDb.size();i++) {
			ProductoAlmacen p = new ProductoAlmacen((int)listDb.get(i)[0],(int)listDb.get(i)[1],(String)listDb.get(i)[2],(int)listDb.get(i)[3],(int)listDb.get(i)[4],(int)listDb.get(i)[5]);
			productos.add(p);
		}
		
		return ordenarProductos(productos);
	}
	
	
	private int generarIdWorkorder() {
	    Random random = new Random();
	    int idWorkorder;
	    
	    do {
	        idWorkorder = random.nextInt(1000000); 
	    } while (idExiste(idWorkorder)); 

	    return idWorkorder;
	}

	private boolean idExiste(int idWorkorder2) {
		String query = "select count(*) from Workorder where idWorkorder = ?";
	    List<Object[]> listDb = db.executeQueryArray(query, idWorkorder);
	    int c = (int)listDb.get(0)[0];
	    return c > 0; 
	}
	
	
	class ProductoComparator implements Comparator<ProductoAlmacen> {
	    private int ultimaBaldaVisitada;

	    public ProductoComparator(int ultimaBaldaVisitada) {
	        this.ultimaBaldaVisitada = ultimaBaldaVisitada;
	    }

	    @Override
	    public int compare(ProductoAlmacen p1, ProductoAlmacen p2) {
	        // Comprobar por pasillo
	        int pasilloComp = Integer.compare(p1.getPasillo(), p2.getPasillo());
	        if (pasilloComp != 0) {
	            return pasilloComp; 
	        }

	        // Comprobar estanteria
	        int estanteriaComparison = Integer.compare(p1.getEstanteria(), p2.getEstanteria());
	        if (estanteriaComparison != 0) {
	            return estanteriaComparison; // Si son diferentes, devolvemos la comparación de estanterías
	        }

	        // comprobar balda
	        if (ultimaBaldaVisitada == 3) {
	            //ordenacion descendente (ultima balda es 3)
	            return Integer.compare(p2.getBalda(), p1.getBalda());
	        } else {
	            // ordenacion ascendente (resto)
	            return Integer.compare(p1.getBalda(), p2.getBalda());
	        }
	    }

	    //no hace falta
	    public void setUltimaBaldaVisitada(int balda) {
	        this.ultimaBaldaVisitada = balda;
	    }
	}

	private List<ProductoAlmacen> ordenarProductos(List<ProductoAlmacen> productos) {
	    ProductoComparator comparator = new ProductoComparator(1); //por defecto primera balda es 1(ascendente)

	    Collections.sort(productos, comparator);

	    // Actualizar ultima balda visitada(no hace falta)
	    for (ProductoAlmacen producto : productos) {
	        comparator.setUltimaBaldaVisitada(producto.getBalda());
	    }

	    return productos;
	}
}
