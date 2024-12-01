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
	
	private final static String GET_FECHAS = "select p.fecha from Pedido p ";
	
//	private final static String GET_VENTAS_TIPOPAGO_DIA = "select  precio from Pedido  " 
//	+ "where tipoPago = ? and fecha = ?";
//private final static String GET_VENTAS_TIPOPAGO_TOTAL = "select  sum(p.Precio) as precioTotal "
//	+ "from Pedido p " 
//	+ "where p.tipoPago = ?";

	
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
	
//	public void getVentasMinoristaDia(String tipoPago, LocalDate fecha) {
//	List<Object[]> result = db.executeQueryArray(GET_VENTAS_TIPOPAGO_DIA, idCliente, fecha);
//	
//	for(Object[]o : result) {
//		 PedidoDto pedido = new PedidoDto();
//		 pedido.fecha = LocalDate.parse((String)o[0]);
//		 //pedido.precio = (double)o[1];
//	}	
//}

//public double getVentasMinoristaTotal(String tipoPago) {
//	List<Object[]> result = db.executeQueryArray(GET_VENTAS_TIPOPAGO_DIA, idCliente);
//
//	return (double)result[0];
//}
}
