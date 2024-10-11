package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.TableModel;

import giis.demo.util.SwingUtil;
import modelo.dto.PedidoDTO;
import modelo.dto.Producto;
import modelo.modelo.PedidoModel;
import modelo.modelo.WorkorderModel;
import vista.PedidoView;
import vista.WorkorderView;

public class PedidoController {
	
	private PedidoModel model;
	private WorkorderModel wModel;
	private PedidoView view;
	private WorkorderView wView;
	
	public PedidoController(PedidoView view, PedidoModel model) {
		this.model = model;
		this.view = view;
		this.wModel = new WorkorderModel();
		this.wView = new WorkorderView();
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
                	 //int idAlmacenero = model.getIdAlmacenero(idPedido);
                	 //wModel.crearWorkorder(idAlmacenero, idPedido);
                	 
                     mostrarWorkorder(idPedido);
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
	
	public void getListaProductos(int idPedido) {
		List<Producto> pedidos = wModel.getProductos(idPedido);
		TableModel tmodel = SwingUtil.getTableModelFromPojos(pedidos, new String[] {"id", "nombre","categoria", "descripcion", "precio", "localizacion"});
		wView.getTablaProductos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaPedidos());
	}
	
	private void mostrarWorkorder(int idPedido) {
		wView.getTextPedido().setText(String.valueOf(idPedido));
		getListaProductos(idPedido);
		wView.getFrame().setVisible(true);
	}
}
