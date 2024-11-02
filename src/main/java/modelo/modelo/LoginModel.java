package modelo.modelo;

import java.util.List;

import giis.demo.util.Database2;

public class LoginModel {
	
	private static final String SQL_GET_CLIENTE = "select * from cliente where nombreUsuario = ?";
	private Database2 db;
	
	
	public LoginModel(Database2 db) {
		this.db = db;
	}
	
	public boolean doesClientExist(String name) {
		List<Object[]> usuario = db.executeQueryArray(SQL_GET_CLIENTE, name);
		
		if (usuario.isEmpty())
			return false;
		
		return true;
		
	}
	
}
