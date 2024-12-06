package modelo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.AlmaceneroDto;
import persistence.dto.ClienteDto;
import persistence.dto.PaqueteDto;
import persistence.dto.Queries;
import persistence.dto.VehiculoDto;

public class CargaPaqueteModel {

	AlmaceneroDto almacenero = new AlmaceneroDto();
	private Database2 db;
	private List<PaqueteDto> paquetes = new ArrayList<PaqueteDto>();
	private List<ClienteDto> clientes = new ArrayList<ClienteDto>();
	private VehiculoDto vehiculo;
	
	public CargaPaqueteModel(Database2 db, int idAlmacenero) {
		this.db = db;
		almacenero.idAlmacenero = idAlmacenero;
		setAlmacenero();
	}
	
	private void setAlmacenero() {
		List<Object[]> o = db.executeQueryArray(Queries.Almacenero.FIND_FROM_ID, almacenero.idAlmacenero);
		almacenero.nombre = (String)o.get(0)[1];
		almacenero.apellido = (String)o.get(0)[2];
	}
	
	public AlmaceneroDto getAlmacenero() {
		return almacenero;
	}
	
	public Database2 getDb() {
		return this.db;
	}
	
	public CargaPaqueteModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
		almacenero.idAlmacenero = 1;
		setAlmacenero();
	}
	
	public List<ClienteDto> getClientes() {
		return this.clientes;
	}
	
	public List<PaqueteDto> getPaquetes() {
		return this.paquetes;
	}
	
	public VehiculoDto getVehiculo() {
		return this.vehiculo;
	}
	
	public void addVehiculo(String matricula, String zonaReparto ) {
		db.executeUpdate(Queries.Vehiculo.ADD_VEHICULO, matricula, zonaReparto, LocalDate.now());
	}
	
	public void addPaquete(String matricula, int idPaquete) {
		db.executeUpdate(Queries.Vehiculo.ADD_PAQUETE, matricula, idPaquete);
	}
	
	public void paquetesZonaRegional() {
		List<Object[]> result = db.executeQueryArray(Queries.Paquete.GET_PAQUETES_ZONA_REGIONAL);
		ClienteDto cliente;
		PaqueteDto paquete;
		for(Object[] o : result) {
			cliente = new ClienteDto();
			paquete = new PaqueteDto();
			paquete.idPaquete = (int)o[0];
			paquete.fecha = LocalDate.parse((String)o[1]);
			cliente.nombre = (String)o[2];
			paquetes.add(paquete);
			clientes.add(cliente);
		}
	}
	
	public void paquetesZonaNacional() {
		List<Object[]> result = db.executeQueryArray(Queries.Paquete.GET_PAQUETES_ZONA_NACIONAL);
		ClienteDto cliente;
		PaqueteDto paquete;
		for(Object[] o : result) {
			cliente = new ClienteDto();
			paquete = new PaqueteDto();
			paquete.idPaquete = (int)o[0];
			paquete.fecha = LocalDate.parse((String)o[1]);
			cliente.nombre = (String)o[2];
			paquetes.add(paquete);
			clientes.add(cliente);
		}
	}

	public void cleanList() {
		 for (int i = paquetes.size() - 1; i >= 0; i--) {
			paquetes.remove(i);
			clientes.remove(i);
		}
		
	}

	public void paqueteReparto(int idPaquete) {
		db.executeUpdate(Queries.Paquete.UPDATE_ESTADO_PAQUETE, "En Reparto", idPaquete);
		
	}

	public void getVehiculoByMatricula(String matricula) {
		List<Object[]> result = db.executeQueryArray(Queries.Vehiculo.GET_VEHICULO_BY_MATRICULA, matricula);
		for(Object[] o : result) {
			vehiculo = new VehiculoDto();
			vehiculo.matricula = (String)o[0];
			vehiculo.zonaReparto = (String)o[1];
		}
	}	
}