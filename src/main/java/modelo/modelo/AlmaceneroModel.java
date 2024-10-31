package modelo.modelo;

import java.util.ArrayList;
import java.util.List;


import giis.demo.util.Database2;
import modelo.dto.AlmaceneroDTO;

public class AlmaceneroModel {
	public static final String SQL_ALMACENERO = "select * from Almacenero where idAlmacenero =?";
	public static final String SQL_ALMACENERO_ID = "select idAlmacenero from Almacenero";
	public static final String SQL_ALMACENERO_PEDIDO = "select ap.idPedido from AlmaceneroPedido ap join Almacenero a on ap.idAlmacenero = a p.idAlmacenero and a.idAlmacenero = ?";
	
	private Database2 db=new Database2();
	
	public AlmaceneroModel(Database2 database) {
		this.db = database;
	}
	
	public AlmaceneroDTO getAlmacenero(int idAlmacenero) {
		List<Object[]> obj = db.executeQueryArray(SQL_ALMACENERO, idAlmacenero);
		AlmaceneroDTO almacenero = new AlmaceneroDTO((int)obj.get(0)[0], (String)obj.get(0)[1], (String)obj.get(0)[2]);
		return almacenero;
	}
	
	public List<Integer> getIdExistente(){
		List<Integer> ids = new ArrayList<Integer>();
		List<Object[]> list = db.executeQueryArray(SQL_ALMACENERO_ID);
		for(int i = 0; i<list.size();i++) {
			int id = (int)list.get(i)[0];
			ids.add(id);
		}
		return ids;
	}
	
	
}
