package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.modelo.InformeVentasTipoPagoModel;
import vista.InformeMenuView;
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
		Object[] columnNames = {"fecha/tipoPago","Tarjeta", "Contrarrembolso", "Transferencia", "Total(€)"};
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
			precios = new ArrayList<>();
			for(String tipoPago : tipos) {	
				precios.add(model.getVentasTipoPagoDia(tipoPago, fecha));
			}
			
			Object[] filaNueva = {fecha,precios.get(0),precios.get(1), precios.get(2),calulaTotal(precios)};
			tbVentasModel.addRow(filaNueva);
			
		}	
		precios = new ArrayList<>();
		for(String tipoPago : tipos) {	
			precios.add(model.getVentasTipoPagoTotal(tipoPago));	
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
