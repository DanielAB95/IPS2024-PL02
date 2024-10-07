package modelo.dto;

public class AlmaceneroDTO {
	private int idAlmacenero;
	private String nombre;
	private String apellido;
	
	public AlmaceneroDTO(int almaceneroId, String nombre, String apellido) {
		this.idAlmacenero = almaceneroId;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public int getIdAlmacenero() {
		return idAlmacenero;
	}

	public void setIdAlmacenero(int idAlmacenero) {
		this.idAlmacenero = idAlmacenero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getNombre());
		sb.append(" ");
		sb.append(getApellido());
		return sb.toString();
	}
}
