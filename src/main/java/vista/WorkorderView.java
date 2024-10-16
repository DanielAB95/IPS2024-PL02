package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.WorkorderController;
import giis.demo.util.Database2;
import modelo.modelo.WorkorderModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class WorkorderView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbAlmacenero;
	private JTextField txAlmacenero;
	private JLabel lblPedido;
	private JTextField txPedido;
	private JScrollPane productoPanel;
	private JLabel lbProductos;
	private Database2 db;
	private WorkorderModel model;
	private WorkorderController controller;
	private JTextArea txProductos;

	/**
	 * Create the frame.
	 */
	public WorkorderView(Database2 db) {
		this.db = db;
		setTitle("Almacenero: WorkOrder");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 960);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null); 
		contentPane.add(getLbAlmacenero());
		contentPane.add(getTxAlmacenero());
		contentPane.add(getLblPedido());
		contentPane.add(getTxPedido());
		contentPane.add(getProductoPanel());
		contentPane.add(getLbProductos());
		
		model = new WorkorderModel(db);
		controller = new WorkorderController(this, model);
		
	}
	private JLabel getLbAlmacenero() {
		if (lbAlmacenero == null) {
			lbAlmacenero = new JLabel("Almacenero responsable:");
			lbAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lbAlmacenero.setBounds(116, 121, 360, 63);
		}
		return lbAlmacenero;
	}
	private JTextField getTxAlmacenero() {
		if (txAlmacenero == null) {
			txAlmacenero = new JTextField();
			txAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			txAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			txAlmacenero.setEditable(false);
			txAlmacenero.setBounds(146, 172, 420, 82);
			txAlmacenero.setColumns(10);
		}
		return txAlmacenero;
	}
	private JLabel getLblPedido() {
		if (lblPedido == null) {
			lblPedido = new JLabel("Pedido:");
			lblPedido.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblPedido.setBounds(156, 10, 162, 55);
		}
		return lblPedido;
	}
	private JTextField getTxPedido() {
		if (txPedido == null) {
			txPedido = new JTextField();
			txPedido.setHorizontalAlignment(SwingConstants.CENTER);
			txPedido.setFont(new Font("Tahoma", Font.PLAIN, 30));
			txPedido.setEditable(false);
			txPedido.setBounds(173, 56, 246, 55);
			txPedido.setColumns(10);
		}
		return txPedido;
	}
	private JScrollPane getProductoPanel() {
		if (productoPanel == null) {
			productoPanel = new JScrollPane();
			productoPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			productoPanel.setBounds(0, 358, 626, 501);
			productoPanel.setViewportView(getTxProductos());
		}
		return productoPanel;
	}
	private JLabel getLbProductos() {
		if (lbProductos == null) {
			lbProductos = new JLabel("Productos:");
			lbProductos.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lbProductos.setBounds(43, 308, 192, 39);
		}
		return lbProductos;
	}
	
	private JTextArea getTxProductos() {
		if (txProductos == null) {
			txProductos = new JTextArea();
			txProductos.setWrapStyleWord(true);
			txProductos.setLineWrap(true);
			txProductos.setEditable(false);

		}
		return txProductos;
	}
	
	//Metodos auxiliares
	
	public JTextField getTextPedido() {
		return this.txPedido;
	}
	
	public JTextField getTextAlmacenero() {
		return this.txAlmacenero;
	}
	
	
	public JFrame getFrame() {
		return this;
	}
	
	public Database2 getDatabase(){
		return this.db;
	}
	
	public JTextArea getJTextProductos() {
		return this.txProductos;
	}
	
}
