package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.table.TableModel;

import giis.demo.util.SwingUtil;
import modelo.dto.Pedido;
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
	private DefaultListModel<PedidoDTO> modeloPedido;
	
	public PedidoController(PedidoView view, PedidoModel model) {
		this.model = model;
		this.view = view;
		this.wModel = new WorkorderModel(view.getDatabase());
		this.wView = new WorkorderView(view.getDatabase());
		this.aModel = new AlmaceneroModel(view.getDatabase());
		modeloPedido = new DefaultListModel<PedidoDTO>();
		}
	 
	public void initView() {
		this.getListaPedidos();
		//view.getFrame().setVisible(true); 
	}

	public void initController() {
		view.getJListPedidos().addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(MouseEvent e) {   
	        	 int idPedido = view.getJListPedidos().getSelectedValue().getIdPedido();
	        	 int idAlmacenero = Integer.parseInt(view.getTextAlmacenero().getText().substring(0,1));
	        	 wModel.crearWorkorder(idAlmacenero, idPedido);
	        	 model.actualizarPedidoListo(idPedido); 
	        	 actualizarLista();
	             mostrarWorkorder(idPedido, idAlmacenero);
			}
		});
	}  
	
	public void getListaPedidos() {
			List<PedidoDTO> pedidos = model.getPedidos();
			rellenarModelo(pedidos);					
			view.getJListPedidos().setModel(modeloPedido);
			if(modeloPedido.isEmpty()) {
				view.getLabelPedidosPendientes().setVisible(true);
			}
			
	//		TableModel tmodel = SwingUtil.getTableModelFromPojos(pedidos, new String[] {"idPedido", "numProductos", "fecha"});
	//		view.getTablaPedidos().setModel(tmodel);
			//SwingUtil.autoAdjustColumns(view.getTablaPedidos());
		}

	private void rellenarModelo(List<PedidoDTO> pedidos) {
		for(PedidoDTO p : pedidos) {
			modeloPedido.addElement(p);
		}
		
	}

	private void actualizarLista() {
		modeloPedido.clear();
		getListaPedidos();
	}

	public void getListaProductos(int idPedido) {
		String texto = "";
		List<ProductoAlmacen> pedidos = wModel.getProductos(idPedido);
		for(ProductoAlmacen p : pedidos) {
			texto += p.toString();
			if(!p.equals(pedidos.get(pedidos.size()-1))) {
				texto += "\n" 
				+ "------------------------------------------------------------------------------------------------------" +
				"\n";		
			}
		}
		wView.getJTextProductos().setText(texto);
	}
	
	private void mostrarWorkorder(int idPedido, int idAlmacenero) {
		wView.getTextPedido().setText(String.valueOf(idPedido));
		//wView.getTextAlmacenero().setText(String.valueOf(idAlmacenero));
		wView.getTextAlmacenero().setText(aModel.getAlmacenero(idAlmacenero).toString());
		getListaProductos(idPedido);
		wView.getFrame().setVisible(true);
	}
}
