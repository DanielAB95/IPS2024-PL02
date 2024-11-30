package controlador;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import modelo.modelo.InformeVentasModel;
import persistence.dto.ClienteDto;
import persistence.dto.ProductoDto;
import vista.InformeRepuestoView;
import vista.InformeVentasTipoMinoristaView;

public class InformeVentasTipoMinoristaController {
	
	private InformeVentasTipoMinoristaView view;
	private InformeVentasModel model;
	private DefaultTableModel tbVentasModel;
	
	public InformeVentasTipoMinoristaController() {
		this.model =  new InformeVentasModel();
	}
	
	public InformeVentasTipoMinoristaController(InformeVentasModel model) {
		this.model = model;
	}
	
	
	public void init() {
		columnasTabla();
		valoresTabla();
	}
	
	private void columnasTabla() {
		model.getMinoristas();
		List<ClienteDto> minoristas = model.getClientes();
		Object[] columnNames = new Object[minoristas.size()+2];
		columnNames[0] = "fehcha/minorista";
		for(int i = 0; i < minoristas.size();i++) {
			columnNames[i+1] = minoristas.get(i).idCliente;
		}
		columnNames[columnNames.length-1] = "Total";
		tbVentasModel = new DefaultTableModel(columnNames,0);
		view.getTablaVentas().setModel(tbVentasModel);
	}
	

	public void setView(InformeVentasTipoMinoristaView view) {
		this.view = view;
	}

	private void valoresTabla() {
//		limpiarModelo();
//		List<ProductoDto> productos = model.productosReponer();
//		for(ProductoDto dto : productos) {
//			int cantidadRepuesto = dto.stockReposicion - dto.stock;
//			
//		}	
	}
	
	
	private void limpiarModelo() {
		for(int i = tbVentasModel.getRowCount()-1  ; i>=0; i++) {
			tbVentasModel.removeRow(i);
		}	
	}
	
}
