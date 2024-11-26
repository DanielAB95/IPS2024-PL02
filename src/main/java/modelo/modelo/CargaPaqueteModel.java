package modelo.modelo;

import giis.demo.util.Database2;

public class CargaPaqueteModel {

	private Database2 db;
	
	private final static String ADD_VEHICULO = "insert into Vehiculo(matricula,zonaReparto) values (?,?)";
	private final static String GET_PAQUETES_ZONA = "select idPaquete, fecha from Paquete where ";
	
	
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
	
}
