package modelo.modelo;

import java.time.LocalDate;
import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.ClienteDto;
import persistence.dto.PaqueteDto;

public class CargaPaqueteModel {

	private Database2 db;
	private ClienteDto cliente;
	private PaqueteDto paquete;
	
	private final static String ADD_VEHICULO = "insert into Vehiculo(matricula,zonaReparto) values (?,?)";
	private final static String GET_PAQUETES_ZONA_REGIONAL = "select p.idPaquete, p.fecha, c.nombre from Paquete p inner join Pedido pe on p.idPedido = pe.idPedido inner join Cliente on pe.idCliente = c.idCliente where c.region = 'Asturias'";
	private final static String GET_PAQUETES_ZONA_NACIONAL = "select p.idPaquete, p.fecha, c.nombre from Paquete p inner join Pedido pe on p.idPedido = pe.idPedido inner join Cliente on pe.idCliente = c.idCliente where not c.region = 'Asturias'";
	
	public CargaPaqueteModel(Database2 db) {
		this.db = db;

	}
	
	public CargaPaqueteModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
	}
	
	public void addVehiculo(String matricula, String zonaReparto ) {
		db.executeUpdate(ADD_VEHICULO, matricula, zonaReparto);
	}
	
	public ClienteDto getCliente() {
		return this.cliente;
	}
	
	public PaqueteDto getPaquete() {
		return this.paquete;
	}
	
	private void paquetesZonaRegional() {
		List<Object[]> result = db.executeQueryArray(GET_PAQUETES_ZONA_REGIONAL);
		for(Object[] o : result) {
			paquete.idPaquete = (int)o[0];
			paquete.fecha = (LocalDate)o[1];
			cliente.nombre = (String)o[2];
		}
	}
	
	private void paquetesZonaNacional() {
		List<Object[]> result = db.executeQueryArray(GET_PAQUETES_ZONA_NACIONAL);
		for(Object[] o : result) {
			paquete.idPaquete = (int)o[0];
			paquete.fecha = (LocalDate)o[1];
			cliente.nombre = (String)o[2];
		}
	}
	

	
}
