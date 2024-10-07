package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.TableModel;

import giis.demo.util.SwingUtil;
import modelo.dto.PedidoDTO;
import modelo.modelo.PedidoModel;
import modelo.modelo.WorkorderModel;
import vista.PedidoView;
import vista.WorkorderView;

public class PedidoController {
	
	private PedidoModel model;
	private WorkorderModel wModel = new WorkorderModel();
	private PedidoView view;
	
	public PedidoController(PedidoView view, PedidoModel model) {
		this.model = model;
		this.view = view;
	}
	
	public void initView() {
		this.getListaPedidos();
		//view.getFrame().setVisible(true); 
	}

	public void initController() {
		view.getTablaPedidos().addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {
                int row = view.getTablaPedidos().getSelectedRow();
                if (row != -1) { 
                	 int idPedido = (int) view.getTablaPedidos().getValueAt(row, 0);
                	
                	 wModel.crearWorkorder(model.getIdAlmacenero(idPedido), idPedido);
                     mostrarWorkorder();
                }
			}
		});
	} 

	public void getListaPedidos() {
		List<PedidoDTO> pedidos = model.getPedidos();
		TableModel tmodel = SwingUtil.getTableModelFromPojos(pedidos, new String[] {"idPedido", "numProductos", "fecha"});
		view.getTablaPedidos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaPedidos());
	}
	
	private void mostrarWorkorder() {
		WorkorderView wv = new WorkorderView();
		wv.getFrame().setVisible(true);
	}
}
