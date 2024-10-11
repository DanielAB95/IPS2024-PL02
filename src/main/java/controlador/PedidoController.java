package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.table.TableModel;

import giis.demo.util.SwingUtil;
import modelo.dto.PedidoDTO;
import modelo.dto.ProductoAlmacen;
import modelo.modelo.AlmaceneroModel;
import modelo.modelo.PedidoModel;
import modelo.modelo.WorkorderModel;
import vista.PedidoView;
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
		this.wModel = new WorkorderModel();
		this.wView = new WorkorderView();
		this.aModel = new AlmaceneroModel();
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
                	 int idAlmacenero = Integer.parseInt(view.getTextAlmacenero().getText().substring(0,1));
                	 wModel.crearWorkorder(idAlmacenero, idPedido);
                	 
                     mostrarWorkorder(idPedido, idAlmacenero);
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
		List<ProductoAlmacen> pedidos = wModel.getProductos(idPedido);
		TableModel tmodel = SwingUtil.getTableModelFromPojos(pedidos, new String[] {"idProducto", "cantidad","descripcion", "pasillo", "estanteria", "posicionEstanteria"});
		wView.getTablaProductos().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaPedidos());
	}
	
	private void mostrarWorkorder(int idPedido, int idAlmacenero) {
		wView.getTextPedido().setText(String.valueOf(idPedido));
		//wView.getTextAlmacenero().setText(String.valueOf(idAlmacenero));
		wView.getTextAlmacenero().setText(aModel.getAlmacenero(idAlmacenero).toString());
		getListaProductos(idPedido);
		wView.getFrame().setVisible(true);
	}
}
