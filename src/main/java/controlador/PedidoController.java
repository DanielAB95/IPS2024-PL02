package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import modelo.modelo.CargaPaqueteModel;
import modelo.modelo.EmpaquetadoModel;
import modelo.modelo.PedidoModel;
import modelo.modelo.RecogidaModel;
import modelo.modelo.WorkorderModel;
import persistence.dto.AlmaceneroDto;
import persistence.dto.PedidoDto;
import vista.CargaPaqueteView;
import vista.EmpaquetadoView;
import vista.PedidoView;
import vista.RecogidaView;
import vista.WorkorderView;

public class PedidoController {

	private PedidoModel model;
	private PedidoView view;
	private List<Integer> woids;

	public PedidoController(PedidoView view, PedidoModel model) {
		this.model = model;
		this.view = view;
	}
	
	public void init() {
		setAlmacenero();
		addPedidosTable();
		accionWorkordersTabla();
		accionBtEmpaquetado();
		accionBtRecogida();
		accionCargaPaquetes();
	}

	private void setAlmacenero() {
		AlmaceneroDto dto = model.getAlmacenero();
		JTextField tx = (JTextField)view.getPnDatos().getComponent(1);
		tx.setText(dto.idAlmacenero + " - " + dto.nombre + " " + dto.apellido);
	}
	
	private void accionWorkordersTabla() {
		view.getTablaPedidos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                	int pos = view.getTablaPedidos().getSelectedRow();
                	generarWorkorder(pos);
                }
            }
        });
	}
	
	private void generarWorkorder(int posPedido) {
		woids = model.generarWorkorder(posPedido);
		addPedidosTable();
		WorkorderModel wm = new WorkorderModel(model.getDB(), woids);
		WorkorderController wc = new WorkorderController(wm);
		WorkorderView wv = new WorkorderView(wc);
		wv.setVisible(true);
	}
	
	private void addPedidosTable() {
		vaciarTabla();
		view.getTableModel().addColumn("ID Pedido");
		view.getTableModel().addColumn("Numero de productos");
		view.getTableModel().addColumn("Fecha");
		for (PedidoDto pedido : model.obtainPedidos()) {
			Object[] data = {pedido.idPedido, model.getCantidadTotalDeProductos(pedido), pedido.fecha};
			view.getTableModel().addRow(data);
		}
		if (model.obtainPedidos().size() == 0) view.getLabelPedidosPendientes().setVisible(true);
		ajustarTabla();
	}
	
	private void vaciarTabla() {
		view.getTableModel().setColumnCount(0);
		view.getTableModel().setRowCount(0);
	}
	
	private void ajustarTabla() {
		for (int i = 0; i < view.getTablaPedidos().getColumnCount(); i++) {
            TableColumn column = view.getTablaPedidos().getColumnModel().getColumn(i);
            int width = 0;
            for (int j = 0; j < view.getTablaPedidos().getRowCount(); j++) {
                TableCellRenderer renderer = view.getTablaPedidos().getCellRenderer(j, i);
                Component comp = renderer.getTableCellRendererComponent(
                		view.getTablaPedidos(), view.getTablaPedidos().getValueAt(j, i), false, false,i, j);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            column.setPreferredWidth(width + view.getTablaPedidos().getIntercellSpacing().width);
        }
	}

	private void accionBtRecogida() {
		view.getButtonRecogida().addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				 mostrarRecogida();
			}
		});
	}

	private void accionBtEmpaquetado() {
		view.getButtonEmpaquetado().addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarEmpaquetado();
			}
		});
	}
	
	private void accionCargaPaquetes() {
		view.getBtCargaProductos().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarCargaProductos();
				
			}	
		});
	}

	private void mostrarEmpaquetado() {
		EmpaquetadoModel em = new EmpaquetadoModel(view.getDatabase(), model.getAlmacenero().idAlmacenero);
		EmpaquetadoController ec = new EmpaquetadoController(em);
		EmpaquetadoView eView = new EmpaquetadoView(ec);
		view.dispose();
		eView.setVisible(true);	
	}
	
	private void mostrarRecogida() {
		RecogidaModel rm = new RecogidaModel(view.getDatabase(), model.getAlmacenero().idAlmacenero);
		RecogidaController rc = new RecogidaController(rm);
		RecogidaView rView = new RecogidaView(rc);
		view.dispose();
		rView.setVisible(true);
	}
	
	private void mostrarCargaProductos() {
		CargaPaqueteModel cpm = new CargaPaqueteModel(model.getDB(),model.getAlmacenero().idAlmacenero);
		CargaPaqueteController cpc = new CargaPaqueteController(cpm);
		CargaPaqueteView cpv = new CargaPaqueteView(cpc);
		view.dispose();
		cpv.setVisible(true);
		
	}
}
