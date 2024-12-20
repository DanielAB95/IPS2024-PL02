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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.RecogidaController;

public class RecogidaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JPanel pnDatos;
	private JLabel lbAlmacennero;
	private JPanel pnBotones;
	private JButton btGenerarWorkOrder;
	private JButton btEmpaquetado;
	private JScrollPane scTabla;
	private JTextArea txaIncidencias;
	private JLabel lbEscaner;
	private JTextField txEscaner;
	private JButton btEscaner;
	private JButton btApuntar;
	private JLabel lbIncidencia;
	private JTable table;
	private JTextField textField;
	private JButton btVolver;
	private JSpinner spinner;
	private JButton btRecogida;
	private JButton btCargaProductos;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RecogidaController controller = new RecogidaController();
					RecogidaView frame = new RecogidaView(controller);
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
	public RecogidaView(RecogidaController ec) {
		setTitle("Recogida");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 480, 854);
		setBounds(100, 100, 450, 700);
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
		contentPane.add(getBtApuntar());
		contentPane.add(getLbIncidencia());
		contentPane.add(getSpinner());
		setLocationRelativeTo(null);
		
		ec.setView(this);
		ec.init();
	}
	
	public JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setBounds(10, 11, 408, 30);//60 para pedidos 90 para productos (30)
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
			pnBotones.setBounds(10, 610, 414, 40);
			pnBotones.setLayout(new GridLayout(2, 3, 4, 0));
			pnBotones.add(getBtVolver());
			pnBotones.add(getBtGenerarWorkOrder());
			pnBotones.add(getBtRecogida());
			pnBotones.add(getBtEmpaquetado());
			pnBotones.add(getBtCargaProductos());
			pnBotones.add(getBtnNewButton_1());
		}
		return pnBotones;
	}
	
	public JButton getBtGenerarWorkOrder() {
		if (btGenerarWorkOrder == null) {
			btGenerarWorkOrder = new JButton("WorkOrder");
			btGenerarWorkOrder.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btGenerarWorkOrder.setBackground(Color.WHITE);
		}
		return btGenerarWorkOrder;
	}
	
	public JButton getBtEmpaquetado() {
		if (btEmpaquetado == null) {
			btEmpaquetado = new JButton("Empaquetado");
			btEmpaquetado.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btEmpaquetado.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
			btEmpaquetado.setBackground(Color.WHITE);
		}
		return btEmpaquetado;
	}
	
	public JScrollPane getScTabla() {
		if (scTabla == null) {
			scTabla = new JScrollPane();
			scTabla.setBounds(10, 82, 408, 517);//470 (671) para escanear productos
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
			txaIncidencias.setBounds(10, 515, 201, 84);
		}
		return txaIncidencias;
	}
	
	public JLabel getLbEscaner() {
		if (lbEscaner == null) {
			lbEscaner = new JLabel("Escaner:");
			lbEscaner.setVisible(false);
			lbEscaner.setBounds(221, 474, 101, 30);
		}
		return lbEscaner;
	}
	
	public JTextField getTxEscaner() {
		if (txEscaner == null) {
			txEscaner = new JTextField();
			txEscaner.setVisible(false);
			txEscaner.setBounds(221, 515, 86, 30);
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
			btEscaner.setBounds(332, 512, 92, 30);
		}
		return btEscaner;
	}
	
	public JButton getBtApuntar() {
		if (btApuntar == null) {
			btApuntar = new JButton("Apuntar");
			btApuntar.setVisible(false);
			btApuntar.setBackground(Color.WHITE);
			btApuntar.setBounds(221, 569, 101, 30);
		}
		return btApuntar;
	}
	
	public JLabel getLbIncidencia() {
		if (lbIncidencia == null) {
			lbIncidencia = new JLabel("Escribir Incidencia:");
			lbIncidencia.setVisible(false);
			lbIncidencia.setBounds(10, 474, 201, 30);
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
			btVolver.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btVolver.setBackground(Color.WHITE);
		}
		return btVolver;
	}
	public JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spinner.setVisible(false);
			spinner.setBounds(332, 569, 92, 30);
		}
		return spinner;
	}
	private JButton getBtRecogida() {
		if (btRecogida == null) {
			btRecogida = new JButton("Recogida");
			btRecogida.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btRecogida.setEnabled(false);
		}
		return btRecogida;
	}
	public JButton getBtCargaProductos() {
		if (btCargaProductos == null) {
			btCargaProductos = new JButton("Carga");
			btCargaProductos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		return btCargaProductos;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("New button");
			btnNewButton_1.setEnabled(false);
		}
		return btnNewButton_1;
	}
}
