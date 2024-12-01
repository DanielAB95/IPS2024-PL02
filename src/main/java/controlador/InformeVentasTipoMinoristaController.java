package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.modelo.InformeVentasTipoMinoristaModel;
import persistence.dto.ClienteDto;
import vista.InformeMenuView;
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
		model.getMinoristas();
		List<ClienteDto> minoristas = model.getClientes();
		Object[] columnNames = new Object[minoristas.size()+2];
		columnNames[0] = "fecha/minorista";
		for(int i = 0; i < minoristas.size();i++) {
			columnNames[i+1] = minoristas.get(i).idCliente;
		}
		columnNames[columnNames.length-1] = "Total(€)";
		tbVentasModel = new DefaultTableModel(columnNames,0);
		view.getTablaVentas().setModel(tbVentasModel);
	}
	

	public void setView(InformeVentasTipoMinoristaView view) {
		this.view = view;
	}

	private void valoresTabla() {
		List<LocalDate> fechas = model.getFechas();
		List<ClienteDto> minoristas = model.getClientes();
		List<Double> precios = new ArrayList<>();	
		for( LocalDate fecha : fechas) {
			precios = new ArrayList<>();
			for(ClienteDto minorista : minoristas) {
				precios.add(model.getVentasMinoristaDia(minorista.idCliente, fecha));
			}
			Object[] filaNueva = {fecha.toString(),precios.get(0),precios.get(1),precios.get(2),precios.get(3),precios.get(4),calulaTotal(precios)};
			tbVentasModel.addRow(filaNueva);
		}	
		precios = new ArrayList<>();
		for(ClienteDto minorista : minoristas) {	
			precios.add(model.getVentasMinoristaTotal(minorista.idCliente));	
		}
		Object[] filaNueva = {"Total(€)",precios.get(0),precios.get(1),precios.get(2),precios.get(3),precios.get(4), calulaTotal(precios)};
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
