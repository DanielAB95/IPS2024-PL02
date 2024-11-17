package vista;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.WorkorderController;

public class WorkorderView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane productoPanel;
	private JLabel lbProductos;
	private JTable tbProductos;
	private DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public WorkorderView(WorkorderController wc) {
		setTitle("Almacenero: WorkOrder");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 854);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getProductoPanel());
		contentPane.add(getLbProductos());
		
		setLocationRelativeTo(null);
		
		wc.setView(this);
		wc.init();
		
	}
	private JScrollPane getProductoPanel() {
		if (productoPanel == null) {
			productoPanel = new JScrollPane();
			productoPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			productoPanel.setBounds(10, 61, 444, 743);
			productoPanel.setViewportView(getTbProductos());
		}
		return productoPanel;
	}
	private JLabel getLbProductos() {
		if (lbProductos == null) {
			lbProductos = new JLabel("Detalles de las Workorders");
			lbProductos.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lbProductos.setBounds(10, 11, 444, 39);
		}
		return lbProductos;
	}
	
	private JTable getTbProductos() {
		if (tbProductos == null) {
			tableModel = new DefaultTableModel();
			tbProductos = new JTable(tableModel);
		}
		return tbProductos;
	}
	
	//Metodos auxiliares
	
	public JTable getTable() {
		return this.tbProductos;
	}
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
}
