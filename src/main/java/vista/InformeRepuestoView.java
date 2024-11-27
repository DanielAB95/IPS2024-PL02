package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import controlador.InformeRepuestoController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

public class InformeRepuestoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbInforme;
	private JScrollPane scpProductos;
	private JTable tbProductos;
	private DefaultTableModel tableModelProductos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeRepuestoController controller = new InformeRepuestoController();
					InformeRepuestoView frame = new InformeRepuestoView(controller);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InformeRepuestoView(InformeRepuestoController controller) {
		setTitle("Informe : Produtos a reponer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		contentPane.add(getLbInforme(), BorderLayout.NORTH);
		contentPane.add(getScpProductos(), BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		controller.setView(this);
		controller.init();
	}
	private JLabel getLbInforme() {
		if (lbInforme == null) {
			lbInforme = new JLabel("Informe productos a reponer");
			lbInforme.setHorizontalAlignment(SwingConstants.CENTER);
			lbInforme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lbInforme;
	}
	private JScrollPane getScpProductos() {
		if (scpProductos == null) {
			scpProductos = new JScrollPane();
			scpProductos.setViewportView(getTbProductos());
		
		}
		return scpProductos;
	}
	private JTable getTbProductos() {
		if (tbProductos == null) {
			Object[] columnNames = {" Producto ", "  Descripcion  ", " Stock actual ", " Cantidad a pedir "};
			tableModelProductos = new DefaultTableModel(columnNames,0);
			tbProductos = new JTable(tableModelProductos);
			tbProductos.setEnabled(false);
			TableColumnModel columnModel = tbProductos.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(30);
			columnModel.getColumn(1).setPreferredWidth(150);
			columnModel.getColumn(2).setPreferredWidth(20);
			columnModel.getColumn(3).setPreferredWidth(20);
			tbProductos.setBounds(712,64,160,236);
			tbProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	
		}
		return tbProductos;
	}
	
	//metodos auxiliares
	public DefaultTableModel getTableProductosModel() {
		return this.tableModelProductos;
	}
	
	public JTable getTablaProductos() {
		return this.tbProductos;
	}
}
