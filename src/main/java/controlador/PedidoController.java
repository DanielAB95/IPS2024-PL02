package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.TableModel;

import giis.demo.util.SwingUtil;
import modelo.dto.PedidoDTO;
import modelo.modelo.AlmaceneroModel;
import modelo.modelo.EmpaquetadoModel;
import modelo.modelo.PedidoModel;
import modelo.modelo.RecogidaModel2;
import modelo.modelo.WorkorderModel;
import persistence.dto.AlmaceneroDto;
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
	
	

	public void initView() {
		AlmaceneroDto dto = model.getAlmacenero();
		JTextField tx = (JTextField)view.getPnDatos().getComponent(1);
		tx.setText(dto.idAlmacenero + " - " + dto.nombre + " " + dto.apellido);
		// view.getFrame().setVisible(true);
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
