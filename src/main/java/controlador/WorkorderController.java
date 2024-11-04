package controlador;

import java.awt.Component;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import modelo.modelo.WorkorderModel;
import persistence.dto.PedidoDto;
import persistence.dto.WorkorderDto;
import vista.WorkorderView;

public class WorkorderController {
	
	private WorkorderView view;
	private WorkorderModel model;
	
	public WorkorderController(WorkorderModel model) {
		this.model = model; 
		
	}
	
	public void setView(WorkorderView workorderView) {
		this.view = workorderView;
	}
	
	public void init() {
		addWorkordersTable();
	}
	
	private void addWorkordersTable() {
		vaciarTabla();
		view.getTableModel().addColumn("Workorder");
		view.getTableModel().addColumn("ID Almacenero");
		view.getTableModel().addColumn("Pedido");
		view.getTableModel().addColumn("Numero de productos");
		for (WorkorderDto workorder : model.obtainWorkorders()) {
			for (PedidoDto pedido : workorder.pedidos) {
				Object[] data = {workorder.idWorkorder, workorder.idAlmacenero, pedido.idPedido, model.getCantidadTotalDeProductos(pedido)};
				view.getTableModel().addRow(data);
			}
		}
		ajustarTabla();
	}
	
	private void vaciarTabla() {
		view.getTableModel().setColumnCount(0);
		view.getTableModel().setRowCount(0);
	}
	
	private void ajustarTabla() {
		for (int i = 0; i < view.getTable().getColumnCount(); i++) {
            TableColumn column = view.getTable().getColumnModel().getColumn(i);
            int width = 0;
            for (int j = 0; j < view.getTable().getRowCount(); j++) {
                TableCellRenderer renderer = view.getTable().getCellRenderer(j, i);
                Component comp = renderer.getTableCellRendererComponent(view.getTable(), view.getTable().getValueAt(j, i), false, false,i, j);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            column.setPreferredWidth(width + view.getTable().getIntercellSpacing().width);
        }
	}
}
