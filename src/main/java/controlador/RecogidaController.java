package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.dto.Producto;
import modelo.modelo.RecogidaModel;
import vista.RecogidaView;

public class RecogidaController {
	
	private RecogidaModel rm;
	private RecogidaView rw;
	
	public RecogidaController(RecogidaView rw) {
		this.rm = new RecogidaModel();
		this.rw = rw;
	}
	
	public void initController() {
		addExit();
		idWorkorder();
		fillList();
	}
	
	private void addExit() {
		rw.getBtCancelar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void idWorkorder() {
		rw.getTxfWorkOrderId().setText(Integer.toString(rm.getWOID()));
	}
	
	private void fillList() {
		List<Producto> productos = rm.extractProducts();
		for (int i = 0; i<productos.size(); i++) 
			rw.getlistModel().addElement(productos.get(i));		
	}

}
