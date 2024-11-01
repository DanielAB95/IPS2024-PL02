package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.dto.Producto;
import modelo.dto.ProductoWrapper;
import modelo.modelo.EmpaquetadoModel;
import modelo.modelo.RecogidaModel;
import vista.EmpaquetadoView;
import vista.RecogidaView;

public class RecogidaController {
	
	private RecogidaModel rm;
	private RecogidaView rw;
	
	public RecogidaController(RecogidaView rw) {
		this.rm = new RecogidaModel();
		this.rw = rw;
	}
	
	public void initController() {
		accionCancelar();
		idWorkorder();
		fillList();
		activarConfirmar();
		accionConfirmar();
		activarBtIncidencia();
		accionIncidencia();
		activarAceptar();
		accionAceptar();
	}
	
	private void accionCancelar() {
		rw.getBtCancelar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void idWorkorder() {
		rw.getTxfWorkOrderId().setText("Workorder ID: " + Integer.toString(rm.getWOID()));
	}
	
	private void fillList() {
		Map<Producto, Integer> productos = rm.getProducts();
		for (Producto entry : productos.keySet()) {
			ProductoWrapper producto = new ProductoWrapper(entry, productos.get(entry));
			rw.getlistModel().addElement(producto);
		}	
	}
	
	private void activarConfirmar() {
		rw.getProductosList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Verificar si la lista no está en proceso de cambio
                if (!e.getValueIsAdjusting()) {
                    // Activar el botón si hay un elemento seleccionado
                    rw.getBtComprobar().setEnabled(!rw.getProductosList().isSelectionEmpty());
                }
            }
        });
	}
	
	private void accionConfirmar() {
		rw.getBtComprobar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				checkProduct();
			}
		});
	}
	
	private void checkProduct() {
		int index = rw.getProductosList().getSelectedIndex();
		ProductoWrapper p = rw.getProductosList().getSelectedValue();
		int cantidad = (int)rw.getSpinner().getValue();
		int id;
		if (rw.getTxfIDProducto().getText().equals("")) {
			id = 0;
		} else {
			id = Integer.parseInt(rw.getTxfIDProducto().getText());
		}
		
		if (rm.checkProduct(p, id, cantidad)) {
			rw.getlistModel().remove(index);
			rw.getLbError().setForeground(Color.black);
			rw.getLbError().setText("Producto comprobado");
		} else {
			rw.getLbError().setForeground(Color.red);
			rw.getLbError().setText("ID o cantidad incorrectos");
		}
	}
	
	private void accionIncidencia() {
		rw.getBtIncidencia().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addIncidencia();
			}
		});
	}
	
	private void activarBtIncidencia() {
		rw.getTextArea().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
            	actualizarIncidencia();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	actualizarIncidencia();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarIncidencia();
            }

            private void actualizarIncidencia() {
                rw.getBtIncidencia().setEnabled(!rw.getTextArea().getText().trim().isEmpty());
            }
        });
	}
	
	private void addIncidencia() {
		rw.getTextArea().setText("");
		rm.apuntarIncidencia();
	}
	
	private void activarAceptar() {
		rw.getlistModel().addListDataListener(new ListDataListener() {
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
                rw.getBtAceptar().setEnabled(rw.getlistModel().isEmpty()); // Habilitar si está vacío
            }
        });
	}
	
	private void accionAceptar() {
		rw.getBtAceptar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pasarWorkorderAListo();
			}
		});
	}
	
	private void pasarWorkorderAListo() {
		rm.pasarAListo();
		rw.dispose();
		EmpaquetadoView ew = new EmpaquetadoView();
		new EmpaquetadoController(ew, new EmpaquetadoModel(rm.getDB(),1));
		ew.setVisible(true);
		
	}
	

}
