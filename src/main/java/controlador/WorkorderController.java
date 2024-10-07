package controlador;

import java.util.List;

import javax.swing.table.TableModel;

import giis.demo.util.SwingUtil;
import modelo.dto.AlmaceneroDTO;
import modelo.dto.PedidoDTO;
import modelo.dto.PedidoProductoDTO;
import modelo.modelo.ClienteModel;
import modelo.modelo.PedidoProductoModel;
import modelo.modelo.WorkorderModel;
import vista.WorkorderView;

public class WorkorderController {
	private WorkorderView view;
	private WorkorderModel model;
	private PedidoProductoModel pModel;
	
	
	
	//private workOrderModel model;
	public WorkorderController(WorkorderView view ,WorkorderModel model) {
		this.view = view;
		this.model = model; 
		pModel = new PedidoProductoModel();
		
	}
	
	public void initView() {
		view.getTextPedido().setText("Falta ");
		view.getTextAlmacenero().setText("Falta ");
		this.getProductos();
	}
	
	public void initController() {
		
	}
	
	public void getProductos() {
//		List<PedidoProductoDTO> pedidos = pModel.getProductosPorPedido(idPedido);
//		TableModel tmodel = SwingUtil.getTableModelFromPojos(pedidos, new String[] {"idPedido", "idProducto", "cantidad", "descripcion"});
//		view.getTablaProductos().setModel(tmodel);
//		SwingUtil.autoAdjustColumns(view.getTablaProductos());
	}
}
