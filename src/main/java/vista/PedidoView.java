package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.PedidoController;
import giis.demo.util.Database2;
import modelo.modelo.PedidoModel;

import java.awt.GridLayout;
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
	private JTable tabPedidos;
	private PedidoModel model;
	private PedidoController controller;
	private JLabel lbAlmacenero;
	private JTextField txAlmacenero;
	private Database2 db;


	/**
	 * Create the frame.
	 */
	public PedidoView(Database2 db) {
		this.db = db;
		setTitle("Pedidos pendientes");
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
		
		model = new PedidoModel(db);
		controller = new PedidoController(this, model);
		controller.initController();
		controller.initView();
	}

	private JScrollPane getTablePanel() {
	    if (tablePanel == null) {
	        tablePanel = new JScrollPane(getTabPedidos()); 
	        tablePanel.setBounds(0, 239, 626, 596);
	        tablePanel.setPreferredSize(new Dimension(300, 300));
	    }
	    return tablePanel;
	}
	private JTable getTabPedidos() {
		if (tabPedidos == null) {
			tabPedidos = new JTable();
			tabPedidos.setDefaultEditor(Object.class, null); //readonly
			tabPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tabPedidos.setName("tabPedidos");
		}
		return tabPedidos;
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
	
	
	//Metodos Auxiliares
	
	public JTable getTablaPedidos() {
		return this.tabPedidos;
	}
	
	public JTextField getTextAlmacenero() {
		return this.txAlmacenero;
	}
	
	public Database2 getDatabase() {
		return this.db;
	}
}
