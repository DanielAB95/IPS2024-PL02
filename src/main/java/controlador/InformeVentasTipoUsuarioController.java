package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.modelo.InformeVentasTipoUsuarioModel;
import vista.InformeMenuView;
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
		actionBtInformes();
		columnasTabla();
		valoresTabla();
	}
	
	private void actionBtInformes() {
		view.getButtonInforme().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InformeMenuController menucont = new InformeMenuController(model.getDb());
				InformeMenuView menuview = new InformeMenuView(menucont);
				view.dispose();
				menuview.setVisible(true);
				
			}
		});
		
	}

	private void columnasTabla() {
		
		
		Object[] columnNames = {"fecha/tipoUsuario", "Particular(Identificado)", "Particular(Anonimo)", "Minorista", "Total(€)"};
		tbVentasModel = new DefaultTableModel(columnNames,0);
		view.getTablaVentas().setModel(tbVentasModel);
	}
	

	public void setView(InformeVentasTipoUsuarioView view) {
		this.view = view;
	}

	private void valoresTabla() {
		List<LocalDate> fechas = model.getFechas();
		String[] tipos = {"PARTICULAR","INVITADO","EMPRESA"};
		List<Double> precios = new ArrayList<>();	
		for(LocalDate fecha : fechas) {
			precios = new ArrayList<>();
			for(String tipoUsuario : tipos) {	
				precios.add(model.getVentasTipoUsuarioDia(tipoUsuario, fecha));
			}
			
			Object[] filaNueva = {fecha.toString(),precios.get(0),precios.get(1), precios.get(2),calulaTotal(precios)};
			tbVentasModel.addRow(filaNueva);
		}	
		precios = new ArrayList<>();
		for(String tipoUsuario : tipos) {	
			precios.add(model.getVentasTipoUsuarioTotal(tipoUsuario));	
		}
		Object[] filaNueva = {"Total(€)",precios.get(0),precios.get(1), precios.get(2), calulaTotal(precios)};
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
