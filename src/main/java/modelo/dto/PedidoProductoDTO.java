package modelo.dto;

public class PedidoProductoDTO {
	 private int idPedido;
	 private int idProducto;
	 private int cantidad;
	 private String descripcion;
	 
	 public PedidoProductoDTO(int idPedido, int idProducto, int cantidad, String descripcion){
		 this.idPedido = idPedido;
		 this.idProducto = idProducto;
		 this.cantidad = cantidad;
		 this.descripcion = descripcion; 
	 }
	 
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	 
	 
}
