package modelo.dto;

public class ClienteDTO {
	
	private String name;
	
	//asd
	public ClienteDTO(String nombre) {
		setName(nombre);
	}


	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		this.name = n;
	}

}
