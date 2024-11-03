package vista;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.WorkorderController;
import giis.demo.util.Database2;
import modelo.modelo.WorkorderModel;

public class WorkorderView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbAlmacenero;
	private JTextField txAlmacenero;
	private JScrollPane productoPanel;
	private JLabel lbProductos;
	private Database2 db;
	private WorkorderModel model;
	private JTable tbProductos;

	/**
	 * Create the frame.
	 */
	public WorkorderView(WorkorderController wc) {
		this.db = db;
		setTitle("Almacenero: WorkOrder");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 854);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null); 
		contentPane.add(getLbAlmacenero());
		contentPane.add(getTxAlmacenero());
		contentPane.add(getProductoPanel());
		contentPane.add(getLbProductos());
		
		setLocationRelativeTo(null);
		
		wc.setView(this);
		wc.init();
		
	}
	private JLabel getLbAlmacenero() {
		if (lbAlmacenero == null) {
			lbAlmacenero = new JLabel("Almacenero responsable:");
			lbAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lbAlmacenero.setBounds(10, 11, 444, 39);
		}
		return lbAlmacenero;
	}
	private JTextField getTxAlmacenero() {
		if (txAlmacenero == null) {
			txAlmacenero = new JTextField();
			txAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			txAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			txAlmacenero.setEditable(false);
			txAlmacenero.setBounds(10, 61, 444, 39);
			txAlmacenero.setColumns(10);
		}
		return txAlmacenero;
	}
	private JScrollPane getProductoPanel() {
		if (productoPanel == null) {
			productoPanel = new JScrollPane();
			productoPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			productoPanel.setBounds(10, 161, 444, 643);
			productoPanel.setViewportView(getTbProductos());
		}
		return productoPanel;
	}
	private JLabel getLbProductos() {
		if (lbProductos == null) {
			lbProductos = new JLabel("Detalles de las Workorders");
			lbProductos.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lbProductos.setBounds(10, 111, 444, 39);
		}
		return lbProductos;
	}
	
	private JTable getTbProductos() {
		if (tbProductos == null) {
			tbProductos = new JTable();
		}
		return tbProductos;
	}
	
	//Metodos auxiliares
	
	public JTextField getTextAlmacenero() {
		return this.txAlmacenero;
	}
	
	public Database2 getDatabase(){
		return this.db;
	}
	
	public JTable getTablaProductos() {
		return this.tbProductos;
	}
	
}
