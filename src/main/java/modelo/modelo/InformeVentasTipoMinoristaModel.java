package modelo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.ClienteDto;
import persistence.dto.PedidoDto;


public class InformeVentasTipoMinoristaModel {
	private Database2 db;
	private List<ClienteDto> clientes = new ArrayList<ClienteDto>();
	private List<PedidoDto> pedidos = new ArrayList<PedidoDto>();
	
	private final static String GET_FECHAS = "select p.fecha from Pedido p " 
												+ "inner join Cliente c on p.idCliente = c.idCliente "
												+ "where c.tipoCliente = 'EMPRESA' "
												+ "order by p.fecha";
	private final static String GET_MINORISTAS = "select idCliente, nombre from Cliente where tipoCliente = 'EMPRESA'";
	private final static String GET_VENTAS_MINORISTAS_DIA = "select sum(p.precio) as precioDia from Pedido p " 
															+ "inner join Cliente c on  p.idCliente = c.idCliente "
															+ "where c.idCliente = ? and fecha = ?";
	private final static String GET_VENTAS_MINORISTAS_TOTAL = "select  sum(p.Precio) as precioTotal "
															+ "from Pedido p " 
															+ "inner join Cliente c on  p.idCliente = c.idCliente "
															+ "where c.idCliente = ?";

	public InformeVentasTipoMinoristaModel(Database2 db) {
		this.db = db;
	}
	
	public InformeVentasTipoMinoristaModel() {
		db = new Database2();
		db.createDatabase(false);
		db.loadDatabase();
	}
	
	public Database2 getDb() {
		return this.db;
	}
	
	public List<ClienteDto> getClientes(){
		return this.clientes;
	}
	
	public List<PedidoDto> getPedidos(){
		return this.pedidos;
	}
	
	
	public void getMinoristas() {
		List<Object[]> result = db.executeQueryArray(GET_MINORISTAS);
		for(Object[]o : result) {
			ClienteDto cliente = new ClienteDto();
			cliente.idCliente = (String)o[0];
			cliente.nombre = (String)o[1];
			clientes.add(cliente);
		}
	}
	
	public List<LocalDate> getFechas(){
		List<Object[]> result = db.executeQueryArray(GET_FECHAS);
		List<LocalDate> fechas = new ArrayList<LocalDate>();
		for(Object[]o : result) {
			fechas.add(LocalDate.parse((String)o[0]));
		}
		return fechas;
	}
	
	public double getVentasMinoristaDia(String idCliente, LocalDate fecha) {
		List<Object[]> result = db.executeQueryArray(GET_VENTAS_MINORISTAS_DIA, idCliente, fecha);
		PedidoDto pedido = new PedidoDto();
		for(Object[]o : result) {
			 if(o[0] == null) {
				 pedido.precio =  0.0;
			 }else {
				 pedido.precio =  ((Number) o[0]).doubleValue();
			 }	
		}	
		return pedido.precio;
	}
	
	public double getVentasMinoristaTotal(String idCliente) {
		List<Object[]> result = db.executeQueryArray(GET_VENTAS_MINORISTAS_TOTAL, idCliente);
	
		if(result.get(0)[0] == null) {
			 return 0.0;
		 }else {
			return ((Number) result.get(0)[0]).doubleValue();
		 }	
	}
	
	
}
