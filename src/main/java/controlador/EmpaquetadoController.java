package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import modelo.dto.ProductoWrapper;
import modelo.dto.WorkorderDTO;
import modelo.dto.WorkorderWrapper;
import modelo.modelo.EmpaquetadoModel;
import modelo.modelo.FacturaModel;
import vista.EmpaquetadoView;
import vista.FacturaView;

public class EmpaquetadoController {
	
	private EmpaquetadoModel em;
	private EmpaquetadoView ew;
	
	public EmpaquetadoController(EmpaquetadoView ew) {
		this.em = new EmpaquetadoModel();
		this.ew = ew;
	}
	
	public EmpaquetadoController(EmpaquetadoView ew, EmpaquetadoModel em) {
		this.em = em;
		this.ew = ew;
	}
	
	public void init() {
		accionCancelar();
		fillComboBox();
		accionComboBox();
		ew.getCbWorkorders().setSelectedIndex(0);
		activarBotonComprobar();
		accionComprobar();
		activarAceptar();
		accionAceptar();
	}

	private void accionCancelar() {
		ew.getBtCancelar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void fillComboBox() {
		for (WorkorderDTO dto : em.workordersListas()) {
			WorkorderWrapper wo = new WorkorderWrapper(dto);
			ew.getCbWorkorders().addItem(wo);
		}
	}
	
	private void accionComboBox() {
		ew.getCbWorkorders().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ew.getListModel().clear();
                fillList();
            }
        });
	}
	
	private void fillList() {
		int id = ((WorkorderWrapper)ew.getCbWorkorders().getSelectedItem()).getInfo().idPedido;
		List<ProductoWrapper> productos = em.productosPorWorkorder(id);
		for (ProductoWrapper producto : productos) {
			ew.getListModel().addElement(producto);
		}	
	}
	
	private void activarBotonComprobar() {
		ew.getTextField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                toggleButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                toggleButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                toggleButton();
            }
            
            private void toggleButton() {
                ew.getBtComprobar().setEnabled(!ew.getTextField().getText().trim().isEmpty());
            }
        });
	}
	
	private void accionComprobar() {
		ew.getBtComprobar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comprobarID();
			}
		});
	}
	
	private void comprobarID() {
		int idProducto = Integer.parseInt(ew.getTextField().getText());
		List<ProductoWrapper> productos = new ArrayList<>();
		int tamaño = ew.getListModel().size();
		for (int i = 0; i<tamaño; i++) {
			productos.add(ew.getListModel().get(i));
		}
		int index = em.checkID(idProducto, productos);
		if (index > -1) {
			ew.getListModel().remove(index);
			ew.getLbInfo().setForeground(Color.black);
			ew.getLbInfo().setText("Elemento empquetado");
		} else {
			ew.getLbInfo().setForeground(Color.red);
			ew.getLbInfo().setText("Elemento no presente");
		}
	}
	
	private void activarAceptar() {
		ew.getListModel().addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                actualizarAceptar();
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
            	actualizarAceptar();
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
            	actualizarAceptar();
            }

            private void actualizarAceptar() {
                ew.getBtAceptar().setEnabled(ew.getListModel().isEmpty()); // Habilitar si está vacío
            }
        });
	}
	
	private void accionAceptar() {
		ew.getBtAceptar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				empaquetar();
			}
		});
	}
	
	private void empaquetar() {
		int idWorkorder = ((WorkorderWrapper)ew.getCbWorkorders().getSelectedItem()).getInfo().idWorkorder;
		em.empaquetar(1, idWorkorder);
		ew.dispose();
		FacturaView fw = new FacturaView();
		new FacturaController(fw, new FacturaModel(em.getDB(), 2));
		fw.setVisible(true);
	}
}
