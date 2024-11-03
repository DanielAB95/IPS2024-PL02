package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import modelo.modelo.AlmaceneroModel;
import modelo.modelo.EmpaquetadoModel;
import modelo.modelo.PedidoModel;
import modelo.modelo.RecogidaModel2;
import modelo.modelo.WorkorderModel;
import persistence.dto.AlmaceneroDto;
import persistence.dto.PedidoDto;
import persistence.dto.ProductoDto;
import vista.EmpaquetadoView;
import vista.PedidoView;
import vista.RecogidaView2;
import vista.WorkorderView;

public class PedidoController {

	private PedidoModel model;
	private WorkorderModel wModel;
	private PedidoView view;
	private WorkorderView wView;
	private AlmaceneroModel aModel;

	public PedidoController(PedidoView view, PedidoModel model) {
		this.model = model;
		this.view = view;
		this.wModel = new WorkorderModel(view.getDatabase());
		this.wView = new WorkorderView(view.getDatabase());
		this.aModel = new AlmaceneroModel(view.getDatabase());
	}
	
	

	public void init() {
		setAlmacenero();
		addPedidosTable();
		accionWorkordersTabla();
		accionBtEmpaquetado();
		accionBtRecogida();
		accionBtGenerar();
		// view.getFrame().setVisible(true);
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
                	generarWorkorder();
                }
            }
        });
	}
	
	private void generarWorkorder() {

	}
	
	private void addPedidosTable() {
		vaciarTabla();
		view.getTableModel().addColumn("ID Pedido");
		view.getTableModel().addColumn("Numero de productos");
		view.getTableModel().addColumn("Fecha");
		for (PedidoDto pedido : model.getPedidos()) {
			Object[] data = {pedido.idPedido, getCantidadTotalDeProductos(pedido), pedido.fecha};
			view.getTableModel().addRow(data);
		}
		ajustarTabla();
	}
	
	private int getCantidadTotalDeProductos(PedidoDto dto) {
		int cantidad = 0;
		for (ProductoDto prod : dto.productos.keySet()) {
			cantidad += dto.productos.get(prod);
		}
		return cantidad;
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
                Component comp = renderer.getTableCellRendererComponent(view.getTablaPedidos(), view.getTablaPedidos().getValueAt(j, i), false, false,i, j);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            column.setPreferredWidth(width + view.getTablaPedidos().getIntercellSpacing().width);
        }
	}

	public void initController() {
		view.getTablaPedidos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = view.getTablaPedidos().getSelectedRow();
				if (row != -1) {
					int idPedido = (int) view.getTablaPedidos().getValueAt(row, 0);
					wModel.crearWorkorder(model.getAlmacenero().idAlmacenero);
					model.actualizarPedidoListo(idPedido);
				}
			}
		});
	}

	private void accionBtGenerar() {
		view.getButtonGenerarWorkOrders().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarWorkOrder();
				
			}
		});
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
	
	private void mostrarEmpaquetado() {
		EmpaquetadoModel em = new EmpaquetadoModel(view.getDatabase(), model.getAlmacenero().idAlmacenero);
		EmpaquetadoController ec = new EmpaquetadoController(em);
		EmpaquetadoView eView = new EmpaquetadoView(ec);
		view.dispose();
		eView.setVisible(true);	
	}
	
	private void mostrarRecogida() {
		RecogidaModel2 rm = new RecogidaModel2(view.getDatabase(), model.getAlmacenero().idAlmacenero);
		RecogidaController2 rc = new RecogidaController2(rm);
		RecogidaView2 rView = new RecogidaView2(rc);
		view.dispose();
		rView.setVisible(true);
	}
	
	private void mostrarWorkOrder() {
		// TODO Auto-generated method stub
		
	}
}
