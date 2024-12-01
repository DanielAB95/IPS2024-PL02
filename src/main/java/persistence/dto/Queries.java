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
		public static final String FIRST_DATE = "select min(fecha) from Workorder where workorderEstado in ('Listo', 'Empaquetada')";
		public static final String NEXT_DATE = "select min(fecha) from Workorder "
											 + "where workorderEstado in ('Listo', 'Empaquetada') "
											 + "and fecha > ?";
		public static final String FINISHED_FROM_DATE = "select count(*), idAlmacenero "
													  + "from Workorder where fecha = ? "
													  + "group by idAlmacenero";
	}
	
	public class Paquete {
		public static final String FIND_READY = "select idPaquete, idPedido, fecha "
											  + "from Paquete "
											  + "where paqueteEstado = 'Listo'";
		
		public static final String FIND_PRODUCTOS = "select idProducto, cantidad "
												  + "from PaqueteProducto "
												  + "where idPaquete = ?";
		public static final String FIRST_DATE = "select min(fecha) from Paquete where paqueteEstado in ('Listo')";
		public static final String NEXT_DATE = "select min(fecha) from Paquete "
				 + "where paqueteEstado in ('Listo') "
				 + "and fecha > ?";
		public static final String FINISHED_FROM_DATE = "select count(*), idAlmacenero "
				  + "from Paquete where fecha = ? "
				  + "group by idAlmacenero";
	}
	
	public class Almacenero {
		public static final String FIND_ALL = "select idAlmacenero, nombre, apellido "
											+ "from almacenero "
											+ "where idAlmacenero > 0";
				
	}

}
