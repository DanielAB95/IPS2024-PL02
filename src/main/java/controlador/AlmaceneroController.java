package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.modelo.AlmaceneroModel;
import modelo.modelo.EmpaquetadoModel;
import vista.AlmaceneroInicioView;
import vista.EmpaquetadoView;
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
	
//	private void menuAlmacenero(int idAlmacenero) {
//		view.getButtonPedidosPendientes().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				mostrarPedidosPendientes(idAlmacenero);
//			}
//		});		
//		
//		view.getBtEmpaquetado().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				irAEmpaquetado();
//			}
//		});	
//	}

	private void mostrarAlmaceneroView() {
		view.getFrame().setVisible(true);	
		view.getFrame().setLocationRelativeTo(view.getFrame());
		view.dispose();
	}
	
	private void irAEmpaquetado() {
		EmpaquetadoModel em = new EmpaquetadoModel(view.getDatabase(), idAlmacenero);
		EmpaquetadoController ec = new EmpaquetadoController(em);
		EmpaquetadoView ew = new EmpaquetadoView(ec);
		ew.setVisible(true);
		view.dispose();
	}
	

	private void mostrarPedidosPendientes(int idAlmacenero) {
		PedidoView pv = new PedidoView(view.getDatabase(), idAlmacenero);
		view.dispose();
		pv.setLocationRelativeTo(view.getFrame());
		pv.setVisible(true);

	}
	
}
