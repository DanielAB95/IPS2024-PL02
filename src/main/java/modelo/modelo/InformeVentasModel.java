package modelo.modelo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.ClienteDto;

public class InformeVentasModel {
	private Database2 db;
	private List<ClienteDto> clientes = new ArrayList<ClienteDto>();
	
	private final static String SQL_FIND_ALMACENERO = "select fecha from Pedido";
	private final static String GET_MINORISTAS = "select idCliente, nombre from Cliente where tipoCliente = 'EMPRESA'";
	
	public InformeVentasModel(Database2 db) {
		this.db = db;
	}
	
	public InformeVentasModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
	}
	
	public Database2 getDb() {
		return this.db;
	}
	
	public List<ClienteDto> getClientes(){
		return this.clientes;
	}
	
	
	public void getMinoristas() {
		List<Object[]> result = db.executeQueryArray(GET_MINORISTAS);
		for(Object[]o : result) {
			ClienteDto cliente = new ClienteDto();
			cliente.idCliente = (String)o[0];
			cliente.nombre = (String)o[1];
			clientes.add(cliente);
		}
	}
	
	
}
