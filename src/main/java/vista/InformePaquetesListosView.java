package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.InformePaqutesListosControler;

public class InformePaquetesListosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btInformes;
	private DefaultTableModel tableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					InformePaqutesListosControler controller = new InformePaqutesListosControler();
					InformePaquetesListosView frame = new InformePaquetesListosView(controller);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InformePaquetesListosView(InformePaqutesListosControler cont) {
		setTitle("Informes - Paquetes Listos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getScrollPane());
		contentPane.add(getBtInformes());
		setLocationRelativeTo(null);
		
		cont.setView(this);
		cont.init();
	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(10, 52, 768, 366);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	public JTable getTable() {
		if (table == null) {
			tableModel = new DefaultTableModel();
			table = new JTable(tableModel);
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
}
