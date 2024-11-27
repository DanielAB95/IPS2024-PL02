package giis.demo.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;

//prueba 14:27
/**
 * Encapsula los datos de acceso JDBC, lectura de la configuracion
 * y scripts de base de datos para creacion y carga.
 */
public class Database2 extends DbUtil {
	//Localizacion de ficheros de configuracion y carga de bases de datos
	private static final String APP_PROPERTIES = "src/main/resources/application.properties";
	
	//MODIFICADOS PARA EL NUEVO schema2.sql y data2.sql
	private static final String SQL_SCHEMA = "src/main/resources/schema2.sql";
	private static final String SQL_LOAD = "src/main/resources/data2.sql";
	
	//ORIGINALES
	//private static final String SQL_SCHEMA = "src/main/resources/schema.sql";
	//private static final String SQL_LOAD = "src/main/resources/data.sql";
	
	//parametros de la base de datos leidos de application.properties (base de datos local sin usuario/password)
	private String driver;
	private String url;
	private static boolean databaseCreated=false;

	/**
	 * Crea una instancia, leyendo los parametros de driver y url de application.properties
	 */
	public Database2() {
		Properties prop=new Properties();
		try (FileInputStream fs=new FileInputStream(APP_PROPERTIES)) {
			prop.load(fs);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}
		driver=prop.getProperty("datasource.driver");
		url=prop.getProperty("datasource.url");
		if (driver==null || url==null)
			throw new ApplicationException("Configuracion de driver y/o url no encontrada en application.properties");
		DbUtils.loadDriver(driver);
	}
	public String getUrl() {
		return url;
	}
	/** 
	 * Creacion de una base de datos limpia a partir del script schema.sql en src/main/properties
	 * (si onlyOnce=true solo ejecutara el script la primera vez
	 */
	public void createDatabase(boolean onlyOnce) {
		//actua como singleton si onlyOnce=true: solo la primera vez que se instancia para mejorar rendimiento en pruebas
		if (!databaseCreated || !onlyOnce) { 
			executeScript(SQL_SCHEMA);
			databaseCreated=true; //NOSONAR
		}
	}
	/** 
	 * Carga de datos iniciales a partir del script data.sql en src/main/properties
	 * (si onlyOnce=true solo ejecutara el script la primera vez
	 */
	public void loadDatabase() {
		executeScript(SQL_LOAD);
	}
	
	
	public boolean isDatabaseEmpty() {
		try (Connection conn = DriverManager.getConnection(url)) {
	        String checkQuery = "SELECT COUNT(*) FROM Cliente"; // Cambia a una tabla clave
	        try (PreparedStatement ps = conn.prepareStatement(checkQuery);
	             ResultSet rs = ps.executeQuery()) {
	            return rs.next() && rs.getInt(1) == 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return true; // Por defecto, asumir que está vacía si hay un error
	    }
	}
}
