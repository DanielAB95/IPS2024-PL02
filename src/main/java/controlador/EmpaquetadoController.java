package controlador;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import modelo.modelo.AlbaranModel;
import modelo.modelo.EmpaquetadoModel;
import modelo.modelo.EtiquetaModel;
import modelo.modelo.RecogidaModel2;
import persistence.dto.AlmaceneroDto;
import persistence.dto.PedidoDto;
import persistence.dto.ProductoDto;
import persistence.dto.WorkorderDto;
import vista.AlbaranView;
import vista.EmpaquetadoView;
import vista.EtiquetaView;
import vista.PedidoView;
import vista.RecogidaView2;

public class EmpaquetadoController {
	
	private EmpaquetadoModel em;
	private EmpaquetadoView ew;
	private int idPaquete;
	
	private WorkorderDto wdto;
	private PedidoDto pdto;
	
	public EmpaquetadoController() {
		this.em = new EmpaquetadoModel();
	}
	
	public EmpaquetadoController(EmpaquetadoModel em) {
		this.em = em;
	}
	
	public void init() {
		fillTxAlmacenero();
		addWorkordersTable();
		accionWorkordersTabla();
		activarBtEscanear();
		accionBotonVolver();
		accionWorkOrder();
		accionRecogida();
		accionBotonEscanear();
		accionBotonCerrarCaja();
	}
	
	public void setView(EmpaquetadoView view) {
		ew = view;
	}

	private void fillTxAlmacenero() {
		AlmaceneroDto dto = em.getAlmacenero();
		JTextField tx = (JTextField)ew.getPnDatos().getComponent(1);
		tx.setText(dto.idAlmacenero + " - " + dto.nombre + " " + dto.apellido);
	}

	private void accionBotonVolver() {
		ew.getBtVolver().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
	}
	
