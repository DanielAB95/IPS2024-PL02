package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.InformeAlmaceneroController;

public class InformeAlmaceneroView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btInformes;
	private DefaultTableModel tableModel;
	private JComboBox<String> comboBox;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					InformeAlmaceneroController controller = new InformeAlmaceneroController();
					InformeAlmaceneroView frame = new InformeAlmaceneroView(controller);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InformeAlmaceneroView(InformeAlmaceneroController cont) {
		setTitle("Informes - Almaceneros");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getBtInformes());
		contentPane.add(getComboBox());
		setLocationRelativeTo(null);
		
		cont.setView(this);
		cont.init();
	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(10, 55, 768, 363);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	public JTable getTable() {
		if (table == null) {
			tableModel = new DefaultTableModel();
			table = new JTable(tableModel);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		return table;
	}
	public JButton getBtInformes() {
		if (btInformes == null) {
			btInformes = new JButton("Informes");
			btInformes.setBackground(Color.WHITE);
			btInformes.setBounds(10, 429, 110, 33);
		}
		return btInformes;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	public JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Workorders recogidas", "Productos recogidos", "Paquetes completados", "Productos empaquetados"}));
			comboBox.setBounds(10, 11, 234, 33);
		}
		return comboBox;
	}
}
