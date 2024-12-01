package controlador;

import java.time.LocalDate;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.modelo.InformeVentasTipoUsuarioModel;
import persistence.dto.ClienteDto;
import vista.InformeVentasTipoUsuarioView;

public class InformeVentasTipoUsuarioController {
	
	private InformeVentasTipoUsuarioView view;
	private InformeVentasTipoUsuarioModel model;
	private DefaultTableModel tbVentasModel;
	
	public InformeVentasTipoUsuarioController() {
		this.model =  new InformeVentasTipoUsuarioModel();
	}
	
	public InformeVentasTipoUsuarioController(InformeVentasTipoUsuarioModel model) {
		this.model = model;
	}
	
	public void init() {
		columnasTabla();
		valoresTabla();
	}
	
	private void columnasTabla() {
		
		
		Object[] columnNames = {"fecha/tipoUsuario", "Particular(Identificado)", "Particular(Anonimo)", "Minorista", "Total"};
		tbVentasModel = new DefaultTableModel(columnNames,0);
		view.getTablaVentas().setModel(tbVentasModel);
	}
	

	public void setView(InformeVentasTipoUsuarioView view) {
		this.view = view;
	}

	private void valoresTabla() {
		List<LocalDate> fechas = model.getFechas();
		//model.getVentasMinorista();
		for( LocalDate fecha : fechas) {
			Object[] filaNueva = {fecha.toString(),"0","0","0","0","0","0"};
			tbVentasModel.addRow(filaNueva);
		}	
		Object[] filaNueva = {"Total","0","0","0","0","0","0"};
		tbVentasModel.addRow(filaNueva);
	}
	
	
}
