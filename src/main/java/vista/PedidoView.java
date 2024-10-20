package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.PedidoController;
import giis.demo.util.Database2;
import modelo.modelo.PedidoModel;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;


public class PedidoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane tablePanel;
	private PedidoModel model;
	private PedidoController controller;
	private JLabel lbAlmacenero;
	private JTextField txAlmacenero;
	private Database2 db;
	private JLabel lbPedidosPendientes;
	private JTable tbPedidos;


	/**
	 * Create the frame.
	 */
	public PedidoView(Database2 db) {
		setResizable(false);
		this.db = db;
		setTitle("Almacenero: Generar WorkOrder");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 960);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTablePanel());
		contentPane.add(getLbAlmacenero());
		contentPane.add(getTextField());
		contentPane.add(getLbPedidosPendientes());
		
		model = new PedidoModel(db);
		controller = new PedidoController(this, model);
		controller.initController();
		controller.initView();
	}

	private JScrollPane getTablePanel() {
	    if (tablePanel == null) {
	        tablePanel = new JScrollPane(); 
	        tablePanel.setBounds(0, 239, 626, 682);
	        tablePanel.setPreferredSize(new Dimension(300, 300));
	        tablePanel.setViewportView(getTbPedidos());
	    }
	    return tablePanel;
	}
	
	private JLabel getLbAlmacenero() {
		if (lbAlmacenero == null) {
			lbAlmacenero = new JLabel("Almacenero en sesion:");
			lbAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lbAlmacenero.setBounds(117, 10, 361, 75);
		}
		return lbAlmacenero;
	}
	private JTextField getTextField() {
		if (txAlmacenero == null) {
			txAlmacenero = new JTextField();
			txAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			txAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			txAlmacenero.setEditable(false);
			txAlmacenero.setBounds(117, 91, 426, 86);
			txAlmacenero.setColumns(10);
		}
		return txAlmacenero;
	}
	
	private JLabel getLbPedidosPendientes() {
		if (lbPedidosPendientes == null) {
			lbPedidosPendientes = new JLabel("No hay pedidos pendientes");
			lbPedidosPendientes.setVisible(false);
			lbPedidosPendientes.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lbPedidosPendientes.setBounds(117, 198, 377, 42);
		}
		return lbPedidosPendientes;
	}
	
	private JTable getTbPedidos() {
		if (tbPedidos == null) {
			tbPedidos = new JTable();
			tbPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tbPedidos;
	}
	
	//Metodos Auxiliares
	
	public JTextField getTextAlmacenero() {
		return this.txAlmacenero;
	}
	
	public Database2 getDatabase() {
		return this.db;
	}
	
	public JTable getTablaPedidos(){
		return this.tbPedidos;
	}
	
	public JLabel getLabelPedidosPendientes() {
		return this.lbPedidosPendientes;
	}
	
}
