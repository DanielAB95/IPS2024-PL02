package modelo.modelo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import giis.demo.util.Database2;
import modelo.dto.ProductoAlmacen;
import modelo.dto.WorkorderDTO;

public class WorkorderModel {
	
	private Database2 db;

	private static final String SQL = "";
	
	public WorkorderModel(Database2 dataBase) {
		this.db = dataBase;
	}
	
	public List<WorkorderDTO> getWorkorders(){
		List<WorkorderDTO> list = new ArrayList<WorkorderDTO>();
		List<Object[]> workorders = db.executeQueryArray(SQL);
		
		for (int i = 0; i < workorders.size(); i++) {
			WorkorderDTO w = new WorkorderDTO((int)workorders.get(i)[0], (int)workorders.get(i)[1]);
			list.add(w);	
		}
		return list;
	}
	
	public int getWorkOrderAlmacenero() {
		List<Object[]> workorders = db.executeQueryArray(SQL);
		return (int)workorders.get(0)[0];
	}
	 
	public int getWorkOrderPedido(String idWorkorder) {
		List<Object[]> workorders = db.executeQueryArray(SQL,idWorkorder);
		return (int)workorders.get(0)[0];
	}
	
	public List<ProductoAlmacen> getProductos(int idPedido){
		List<ProductoAlmacen> productos = new ArrayList<ProductoAlmacen>();
		List<Object[]> listDb = db.executeQueryArray(SQL, idPedido);
		for(int i = 0; i<listDb.size();i++) {
			ProductoAlmacen p = new ProductoAlmacen((int)listDb.get(i)[0],(int)listDb.get(i)[1],(String)listDb.get(i)[2],(int)listDb.get(i)[3],(int)listDb.get(i)[4],(int)listDb.get(i)[5]);
			productos.add(p);
		}
		
		return ordenarProductos(productos);
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
