package modelo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.AlmaceneroDto;
import persistence.dto.Queries;

public class InformeAlmaceneroModel {
	
	private Database2 db;
	private List<AlmaceneroDto> almaceneros = new ArrayList<>();
	
	public InformeAlmaceneroModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
		findAllAlmaceneros();
	}
	
	public InformeAlmaceneroModel(Database2 db) {
		this.db = db;
		findAllAlmaceneros();
	}
	
	private void findAllAlmaceneros() {
		List<Object[]> result = db.executeQueryArray(Queries.Almacenero.FIND_ALL);
		
		for (Object[] o : result) {
			AlmaceneroDto alm = new AlmaceneroDto();
			alm.idAlmacenero = (int)o[0];
			alm.nombre = (String)o[1];
			alm.apellido = (String)o[2];
			almaceneros.add(alm);
		}
	}

	public List<AlmaceneroDto> getAlmaceneros() {
		return almaceneros;
	}
	
	public Integer[] getOptionFecha(LocalDate date, String option) {
		switch (option) {
		case "Workorders recogidas":
			return getOptionFechaQuery(date, Queries.Workorder.FINISHED_FROM_DATE);
        case "Productos recogidos":
        	return getOptionFechaQuery(date, Queries.Workorder.FIND_ALL_REGISTROS);
        case "Paquetes completados":
        	return getOptionFechaQuery(date, Queries.Paquete.FINISHED_FROM_DATE);
        case "Productos empaquetados":
        	return getOptionFechaQuery(date, Queries.Paquete.FIND_ALL_REGISTROS);
		}
		return null;
	}
	
	private Integer[] getOptionFechaQuery(LocalDate date, String query) {
		Integer[] array = new Integer[almaceneros.size()+1];
		Arrays.fill(array, 0);
		Integer total = 0;
		List<Object[]> result = db.executeQueryArray(query, date);
		for (Object[] o : result) {
			array[(int)o[1]-1] = (int)o[0];
			total += (int)o[0];
		}
		array[array.length-1] = total;
		return array;
	}
	
	public LocalDate getPrimeraFecha(String option) {
		List<Object[]> result;
		switch (option) {
		case "Workorders recogidas":
			result = db.executeQueryArray(Queries.Workorder.FIRST_DATE);
            break;
        case "Productos recogidos":
        	result = db.executeQueryArray(Queries.Workorder.FIRST_DATE_REGISTRO);
            break;
        case "Paquetes completados":
        	result = db.executeQueryArray(Queries.Paquete.FIRST_DATE);
            break;
        case "Productos empaquetados":
        	result = db.executeQueryArray(Queries.Paquete.FIRST_DATE_REGISTRO);
            break;
		default:
			result = db.executeQueryArray(Queries.Workorder.FIRST_DATE);
		}
		if (result.get(0)[0] == null) return null;
		LocalDate date = LocalDate.parse((String)result.get(0)[0]);
		return date;
	}
	
	public LocalDate getNextFecha(LocalDate date, String option) {
		List<Object[]> result;
		switch (option) {
		case "Workorders recogidas":
			result = db.executeQueryArray(Queries.Workorder.NEXT_DATE, date);
            break;
        case "Productos recogidos":
        	result = db.executeQueryArray(Queries.Workorder.NEXT_DATE_REGISTRO, date);
            break;
        case "Paquetes completados":
        	result = db.executeQueryArray(Queries.Paquete.NEXT_DATE, date);
            break;
        case "Productos empaquetados":
        	result = db.executeQueryArray(Queries.Paquete.NEXT_DATE_REGISTRO, date);
            break;
		default:
			result = db.executeQueryArray(Queries.Workorder.NEXT_DATE, date);
		}
		if (result.get(0)[0] == null) return null;
		LocalDate res = LocalDate.parse((String)result.get(0)[0]);
		return res;
	}

	public Database2 getDB() {
		return db;
	}
	
}
