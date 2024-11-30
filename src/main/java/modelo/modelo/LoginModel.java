package modelo.modelo;

import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.ClienteDto;

public class LoginModel {
	
	private static final String SQL_GET_CLIENTE = "select * from cliente where nombreUsuario = ?";
	private Database2 db;
	
	
	public LoginModel(Database2 db) { // qw
		this.db = db;
	}
	
	public boolean doesClientExist(String name) {
		List<Object[]> usuario = db.executeQueryArray(SQL_GET_CLIENTE, name);
		
		if (usuario.isEmpty())
			return false;
		
		return true;
		
	}

	public void rellenaDto(ClienteDto dto) {
		List<Object[]> usuario = db.executeQueryArray(SQL_GET_CLIENTE, dto.nombreUsusario);
		
		dto.idCliente = (String) usuario.get(0)[0];
		dto.nombreUsusario = (String) usuario.get(0)[1];
		dto.nombre = (String) usuario.get(0)[2];
		dto.telefono = (String) usuario.get(0)[3];
		dto.pais = (String) usuario.get(0)[4];
		dto.region = (String) usuario.get(0)[5];
		dto.ciudad = (String) usuario.get(0)[6];
		dto.calle = (String) usuario.get(0)[7];
		dto.tipoCliente = (String) usuario.get(0)[8];
	}
	
}
