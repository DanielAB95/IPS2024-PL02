package modelo.dto;


public class PedidoDTO {
	public enum Estado{PENDIENTE, LISTO}
	
	private int idPedido;
	
	private int numProductos;
	
	private String fecha;
	
	private Estado estado;
	
	public PedidoDTO(int id,int n, String date) {
		this.idPedido = id;
		this.numProductos =n;
		this.fecha = date;
		this.estado = Estado.PENDIENTE;
	}

	/**
	 * Metodo getIdPedido
	 * @return idPedido
	 */

	public int getIdPedido() {
		return idPedido;
	}

	/**
	 * Metodo setIdPedido
	 * @param idPedido
	 */

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}



	/**
	 * Metodo getNumeroProductos
	 * @return NumeroProductos
	 */
	public int getNumProductos() {
		return numProductos;
	}

	/**
	 * Metodo setNumeroProductos
	 * @param numeroProductos
	 */
	public void setNumProductos(int numProductos) {
		this.numProductos = numProductos;
	}

	/**
	 *  Metodo getFechaPedido
	 * @return fechaPedido
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 *  Metodo setFechaPedido
	 * @param fechaPedido
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 *  Metodo getEstado
	 * @return estado
	 */
	public Estado getEstado() {
		return estado;
	}
	
	/**
	 * Metodo setEstado
	 * @param estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("IdPedido: ");
		sb.append(getIdPedido());
		sb.append(" | ");
		sb.append("Tamaño: ");
		sb.append(getNumProductos());
		sb.append(" | ");
		sb.append("Fecha: ");
		sb.append(getFecha());
		
		return sb.toString();
	}
	
	
	
}
