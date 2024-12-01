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
import javax.swing.JButton;

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
	private JButton btInforme;

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
		setResizable(false);
		setTitle("Informe : Produtos a reponer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbInforme());
		contentPane.add(getScpProductos());
		contentPane.add(getBtInforme());
		setLocationRelativeTo(null);
		
		controller.setView(this);
		controller.init();
	}
	private JLabel getLbInforme() {
		if (lbInforme == null) {
			lbInforme = new JLabel("Informe productos a reponer");
			lbInforme.setBounds(10, 21, 778, 25);
			lbInforme.setHorizontalAlignment(SwingConstants.CENTER);
			lbInforme.setFont(new Font("Tahoma", Font.BOLD, 24));
		}
		return lbInforme;
	}
	private JScrollPane getScpProductos() {
		if (scpProductos == null) {
			scpProductos = new JScrollPane();
			scpProductos.setBounds(15, 57, 752, 358);
			scpProductos.setViewportView(getTbProductos());
		
		}
		return scpProductos;
	}
	private JTable getTbProductos() {
		if (tbProductos == null) {
			Object[] columnNames = {" Id ", " Nombre ", "  Descripcion  ", " Stock actual ", " Cantidad a pedir "};
			tableModelProductos = new DefaultTableModel(columnNames,0);
			tbProductos = new JTable(tableModelProductos);
			tbProductos.setEnabled(false);
			TableColumnModel columnModel = tbProductos.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(20);
			columnModel.getColumn(0).setPreferredWidth(30);
			columnModel.getColumn(1).setPreferredWidth(150);
			columnModel.getColumn(2).setPreferredWidth(20);
			columnModel.getColumn(3).setPreferredWidth(20);
			tbProductos.setBounds(712,64,160,236);
			tbProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	
		}
		return tbProductos;
	}
	
	private JButton getBtInforme() {
		if (btInforme == null) {
			btInforme = new JButton("Volver a informes");
			btInforme.setBounds(10, 426, 141, 42);
		}
		return btInforme;
	}
	
	//metodos auxiliares
	public DefaultTableModel getTableProductosModel() {
		return this.tableModelProductos;
	}
	
	public JTable getTablaProductos() {
		return this.tbProductos;
	}
	public JButton getButtonInforme() {
		return this.btInforme;
	}
	
}
