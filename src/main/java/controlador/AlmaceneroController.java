package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.modelo.AlmaceneroModel;
import vista.AlmaceneroInicioView;
import vista.PedidoView;

public class AlmaceneroController {
   private AlmaceneroInicioView view;
	private AlmaceneroModel model;
	List<Integer> ids = new ArrayList<Integer>();
	private int idAlmacenero;
	
	public AlmaceneroController(AlmaceneroInicioView view, AlmaceneroModel model) {
		this.view = view;
		this.model = model;
		this.ids = model.getIdExistente();
	}
	
	
	public void initView() {
		
	}
	
	
	public void initController() {
		view.getButtonContinuar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inicioSesion();
				initView();
			}			
		});
	}
	
	private void inicioSesion() {
		
		if (view.getTextIdAlmacenero().getText().isEmpty() || view.getTextIdAlmacenero().getText().equals(" ")) {
            JOptionPane.showMessageDialog(null, "porfavor, identifiquise");
            return;
        }
        try {
            idAlmacenero = Integer.parseInt(view.getTextIdAlmacenero().getText());  // Convertir el texto a número
            if (!ids.contains(idAlmacenero)) {
                JOptionPane.showMessageDialog(null, "Id Invalida o no existe");
            } else {
            	mostrarPedidosPendientes(idAlmacenero);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Id debe ser un numero");
        }
		
	}
	
	private void mostrarPedidosPendientes(int idAlmacenero) {
		PedidoView pv = new PedidoView(view.getDatabase());
		pv.getTextAlmacenero().setText(model.getAlmacenero(idAlmacenero).toString());
		view.dispose();
		pv.setVisible(true);
		pv.setLocationRelativeTo(view.getFrame());
		
	}
	
}
