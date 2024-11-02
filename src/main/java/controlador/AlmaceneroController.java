package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.modelo.AlmaceneroModel;
import modelo.modelo.EmpaquetadoModel;
import vista.AlmaceneroInicioView;
import vista.AlmaceneroView;
import vista.EmpaquetadoView;
import vista.PedidoView;

public class AlmaceneroController {
	private AlmaceneroView view;
	private AlmaceneroInicioView viewId;
	private AlmaceneroModel model;
	List<Integer> ids = new ArrayList<Integer>();
	private int idAlmacenero;
	
	public AlmaceneroController(AlmaceneroInicioView viewId,AlmaceneroView view, AlmaceneroModel model) {
		this.viewId = viewId;
		this.view = view;
		this.model = model;
		this.ids = model.getIdExistente();
	}
	
	
	public void initView() {
		view.getTextAlmacenero().setText(model.getAlmacenero(idAlmacenero).toString());
	}
	
	
	public void initController() {
		viewId.getButtonContinuar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inicioSesion();
				initView();
				menuAlmacenero(idAlmacenero);
			}			
		});
	}
	
	private void inicioSesion() {
		
		if (viewId.getTextIdAlmacenero().getText().isEmpty() || viewId.getTextIdAlmacenero().getText().equals(" ")) {
            JOptionPane.showMessageDialog(null, "porfavor, identifiquise");
            return;
        }
        try {
            idAlmacenero = Integer.parseInt(viewId.getTextIdAlmacenero().getText());  // Convertir el texto a número
            if (!ids.contains(idAlmacenero)) {
                JOptionPane.showMessageDialog(null, "Id Invalida o no existe");
            } else {
                mostrarAlmaceneroView();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Id debe ser un numero");
        }
		
	}
	
	private void menuAlmacenero(int idAlmacenero) {
		view.getButtonPedidosPendientes().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPedidosPendientes(idAlmacenero);
			}
		});		
		
		view.getBtEmpaquetado().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				irAEmpaquetado();
			}
		});	
	}

	private void mostrarAlmaceneroView() {
		view.getFrame().setVisible(true);	
		view.getFrame().setLocationRelativeTo(viewId.getFrame());
		viewId.dispose();
	}
	
	private void irAEmpaquetado() {
		EmpaquetadoModel em = new EmpaquetadoModel(view.getDatabase(), idAlmacenero);
		EmpaquetadoController ec = new EmpaquetadoController(em);
		EmpaquetadoView ew = new EmpaquetadoView(ec);
		ew.setVisible(true);
		view.dispose();
	}
	
	private void mostrarPedidosPendientes(int idAlmacenero) {
		PedidoView pv = new PedidoView(viewId.getDatabase());
		pv.getTextAlmacenero().setText(model.getAlmacenero(idAlmacenero).toString());
		pv.setVisible(true);
		pv.setLocationRelativeTo(view.getFrame());
		
	}
	
}
