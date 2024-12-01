package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelo.modelo.InformeAlmaceneroModel;
import persistence.dto.AlmaceneroDto;
import vista.InformeAlmaceneroView;
import vista.InformeMenuView;

public class InformeAlmaceneroController {
	
	private InformeAlmaceneroView view;
	private InformeAlmaceneroModel model;
	
	public InformeAlmaceneroController(InformeAlmaceneroModel model) {
		this.model = model;
	}
	
	public InformeAlmaceneroController() {
		model = new InformeAlmaceneroModel();
	}
	
	public void setView(InformeAlmaceneroView view) {
		this.view = view;
	}
	
	public void init() {
		showAlmaceneros();
		actionBtInformes();
		actionComboBox();
		view.getComboBox().setSelectedIndex(0);
	}
	
	private void actionBtInformes() {
		view.getBtInformes().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InformeMenuController menucont = new InformeMenuController(model.getDB());
				InformeMenuView menuview = new InformeMenuView(menucont);
				view.dispose();
				menuview.setVisible(true);
				
			}
		});
	}
	
	private void actionComboBox() {
		view.getComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) view.getComboBox().getSelectedItem();
                showOption(selectedOption);
            }
        });
	}
	
	private void vaciarTabla() {
		view.getTableModel().setRowCount(0);
	}

	private void showAlmaceneros() {
		view.getTableModel().addColumn("Fecha");
		for (AlmaceneroDto alm : model.getAlmaceneros()) {
			String data = alm.idAlmacenero + " - " + alm.nombre + " " + alm.apellido;
			view.getTableModel().addColumn(data);
		}
		view.getTableModel().addColumn("Total");
		ajustarTabla();
	}
	
	private void showOption(String option) {
		vaciarTabla();
		LocalDate date = model.getPrimeraFecha(option);
		do {
			ArrayList<Object> data = new ArrayList<>(Arrays.asList(model.getOptionFecha(date, option)));
			data.add(0, date);
			view.getTableModel().addRow(data.toArray());
			date = model.getNextFecha(date, option);
		} while(date != null);
		view.getTableModel().addRow(getTotalAlmacenero());
	}
	
	private Object[] getTotalAlmacenero() {
		List<Object> result = new ArrayList<>();
		result.add("Total");
		for (int i = 1; i<view.getTableModel().getColumnCount(); i++) {
			result.add(getTotal(i));
		}
		return result.toArray();
	}


	private int getTotal(int column) {
		int total = 0;
		for (int i = 0; i < view.getTableModel().getRowCount(); i++) {
            int value = (int)view.getTableModel().getValueAt(i, column);
            total += value;
		}
		return total;
	}

	private void ajustarTabla() {
		for (int i = 0; i < view.getTable().getColumnCount(); i++) {
            view.getTable().getColumnModel().getColumn(i).setPreferredWidth(125);
        }
	}

}
