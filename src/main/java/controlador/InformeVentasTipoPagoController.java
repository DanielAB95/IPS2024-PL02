package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.dto.PedidoDTO;
import modelo.modelo.InformeVentasTipoPagoModel;
import persistence.dto.ClienteDto;
import persistence.dto.PedidoDto;
import vista.InformeVentasTipoPagoView;

public class InformeVentasTipoPagoController {
	private InformeVentasTipoPagoModel model;
	private InformeVentasTipoPagoView view;
	private DefaultTableModel tbVentasModel;
	
	public InformeVentasTipoPagoController() {
		this.model =  new InformeVentasTipoPagoModel();
	}
	
	public InformeVentasTipoPagoController(InformeVentasTipoPagoModel model) {
		this.model = model;
	}
	
	
	public void init() {
		columnasTabla();
		valoresTabla();
	}
	
	private void columnasTabla() {
		Object[] columnNames = {"fecha/tipoPago", "Particular(Identificado)", "Tarjeta", "Contrarrembolso", "Transferencia"};
		tbVentasModel = new DefaultTableModel(columnNames,0);
		view.getTablaVentas().setModel(tbVentasModel);
	}
	

	public void setView(InformeVentasTipoPagoView view) {
		this.view = view;
	}

	private void valoresTabla() {
		List<LocalDate> fechas = model.getFechas();
		String[] tipos = {"Tarjeta","Contrarrembolso","Transferencia"};
		List<Double> precios = new ArrayList<>();
		for( LocalDate fecha : fechas) {
			for(String tipoPago : tipos) {
				precios = new ArrayList<>();
				PedidoDto pedido = new PedidoDto();
				//model.getVentasTipoPagoDia(tipoDePago);
				//precios.add(pedido.precio)
				
				
			}
			
			Object[] filaNueva = {fecha,precios.toArray(),calulaTotal(precios)};
			tbVentasModel.addRow(filaNueva);
			
		}	
		
		for(String tipoPago : tipos) {
			precios = new ArrayList<>();
			//precios.add(model.getVentasTipoPagoTotal(tipoDePago));	
		}
		Object[] filaNueva = {"Total",precios.toArray(),calulaTotal(precios)};
		tbVentasModel.addRow(filaNueva);
	}
	
	

	private double calulaTotal(List<Double> precios) {
		double total = 0.0;
		for(Double precio : precios) {
			total += precio;
		}
		return total;
	}

}
