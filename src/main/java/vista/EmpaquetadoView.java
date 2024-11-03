package vista;

import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.EmpaquetadoController;

public class EmpaquetadoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JPanel pnDatos;
	private JLabel lbAlmacennero;
	private JPanel pnBotones;
	private JButton btGenerarWorkOrder;
	private JButton btRecogida;
	private JScrollPane scTabla;
	private JTextArea txaIncidencias;
	private JLabel lbEscaner;
	private JTextField txEscaner;
	private JButton btEscaner;
	private JButton btCerrarCaja;
	private JButton btApuntar;
	private JLabel lbIncidencia;
	private JTable table;
	private JTextField textField;
	private JButton btVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					EmpaquetadoController controller = new EmpaquetadoController();
					EmpaquetadoView frame = new EmpaquetadoView(controller);
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
	public EmpaquetadoView(EmpaquetadoController ec) {
		setTitle("Empaquetado");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 854);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPnDatos());
		contentPane.add(getPnBotones());
		contentPane.add(getScTabla());
		contentPane.add(getTxaIncidencias());
		contentPane.add(getLbEscaner());
		contentPane.add(getTxEscaner());
		contentPane.add(getBtEscaner());
		contentPane.add(getBtCerrarCaja());
		contentPane.add(getBtApuntar());
		contentPane.add(getLbIncidencia());
		setLocationRelativeTo(null);
		
		ec.setView(this);
		ec.init();
	}
	
	public JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setBounds(10, 11, 444, 30);//60 para pedidos 90 para productos (30)
			pnDatos.setLayout(new GridLayout(0, 2, 0, 0));
			pnDatos.add(getLbAlmacennero());
			pnDatos.add(getTextField());
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
	
	public JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setBounds(10, 764, 444, 40);
			pnBotones.setLayout(new GridLayout(0, 3, 4, 0));
			pnBotones.add(getBtVolver());
			pnBotones.add(getBtGenerarWorkOrder());
			pnBotones.add(getBtRecogida());
		}
		return pnBotones;
	}
	
	public JButton getBtGenerarWorkOrder() {
		if (btGenerarWorkOrder == null) {
			btGenerarWorkOrder = new JButton("WorkOrder");
			btGenerarWorkOrder.setBackground(Color.WHITE);
		}
		return btGenerarWorkOrder;
	}
	
	public JButton getBtRecogida() {
		if (btRecogida == null) {
			btRecogida = new JButton("Recogida");
			btRecogida.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
			btRecogida.setBackground(Color.WHITE);
		}
		return btRecogida;
	}
	
	public JScrollPane getScTabla() {
		if (scTabla == null) {
			scTabla = new JScrollPane();
			scTabla.setBounds(10, 112, 444, 641);//440 (641) para escanear productos
			scTabla.setViewportView(getTable());
		}
		return scTabla;
	}
	
	public JTable getTable() {
		if (table == null) {
			tableModel = new DefaultTableModel();
			table = new JTable(tableModel);
		}
		return table;
	}
	
	public DefaultTableModel getTableModel(){
		return tableModel;
	}
	
	public JTextArea getTxaIncidencias() {
		if (txaIncidencias == null) {
			txaIncidencias = new JTextArea();
			txaIncidencias.setVisible(false);
			txaIncidencias.setBounds(10, 604, 222, 84);
		}
		return txaIncidencias;
	}
	
	public JLabel getLbEscaner() {
		if (lbEscaner == null) {
			lbEscaner = new JLabel("Escaner:");
			lbEscaner.setVisible(false);
			lbEscaner.setBounds(242, 563, 101, 30);
		}
		return lbEscaner;
	}
	
	public JTextField getTxEscaner() {
		if (txEscaner == null) {
			txEscaner = new JTextField();
			txEscaner.setVisible(false);
			txEscaner.setBounds(242, 604, 101, 30);
			txEscaner.setColumns(10);
		}
		return txEscaner;
	}
	
	public JButton getBtEscaner() {
		if (btEscaner == null) {
			btEscaner = new JButton("Escanear");
			btEscaner.setEnabled(false);
			btEscaner.setVisible(false);
			btEscaner.setBackground(Color.WHITE);
			btEscaner.setBounds(353, 604, 101, 30);
		}
		return btEscaner;
	}
	
	public JButton getBtCerrarCaja() {
		if (btCerrarCaja == null) {
			btCerrarCaja = new JButton("Cerrar Caja");
			btCerrarCaja.setVisible(false);
			btCerrarCaja.setBackground(Color.WHITE);
			btCerrarCaja.setBounds(353, 658, 101, 30);
		}
		return btCerrarCaja;
	}
	
	public JButton getBtApuntar() {
		if (btApuntar == null) {
			btApuntar = new JButton("Apuntar");
			btApuntar.setVisible(false);
			btApuntar.setBackground(Color.WHITE);
			btApuntar.setBounds(242, 658, 101, 30);
		}
		return btApuntar;
	}
	
	public JLabel getLbIncidencia() {
		if (lbIncidencia == null) {
			lbIncidencia = new JLabel("Escribir Incidencia:");
			lbIncidencia.setVisible(false);
			lbIncidencia.setBounds(10, 563, 222, 30);
		}
		return lbIncidencia;
	}

	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setEditable(false);
			textField.setColumns(10);
		}
		return textField;
	}
	public JButton getBtVolver() {
		if (btVolver == null) {
			btVolver = new JButton("Volver");
			btVolver.setBackground(Color.WHITE);
		}
		return btVolver;
	}
}
