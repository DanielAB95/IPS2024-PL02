package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.PedidoController;
import giis.demo.util.Database2;
import modelo.modelo.PedidoModel;


public class PedidoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane tablePanel;
	private PedidoModel model;
	private PedidoController controller;
	private Database2 db;
	private JLabel lbPedidosPendientes;
	private JTable tbPedidos;
	private DefaultTableModel tableModel;
	private JPanel pnMenu;
	private JButton btGenerarWorkOrder;
	private JButton btRecogida;
	private JButton btnEmpaquetado;
	private JPanel pnDatos;
	private JLabel lbAlmacennero;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public PedidoView(Database2 db, int idAlmacenero) {
		setResizable(false);
		this.db = db;
		setTitle("Almacenero: Generar WorkOrder");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 854);
		setLocationRelativeTo(null);
		contentPane =  new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTablePanel());
		contentPane.add(getLbPedidosPendientes());
		contentPane.add(getPnMenu());
		contentPane.add(getPnDatos());
		
		model = new PedidoModel(db, idAlmacenero);
		controller = new PedidoController(this, model);
		controller.init();
	}

	private JScrollPane getTablePanel() {
	    if (tablePanel == null) {
	        tablePanel = new JScrollPane(); 
	        tablePanel.setBounds(10, 105, 444, 648);
	        tablePanel.setPreferredSize(new Dimension(300, 300));
	        tablePanel.setViewportView(getTbPedidos());
	    }
	    return tablePanel;
	}
	
	private JLabel getLbPedidosPendientes() {
		if (lbPedidosPendientes == null) {
			lbPedidosPendientes = new JLabel("No hay pedidos pendientes");
			lbPedidosPendientes.setVisible(false);
			lbPedidosPendientes.setHorizontalAlignment(SwingConstants.CENTER);
			lbPedidosPendientes.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lbPedidosPendientes.setBounds(10, 52, 444, 42);
		}
		return lbPedidosPendientes;
	}
	
	private JTable getTbPedidos() {
		if (tbPedidos == null) {
			tableModel = new DefaultTableModel();
			tbPedidos = new JTable(tableModel);
		}
		return tbPedidos;
	}
	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	private JPanel getPnMenu() {
		if (pnMenu == null) {
			pnMenu = new JPanel();
			pnMenu.setBounds(10, 764, 444, 40);
			pnMenu.setLayout(new GridLayout(0, 3, 4, 0));
			pnMenu.add(getBtGenerarWorkOrder());
			pnMenu.add(getBtRecogida());
			pnMenu.add(getBtnEmpaquetado());
		}
		return pnMenu;
	}
	private JButton getBtGenerarWorkOrder() {
		if (btGenerarWorkOrder == null) {
			btGenerarWorkOrder = new JButton("WorkOrder");
			btGenerarWorkOrder.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btGenerarWorkOrder.setBackground(Color.WHITE);
			btGenerarWorkOrder.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		return btGenerarWorkOrder;
	}
	private JButton getBtRecogida() {
		if (btRecogida == null) {
			btRecogida = new JButton("Recogida");
			btRecogida.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btRecogida.setBackground(Color.WHITE);
		}
		return btRecogida;
	}
	private JButton getBtnEmpaquetado() {
		if (btnEmpaquetado == null) {
			btnEmpaquetado = new JButton("Empaquetado");
			btnEmpaquetado.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btnEmpaquetado.setBackground(Color.WHITE);
		}
		return btnEmpaquetado;
	}
	
	//Metodos Auxiliares
	
	public Database2 getDatabase() {
		return this.db;
	}
	
	public JTable getTablaPedidos(){
		return this.tbPedidos;
	}
	
	public JLabel getLabelPedidosPendientes() {
		return this.lbPedidosPendientes;
	}
	
	public JButton getButtonGenerarWorkOrders() {
		return this.btGenerarWorkOrder;
	}
	
	public JButton getButtonRecogida() {
		return this.btRecogida;
	}
	
	public JButton getButtonEmpaquetado() {
		return this.btnEmpaquetado;
	}
	public JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setBounds(10, 11, 444, 30);
			pnDatos.setLayout(new GridLayout(0, 2, 0, 0));
			pnDatos.add(getLbAlmacennero());
			pnDatos.add(getTextField_1());
		}
		return pnDatos;
	}
	public JLabel getLbAlmacennero() {
		if (lbAlmacennero == null) {
			lbAlmacennero = new JLabel("Almacenero en sesion:");
			lbAlmacennero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lbAlmacennero;
	}
	public JTextField getTextField_1() {
		if (textField == null) {
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setEditable(false);
			textField.setColumns(10);
		}
		return textField;
	}
}
