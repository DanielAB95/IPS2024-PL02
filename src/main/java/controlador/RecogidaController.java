package controlador;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import modelo.modelo.EmpaquetadoModel;
import modelo.modelo.RecogidaModel;
import modelo.modelo.WorkorderModel;
import persistence.dto.AlmaceneroDto;
import persistence.dto.PedidoDto;
import persistence.dto.ProductoDto;
import persistence.dto.WorkorderDto;
import vista.EmpaquetadoView;
import vista.PedidoView;
import vista.RecogidaView;

public class RecogidaController {

	private RecogidaModel rm;
	private RecogidaView rw;
	
	private WorkorderDto wdto;
	
	public RecogidaController() {
		this.rm = new RecogidaModel();
	}
	
	public RecogidaController(RecogidaModel em) {
		this.rm = em;
	}
	
	public void init() {
		fillTxAlmacenero();
		addWorkordersTable();
		accionWorkordersTabla();
		activarBtEscanear();
		activarBtIncidencia();
		accionBotonVolver();
		accionWorkOrder();
		accionEmpaquetado();
		accionBotonEscanear();
		accionIncidencia();
	}
	
	public void setView(RecogidaView view) {
		rw = view;
	}

	private void fillTxAlmacenero() {
		AlmaceneroDto dto = rm.getAlmacenero();
		JTextField tx = (JTextField)rw.getPnDatos().getComponent(1);
		tx.setText(dto.idAlmacenero + " - " + dto.nombre + " " + dto.apellido);
	}

	private void accionBotonVolver() {
		rw.getBtVolver().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
	}
	
