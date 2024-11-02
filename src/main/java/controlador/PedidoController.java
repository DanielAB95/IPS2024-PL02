package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import giis.demo.util.SwingUtil;
import modelo.dto.PedidoDTO;
import modelo.dto.PedidoProductoDTO;
import modelo.dto.ProductoAlmacen;
import modelo.modelo.AlmaceneroModel;
import modelo.modelo.PedidoModel;
import modelo.modelo.WorkorderModel;
import vista.EmpaquetadoView;
import vista.PedidoView;
import vista.RecogidaView;
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

	public void initView() {
		this.getPedidos();
		// view.getFrame().setVisible(true);
	}

	public void initController() {
		view.getTablaPedidos().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = view.getTablaPedidos().getSelectedRow();
				if (row != -1) {
					int idPedido = (int) view.getTablaPedidos().getValueAt(row, 0);
					int idAlmacenero = Integer.parseInt(view.getTextAlmacenero().getText().substring(0, 1));
					wModel.crearWorkorder(idAlmacenero);
					model.actualizarPedidoListo(idPedido);
					actualizaTabla();
				}
			}
		});
		
		view.getButtonEmpaquetado().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarEmpaquetado();
				
			}
		});
		
		view.getButtonRecogida().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 mostrarRecogida();
				
			}
		});
		
		view.getButtonGenerarWorkOrders().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarWorkOrder();
				
			}
		});
	}

	public void getPedidos() {
		List<PedidoDTO> pedidos = model.getPedidos();
		TableModel tmodel = SwingUtil.getTableModelFromPojos(pedidos,
				new String[] { "idPedido", "numProductos", "fecha" });
		view.getTablaPedidos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaPedidos());
		if (tmodel.getRowCount() == 0) {
			view.getLabelPedidosPendientes().setVisible(true);
		}

	}

	private void actualizaTabla() {
		view.getTablaPedidos().removeAll();
		getPedidos();
		
	}
	
	private void mostrarEmpaquetado() {
		EmpaquetadoView eView = new EmpaquetadoView(view.getDatabase());
		view.dispose();
		eView.setVisible(true);	
	}
	
	private void mostrarRecogida() {
		RecogidaView rView = new RecogidaView(view.getDatabase());
		view.dispose();
		rView.setVisible(true);	
	}
	
	private void mostrarWorkOrder() {
		// TODO Auto-generated method stub
		
	}
}
