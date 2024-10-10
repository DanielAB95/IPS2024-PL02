package modelo.dto;

public class PedidoProductoDTO {
	 private int idPedido;
	 private int idProducto;
	 private int cantidad;
	 
	 public PedidoProductoDTO(int idPedido, int idProducto, int cantidad){
		 this.idPedido = idPedido;
		 this.idProducto = idProducto;
		 this.cantidad = cantidad;
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
	 
}
