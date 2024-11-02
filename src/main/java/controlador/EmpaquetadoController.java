package controlador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.modelo.EmpaquetadoModel;
import modelo.modelo.FacturaModel;
import persistence.dto.AlmaceneroDto;
import persistence.dto.PedidoDto;
import persistence.dto.ProductoDto;
import persistence.dto.WorkorderDto;
import vista.EmpaquetadoView;
import vista.FacturaView;

public class EmpaquetadoController {
	
	private EmpaquetadoModel em;
	private EmpaquetadoView ew;
	private int rowWorkorder;
	private int rowPedido = 0;
	private int idPaquete;
	
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
	
	private void cerrar() {
		FacturaModel fm = new FacturaModel(em.getDB(), idPaquete);
		FacturaController fc =  new FacturaController(fm);
		FacturaView fw = new FacturaView(fc);
		fw.setVisible(true);
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
		idPaquete = em.checkID(rowWorkorder, rowPedido, id);
		if (idPaquete > 0) {
			addProductosTable(rowWorkorder, rowPedido);
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

	private void addPedidosTable(int rowWorkorder) {
		vaciarTabla();
		ew.getTableModel().addColumn("ID Pedido");
		ew.getTableModel().addColumn("Numero de productos");
		WorkorderDto workorder = em.getWorkorders().get(rowWorkorder);
		for (PedidoDto pedido : workorder.pedidos) {
			Object[] data = {pedido.idPedido, pedido.productos.size()};
			ew.getTableModel().addRow(data);
		}
	}
	
	private void addProductosTable(int idWorkorder, int idPedido) {
		vaciarTabla();
		ew.getTableModel().addColumn("ID Producto");
		ew.getTableModel().addColumn("Nombre");
		ew.getTableModel().addColumn("Cantidad");
		WorkorderDto workorder = em.getWorkorders().get(idWorkorder);
		PedidoDto pedido = workorder.pedidos.get(idPedido);
		for (ProductoDto producto : pedido.productos.keySet()) {
			Object[] data = {producto.idProducto, producto.nombre, pedido.productos.get(producto)};
			ew.getTableModel().addRow(data);
		}
		
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
			rowWorkorder = ew.getTable().getSelectedRow();
			if ((int)ew.getTableModel().getValueAt(rowWorkorder, 1) > 1) {
				ajustarTamañoPedidos();
				addPedidosTable(rowWorkorder);
			} else {
				ajustarTamañosProductos(true);
				addProductosTable(rowWorkorder, rowPedido);
			}
			break;
		case "ID Pedido":
			rowPedido = ew.getTable().getSelectedRow();
				ajustarTamañosProductos(false);
				addProductosTable(rowWorkorder , rowPedido);
			break;
		case "ID Producto":
			break;
		}
	}
	
	private void ajustarTamañoPedidos() {
		ew.getPnDatos().setBounds(10, 11, 444, 60);
		
		ew.getPnDatos().add(nuevaLabel("Workorder ID:"));
		ew.getPnDatos().add(nuevoTxField(em.getWorkorders().get(rowWorkorder).idWorkorder));
		ew.validate();
		ew.repaint();
	}
	
	private void ajustarTamañosProductos(boolean pasoDirecto) {
		ew.getScTabla().setBounds(10, 112, 444, 440);
		ew.getPnDatos().setBounds(10, 11, 444, 90);
		if (pasoDirecto) {
			ew.getPnDatos().add(nuevaLabel("Workorder ID:"));
			ew.getPnDatos().add(nuevoTxField(em.getWorkorders().get(rowWorkorder).idWorkorder));
		}
		ew.getPnDatos().add(nuevaLabel("Pedido ID:"));
		ew.getPnDatos().add(nuevoTxField(em.getWorkorders().get(rowWorkorder).pedidos.get(rowPedido).idPedido));
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

	
}
