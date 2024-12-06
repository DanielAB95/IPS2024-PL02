package persistence.dto;

public class Queries {
	
	public class Workorder {
		public static final String UPDATE = "update Workorder set workorderEstado = ? where idWorkorder = ?";
		public static final String UPDATE_FECHA = "update Workorder set workorderEstado = ?, fecha = ? where idWorkorder = ?";
		public static final String UPDATE_PRODUCTO = "update WorkorderProducto set recogidos = recogidos + ? "
				  								    + "where idWorkorder = ? and idPedido = ? and idProducto = ?";
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
		public static final String FIRST_DATE_REGISTRO = "select min(fecha) from recogidos";
		public static final String NEXT_DATE_REGISTRO = "select min(fecha) from recogidos "
				 	 						 		  + "where fecha > ?";
		public static final String FINISHED_FROM_DATE = "select count(*), idAlmacenero "
													  + "from Workorder where fecha = ? "
													  + "group by idAlmacenero";
		public static final String FIND_ALL_REGISTROS = "select cantidad, idAlmacenero "
				  									  + "from recogidos where fecha = ?";
		public static final String FIND_REGISTRO = "select * from recogidos where idAlmacenero = ? and fecha = ?";
		public static final String INCREMENT_REGISTRO = "update recogidos set cantidad = cantidad + 1 "
				  									  + "where idAlmacenero = ? and fecha = ?";
		public static final String INSERT_REGISTRO = "insert into recogidos(idAlmacenero, cantidad, fecha) "
												   + "values(?,1,?)";
	}
	
	public class Paquete {
		public static final String UPDATE = "update Paquete set paqueteEstado = ?, fecha = ? where idPaquete = ?";
		public final static String INSERT = "insert into Paquete(idPaquete, idPedido, idAlmacenero, paqueteEstado) "
										  + "values (?,?,?,'En Curso')";
		public static final String FIND_READY = "select idPaquete, idPedido, fecha "
											  + "from Paquete "
											  + "where paqueteEstado = 'Listo'";
		public static final String MAX_NUM_PAQUETE = "select max(idPaquete) from Paquete";
		public static final String FIND_PRODUCTOS = "select idProducto, cantidad "
												  + "from PaqueteProducto "
												  + "where idPaquete = ?";
		public static final String FIRST_DATE = "select min(fecha) from Paquete where paqueteEstado in ('Listo')";
		public static final String NEXT_DATE = "select min(fecha) from Paquete "
				 	 						 + "where paqueteEstado in ('Listo') "
				 	 						 + "and fecha > ?";
		public static final String FIRST_DATE_REGISTRO = "select min(fecha) from empaquetados";
		public static final String NEXT_DATE_REGISTRO = "select min(fecha) from empaquetados "
				 	 						 		  + "where fecha > ?";
		public static final String FINISHED_FROM_DATE = "select count(*), idAlmacenero "
				  									  + "from Paquete where fecha = ? "
				  									  + "group by idAlmacenero";
		public static final String FIND_ALL_REGISTROS = "select cantidad, idAlmacenero "
													  + "from empaquetados where fecha = ?";
		public static final String FIND_REGISTRO = "select * from empaquetados where idAlmacenero = ? and fecha = ?";
		public static final String INCREMENT_REGISTRO = "update empaquetados set cantidad = cantidad + 1 "
													  + "where idAlmacenero = ? and fecha = ?";
		public static final String INSERT_REGISTRO = "insert into empaquetados(idAlmacenero, cantidad, fecha) "
												   + "values(?,1,?)";
		public static final String GET_PAQUETES_ZONA_REGIONAL = "select p.idPaquete, p.fecha, c.nombre "
				+ "from Paquete p inner join Pedido pe on p.idPedido = pe.idPedido "
				+ "inner join Cliente c on pe.idCliente = c.idCliente "
				+ "where c.region = 'Asturias' and  p.paqueteEstado = 'Listo'";
		
		public static final String GET_PAQUETES_ZONA_NACIONAL = "select p.idPaquete, p.fecha, c.nombre "
				+ "from Paquete p inner join Pedido pe on p.idPedido = pe.idPedido "
				+ "inner join Cliente c on pe.idCliente = c.idCliente "
				+ "where not c.region = 'Asturias' and  p.paqueteEstado = 'Listo'";
		public static final String UPDATE_ESTADO_PAQUETE = "update Paquete set paqueteEstado = ? where idPaquete = ?";
	}
	
	public class Almacenero {
		public static final String FIND_ALL = "select idAlmacenero, nombre, apellido "
											+ "from almacenero "
											+ "where idAlmacenero > 0";
		public static final String FIND_FROM_ID = "select idAlmacenero, nombre, apellido "
												+ "from almacenero "
												+ "where idAlmacenero = ?";
				
	}
	
	public class Vehiculo {
		public static final String ADD_VEHICULO = "insert into Vehiculo(matricula,zonaReparto,fecha) values (?,?,?)";
		
		public static final String GET_VEHICULO_BY_MATRICULA = "select matricula, zonaReparto from Vehiculo where matricula = ?";
		
		public static final String ADD_PAQUETE = "insert into VehiculoPaquete  (matricula,idPaquete) values (?,?)";
	}
	
	public class Producto {
		public  static final String FIND_PRODUCTOS_REPONER = "select id, nombre, descripcion, stock, stockReposicion from Producto where stock < minStock";
	}
	
	public class Cliente{
		public static final String GET_MINORISTAS = "select idCliente, nombre from Cliente where tipoCliente = 'EMPRESA'";
		public static final String GET_VENTAS_MINORISTAS_DIA = "select sum(p.precio) as precioDia from Pedido p " 
																+ "inner join Cliente c on  p.idCliente = c.idCliente "
																+ "where c.idCliente = ? and fecha = ?";
		public static final String GET_VENTAS_MINORISTAS_TOTAL = "select  sum(p.Precio) as precioTotal "
																+ "from Pedido p " 
																+ "inner join Cliente c on  p.idCliente = c.idCliente "
																+ "where c.idCliente = ?";
	}
	
	public class Pedido{
		public static final String GET_FECHAS = "select p.fecha from Pedido p " 
				+ "inner join Cliente c on p.idCliente = c.idCliente "
				+ "where c.tipoCliente = 'EMPRESA' "
				+ "order by p.fecha";
		
		public static final String GET_VENTAS_TIPOPAGO_DIA = "select sum(precio) as precioDia from Pedido  " 
				+ "where tipoPago = ? and fecha = ?";
				
		public static final String GET_VENTAS_TIPOPAGO_TOTAL = "select sum(p.Precio) as precioTotal "
				+ "from Pedido p " 
				+ "where p.tipoPago = ?";
		public static final  String GET_VENTAS_TIPOUSUARIO_DIA = "select sum(p.precio) as precioDia from Pedido p " 
				+ "inner join Cliente c on  p.idCliente = c.idCliente "
				+ "where c.tipoCliente = ? and fecha = ?";
				
		public static final String GET_VENTAS_TIPOUSUARIO_TOTAL = "select  sum(p.Precio) as precioTotal "
				+ "from Pedido p " 
				+ "inner join Cliente c on  p.idCliente = c.idCliente "
				+ "where c.tipoCliente = ?";
	}

}