	private void accionBotonEscanear() {
		ew.getBtEscaner().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				escanear();
			}
		});
	}
	
	private void accionBotonCerrarCaja() {
		ew.getBtCerrarCaja().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
	}
	
	private void activarBtEscanear() {
		ew.getTxEscaner().getDocument().addDocumentListener(new DocumentListener() {
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
                ew.getBtEscaner().setEnabled(!ew.getTxEscaner().getText().trim().isEmpty());
            }
        });
	}
	
	private void volver() {
		addWorkordersTable();
	}
	
	private void escanear() {
		Integer id = Integer.parseInt(ew.getTxEscaner().getText());
		idPaquete = em.checkID(wdto, pdto, id);
		if (pdto.productos.size() == 0) {
			cerrar();
		}
		if (idPaquete > 0) {
			addProductosTable();
		} else {
			JOptionPane.showMessageDialog(null, "No se encuentra la ID en el pedido", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void cerrar() {
		idPaquete = em.cerrarCaja(wdto.idWorkorder);
		if (idPaquete > 0) {
			EtiquetaModel fm = new EtiquetaModel(em.getDB(), idPaquete);
			EtiquetaController fc =  new EtiquetaController(fm);
			EtiquetaView fw = new EtiquetaView(fc);
			fw.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "No se ha empaquetado nada", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (em.albaranDisponible(pdto)) {
			AlbaranModel am = new AlbaranModel(em.getDB(), pdto.idPedido);
			AlbaranController ac =  new AlbaranController(am);
			AlbaranView aw = new AlbaranView(ac);
			aw.setVisible(true);
		}
	}

	private void addWorkordersTable() {
		vaciarTabla();
		if (ew.getPnDatos().getComponents().length > 2) {
			borrarDatos();
		}
		ew.getTableModel().addColumn("ID Workorder");
		ew.getTableModel().addColumn("Numero de pedidos");
		for (WorkorderDto workorder : em.getWorkorders()) {
			Object[] data = {workorder.idWorkorder, workorder.pedidos.size()};
			ew.getTableModel().addRow(data);
		}
		ajustarTabla();
	}
	
	private void ajustarTabla() {
		for (int i = 0; i < ew.getTable().getColumnCount(); i++) {
            TableColumn column = ew.getTable().getColumnModel().getColumn(i);
            int width = 0;

            // Calcular el ancho máximo de cada celda en la columna
            for (int j = 0; j < ew.getTable().getRowCount(); j++) {
                TableCellRenderer renderer = ew.getTable().getCellRenderer(j, i);
                Component comp = renderer.getTableCellRendererComponent(ew.getTable(), ew.getTable().getValueAt(j, i), false, false,i, j);
                width = Math.max(width, comp.getPreferredSize().width);
            }

            // Ajustar el ancho de la columna
            column.setPreferredWidth(width + ew.getTable().getIntercellSpacing().width);
        }
	}
	
	private void borrarDatos() {
		ew.getPnDatos().setBounds(10, 11, 444, 30);
		ew.getScTabla().setBounds(10, 112, 444, 641);
		setVisibleComponentes(false);
		if (ew.getPnDatos().getComponents().length > 4) {
			ew.getPnDatos().remove(5);
			ew.getPnDatos().remove(4);
		}
		ew.getPnDatos().remove(3);
		ew.getPnDatos().remove(2);
		ew.validate();
		ew.repaint();
	}

	private void addPedidosTable() {
		vaciarTabla();
		ew.getTableModel().addColumn("ID Pedido");
		ew.getTableModel().addColumn("Numero de productos");
		for (PedidoDto pedido : wdto.pedidos) {
			Object[] data = {pedido.idPedido, pedido.productos.size()};
			ew.getTableModel().addRow(data);
		}
		ajustarTabla();
	}
	
	private void addProductosTable() {
		vaciarTabla();	
		ew.getTableModel().addColumn("ID Producto");
		ew.getTableModel().addColumn("Nombre");
		ew.getTableModel().addColumn("Cantidad");
		for (ProductoDto producto : pdto.productos.keySet()) {
			Object[] data = {producto.idProducto, producto.nombre, pdto.productos.get(producto)};
			ew.getTableModel().addRow(data);
		}
		ajustarTabla();
	}

	private void accionWorkordersTabla() {
		ew.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                	cambiarDatosDelaTabla();
                }
            }
        });
	}
	
	private void cambiarDatosDelaTabla() {
		switch(ew.getTableModel().getColumnName(0)) {
		case "ID Workorder":
			int rowWorkorder = ew.getTable().getSelectedRow();
			wdto = em.getWorkorders().get(rowWorkorder);
			if (wdto.pedidos.size() > 1) {
				ajustarTamañoPedidos();
				addPedidosTable();
			} else {
				pdto = wdto.pedidos.get(0);
				ajustarTamañosProductos(true);
				addProductosTable();
			}
			break;
		case "ID Pedido":
			int rowPedido = ew.getTable().getSelectedRow();
			pdto = wdto.pedidos.get(rowPedido);
			ajustarTamañosProductos(false);
			addProductosTable();
			break;
		case "ID Producto":
			break;
		}
	}
	
	private void ajustarTamañoPedidos() {
		ew.getPnDatos().setBounds(10, 11, 444, 60);
		
		ew.getPnDatos().add(nuevaLabel("Workorder ID:"));
		ew.getPnDatos().add(nuevoTxField(wdto.idWorkorder));
		ew.validate();
		ew.repaint();
	}
	
	private void ajustarTamañosProductos(boolean pasoDirecto) {
		ew.getScTabla().setBounds(10, 112, 444, 440);
		ew.getPnDatos().setBounds(10, 11, 444, 90);
		if (pasoDirecto) {
			ew.getPnDatos().add(nuevaLabel("Workorder ID:"));
			ew.getPnDatos().add(nuevoTxField(wdto.idWorkorder));
		}
		ew.getPnDatos().add(nuevaLabel("Pedido ID:"));
		ew.getPnDatos().add(nuevoTxField(pdto.idPedido));
		setVisibleComponentes(true);
		ew.validate();
		ew.repaint();
	}
	
	private void setVisibleComponentes(boolean visible) {
		ew.getTxaIncidencias().setVisible(visible);
		ew.getBtEscaner().setVisible(visible);
		ew.getLbEscaner().setVisible(visible);
		ew.getTxEscaner().setVisible(visible);
		ew.getLbIncidencia().setVisible(visible);
		ew.getBtApuntar().setVisible(visible);
		ew.getBtCerrarCaja().setVisible(visible);
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
		ew.getTableModel().setColumnCount(0);
		ew.getTableModel().setRowCount(0);
	}

	private void accionRecogida() {
		ew.getBtRecogida().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostarRecogida();
				
			}

			
		});	
	}

	private void accionWorkOrder() {
		ew.getBtGenerarWorkOrder().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarWorkOrder();
				
			}	
		});	
	}
	
	private void mostarRecogida() {
		RecogidaModel2 rm = new RecogidaModel2(em.getDB(), em.getAlmacenero().idAlmacenero);
		RecogidaController2 rc = new RecogidaController2(rm);
		RecogidaView2 rView = new RecogidaView2(rc);
		ew.dispose();
		rView.setVisible(true);
		
	}
	
	private void mostrarWorkOrder() {
		PedidoView pView = new PedidoView(em.getDB(), em.getAlmacenero().idAlmacenero);
		ew.dispose();
		pView.setVisible(true);
		
	}
	
}
