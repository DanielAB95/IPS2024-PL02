package controlador;

import java.time.LocalDate;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.modelo.InformeVentasTipoMinoristaModel;
import persistence.dto.ClienteDto;

import vista.InformeVentasTipoMinoristaView;

public class InformeVentasTipoMinoristaController {
	
	private InformeVentasTipoMinoristaView view;
	private InformeVentasTipoMinoristaModel model;
	private DefaultTableModel tbVentasModel;
	
	public InformeVentasTipoMinoristaController() {
		this.model =  new InformeVentasTipoMinoristaModel();
	}
	
	public InformeVentasTipoMinoristaController(InformeVentasTipoMinoristaModel model) {
		this.model = model;
	}
	
	
	public void init() {
		columnasTabla();
		valoresTabla();
	}
	
	private void columnasTabla() {
		model.getMinoristas();
		List<ClienteDto> minoristas = model.getClientes();
		Object[] columnNames = new Object[minoristas.size()+2];
		columnNames[0] = "fecha/minorista";
		for(int i = 0; i < minoristas.size();i++) {
			columnNames[i+1] = minoristas.get(i).idCliente;
		}
		columnNames[columnNames.length-1] = "Total";
		tbVentasModel = new DefaultTableModel(columnNames,0);
		view.getTablaVentas().setModel(tbVentasModel);
	}
	

	public void setView(InformeVentasTipoMinoristaView view) {
		this.view = view;
	}

	private void valoresTabla() {
		List<LocalDate> fechas = model.getFechas();
		//model.getVentasMinorista();
		for( LocalDate fecha : fechas) {
			Object[] filaNueva = {fecha.toString(),"0","0","0","0","0","0"};
			tbVentasModel.addRow(filaNueva);
		}	
		//Object[] filaNueva = {CalcultaTotal()};
	}
		
//	private double[] CalcultaTotal() {
//		double total = 0.0;
//		for(ClienteDto cliente : model.getClientes() ) {
//		//	tbVentasModel.addColumn(cliente.idCliente, model.getVentaMinoristaTotal(cliente.idCliente));
//			//total += model.getVentaMinoristaTotal(cliente.idCliente);
//		}
//		Object[] col = {total};
//		//tbVentasModel.ad("Total", col);	
//		
//	}
	
	private void limpiarModelo() {
		for(int i = tbVentasModel.getRowCount()-1  ; i>=0; i++) {
			tbVentasModel.removeRow(i);
		}	
	}
}
