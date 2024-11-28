package persistence.dto;

public class Queries {
	
	public class Workorder {
		public static final String UPDATE = "update Workorder set workorderEstado = ? where idWorkorder = ?";
		public static final String INSERT = "insert into Workorder(idWorkorder, idAlmacenero, workorderEstado, fecha) "
										  + "values (?,?,?,?)";
		public static final String PEDIDO_INSERT = "insert into WorkorderPedido(idWorkorder, idPedido) "
												 + "values (?,?)";
		public static final String PRODUCTO_INSERT = "insert into WorkorderProducto(idWorkorder, idPedido, idProducto, cantidad, recogidos) "
												   + "values (?,?,?,?,0)";
	}

}