	private void accionBotonEscanear() {
		rw.getBtEscaner().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				escanear();
			}
		});
	}
	
	private void accionIncidencia() {
		rw.getBtApuntar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addIncidencia();
			}
		});
	}
	
	private void addIncidencia() {
		rw.getTxaIncidencias().setText("");
		rm.apuntarIncidencia(wdto);
		addWorkordersTable();
	}
	
	private void activarBtIncidencia() {
		rw.getTxaIncidencias().getDocument().addDocumentListener(new DocumentListener() {
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
                rw.getBtApuntar().setEnabled(!rw.getTxaIncidencias().getText().trim().isEmpty());
            }
        });
	}
	
	private void activarBtEscanear() {
		rw.getTxEscaner().getDocument().addDocumentListener(new DocumentListener() {
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
                rw.getBtEscaner().setEnabled(!rw.getTxEscaner().getText().trim().isEmpty());
            }
        });
	}
	
	private void volver() {
		addWorkordersTable();
	}
	
	private void escanear() {
		Integer id = Integer.parseInt(rw.getTxEscaner().getText());
		int cant = (int)rw.getSpinner().getValue();
		if (rm.checkID(wdto, id, cant)) {
			addProductosTable();
		} else {
			JOptionPane.showMessageDialog(null
					, "No se encuentra la ID en el pedido\n o la cantidad es superior a la necesaria"
					, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addWorkordersTable() {
		vaciarTabla();
		if (rw.getPnDatos().getComponents().length > 2) {
			borrarDatos();
		}
		rw.getTableModel().addColumn("ID Workorder");
		rw.getTableModel().addColumn("Numero de pedidos");
		for (WorkorderDto workorder : rm.getWorkorders()) {
			Object[] data = {workorder.idWorkorder, workorder.pedidos.size()};
			rw.getTableModel().addRow(data);
		}
		ajustarTabla();
	}
	
	private void ajustarTabla() {
		for (int i = 0; i < rw.getTable().getColumnCount(); i++) {
            TableColumn column = rw.getTable().getColumnModel().getColumn(i);
            int width = 0;

            for (int j = 0; j < rw.getTable().getRowCount(); j++) {
                TableCellRenderer renderer = rw.getTable().getCellRenderer(j, i);
                Component comp = renderer.getTableCellRendererComponent(rw.getTable(), rw.getTable().getValueAt(j, i), false, false,i, j);
                width = Math.max(width, comp.getPreferredSize().width);
            }
            column.setPreferredWidth(width + rw.getTable().getIntercellSpacing().width);
        }
	}

	//------------------
	private void borrarDatos() {
		rw.getPnDatos().setBounds(10, 11, 414, 30);
		rw.getScTabla().setBounds(10, 82, 414, 517);
		setVisibleComponentes(false);
		rw.getPnDatos().remove(3);
		rw.getPnDatos().remove(2);
		rw.validate();
		rw.repaint();
	}
	
	private void addProductosTable() {
		vaciarTabla();
		rw.getTableModel().addColumn("ID Pedido");
		rw.getTableModel().addColumn("Producto");
		rw.getTableModel().addColumn("Nombre");
		rw.getTableModel().addColumn("Cantidad");
		rw.getTableModel().addColumn("Ubicación");
		for (PedidoDto pedido : wdto.pedidos) {
			List<ProductoDto> productos = new ArrayList<>(pedido.productos.keySet());
			for (ProductoDto producto : WorkorderModel.ordenarProductos(productos)) {
				Object[] data = {pedido.idPedido, producto.idProducto, producto.nombre, pedido.productos.get(producto), producto.ubicacion()};
				rw.getTableModel().addRow(data);
			}
		}
		ajustarTabla();
	}

	private void accionWorkordersTabla() {
		rw.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                	cambiarDatosDelaTabla();
                }
            }
        });
	}
	
	private void cambiarDatosDelaTabla() {
		switch(rw.getTableModel().getColumnName(0)) {
		case "ID Workorder":
			int rowWorkorder = rw.getTable().getSelectedRow();
			wdto = rm.getWorkorders().get(rowWorkorder);
			ajustarTamañosProductos();
			addProductosTable();			
			break;
		case "ID Pedido":
			break;
		}		
	}
	
	
	private void ajustarTamañosProductos() {
		rw.getScTabla().setBounds(10, 82, 414, 381);
		rw.getPnDatos().setBounds(10, 11, 414, 60);
		rw.getPnDatos().add(nuevaLabel("Workorder ID:"));
		rw.getPnDatos().add(nuevoTxField(wdto.idWorkorder));
		setVisibleComponentes(true);
		rw.validate();
		rw.repaint();
	}
	
	private void setVisibleComponentes(boolean visible) {
		rw.getTxaIncidencias().setVisible(visible);
		rw.getBtEscaner().setVisible(visible);
		rw.getLbEscaner().setVisible(visible);
		rw.getTxEscaner().setVisible(visible);
		rw.getLbIncidencia().setVisible(visible);
		rw.getBtApuntar().setVisible(visible);
		rw.getSpinner().setVisible(visible);
	}

	private JTextField nuevoTxField(int id) {
		JTextField tx = new JTextField();
		tx.setHorizontalAlignment(SwingConstants.CENTER);
		tx.setEditable(false);
		tx.setText(String.valueOf(id));
		return tx;
	}
	
	private JLabel nuevaLabel(String texto) {
		JLabel lb = new JLabel(texto);
		lb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		return lb;
	}

	private void vaciarTabla() {
		rw.getTableModel().setColumnCount(0);
		rw.getTableModel().setRowCount(0);
	}

	private void accionEmpaquetado() {
		rw.getBtEmpaquetado().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostarEmpaquetado();
			}
		});	
	}

	private void accionWorkOrder() {
		rw.getBtGenerarWorkOrder().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarWorkOrder();
			}	
		});	
	}
	
	private void mostarEmpaquetado() {
		EmpaquetadoModel em = new EmpaquetadoModel(rm.getDB(), rm.getAlmacenero().idAlmacenero);
		EmpaquetadoController ec = new EmpaquetadoController(em);
		EmpaquetadoView eView = new EmpaquetadoView(ec);
		rw.dispose();
		eView.setVisible(true);	
	}
	
	private void mostrarWorkOrder() {
		PedidoView pView = new PedidoView(rm.getDB(), rm.getAlmacenero().idAlmacenero);
		rw.dispose();
		pView.setVisible(true);
		
	}
}
