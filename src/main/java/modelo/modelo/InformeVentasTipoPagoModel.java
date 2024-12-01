package modelo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.ClienteDto;
import persistence.dto.PedidoDto;

public class InformeVentasTipoPagoModel {
	private Database2 db;
	private List<ClienteDto> clientes = new ArrayList<ClienteDto>();
	private List<PedidoDto> pedidos = new ArrayList<PedidoDto>();	
	private final static String GET_FECHAS = "select distinct p.fecha from Pedido p order by p.fecha";
	
	private final static String GET_VENTAS_TIPOPAGO_DIA = "select sum(precio) as precioDia from Pedido  " 
	+ "where tipoPago = ? and fecha = ?";
	
	private final static String GET_VENTAS_TIPOPAGO_TOTAL = "select sum(p.Precio) as precioTotal "
	+ "from Pedido p " 
	+ "where p.tipoPago = ?";

	
	public InformeVentasTipoPagoModel(Database2 db) {
		this.db = db;
	}
	
	public InformeVentasTipoPagoModel() {
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
	
	
	public List<LocalDate> getFechas(){
		List<Object[]> result = db.executeQueryArray(GET_FECHAS);
		List<LocalDate> fechas = new ArrayList<LocalDate>();
		for(Object[]o : result) {
			fechas.add(LocalDate.parse((String)o[0]));
		}
		return fechas;
	}
	
	public double getVentasTipoPagoDia(String tipoPago, LocalDate fecha) {
		List<Object[]> result = db.executeQueryArray(GET_VENTAS_TIPOPAGO_DIA, tipoPago, fecha);
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

	public double getVentasTipoPagoTotal(String tipoPago) {
		List<Object[]> result = db.executeQueryArray(GET_VENTAS_TIPOPAGO_TOTAL, tipoPago);
	
		if(result.get(0)[0] == null) {
			 return 0.0;
		 }else {
			return (double) result.get(0)[0];
		 }	
	}
}
