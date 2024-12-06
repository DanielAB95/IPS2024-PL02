package modelo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database2;
import persistence.dto.ClienteDto;
import persistence.dto.PedidoDto;
import persistence.dto.Queries;

public class InformeVentasTipoUsuarioModel {
	private Database2 db;
	private List<ClienteDto> clientes = new ArrayList<ClienteDto>();
	
	
	public InformeVentasTipoUsuarioModel(Database2 db) {
		this.db = db;
	}
	
	public InformeVentasTipoUsuarioModel() {
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
	
	public List<LocalDate> getFechas(){
		List<Object[]> result = db.executeQueryArray(Queries.Pedido.GET_FECHAS);
		List<LocalDate> fechas = new ArrayList<LocalDate>();
		for(Object[]o : result) {
			fechas.add(LocalDate.parse((String)o[0]));
		}
		return fechas;
	}
	
	public double getVentasTipoUsuarioDia(String tipoCliente, LocalDate fecha) {
		List<Object[]> result = db.executeQueryArray(Queries.Pedido.GET_VENTAS_TIPOUSUARIO_DIA, tipoCliente, fecha);
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

	public double getVentasTipoUsuarioTotal(String tipoCliente) {
		List<Object[]> result = db.executeQueryArray(Queries.Pedido.GET_VENTAS_TIPOUSUARIO_TOTAL, tipoCliente);
		if(result.get(0)[0] == null) {
			 return 0.0;
		 }else {
			return (double) result.get(0)[0];
		 }	
	}
}
