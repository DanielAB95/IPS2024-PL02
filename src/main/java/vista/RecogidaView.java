package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.RecogidaController;
import modelo.dto.ProductoWrapper;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RecogidaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<ProductoWrapper> productosList;
	private DefaultListModel<ProductoWrapper> listModel;
	private JButton btAceptar;
	private JButton btCancelar;
	private JTextField txfWorkOrderId;
	private JTextField txfIDProducto;
	private JSpinner spinner;
	private JButton btComprobar;
	private RecogidaController rc;
	private JScrollPane scrollPane;
	private JLabel lbError;
	private JLabel lblNewLabel;
	private JButton btIncidencia;
	private JTextArea textArea;
	private JLabel lbID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecogidaView frame = new RecogidaView();
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
	public RecogidaView() {
		setResizable(false);
		setTitle("Recogida");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtAceptar());
		contentPane.add(getBtCancelar());
		contentPane.add(getTxfWorkOrderId());
		contentPane.add(getTxfIDProducto());
		contentPane.add(getSpinner());
		contentPane.add(getBtComprobar());
		contentPane.add(getScrollPane());
		contentPane.add(getLbError());
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtIncidencia());
		contentPane.add(getTextArea());
		contentPane.add(getLbID());
		setLocationRelativeTo(null);
		
		rc = new RecogidaController(this);
		rc.initController();
	}
	public JList<ProductoWrapper> getProductosList() {
		if (productosList == null) {
			listModel = new DefaultListModel<ProductoWrapper>();
			productosList = new JList<>(listModel);
		}
		return productosList;
	}
	
	public DefaultListModel<ProductoWrapper> getlistModel(){
		return listModel;
	}
	
	public JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.setEnabled(false);
			btAceptar.setBackground(Color.GREEN);
			btAceptar.setForeground(Color.WHITE);
			btAceptar.setBounds(385, 357, 89, 23);
		}
		return btAceptar;
	}
	public JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.setBackground(Color.RED);
			btCancelar.setForeground(Color.WHITE);
			btCancelar.setBounds(484, 357, 89, 23);
		}
		return btCancelar;
	}
	public JTextField getTxfWorkOrderId() {
		if (txfWorkOrderId == null) {
			txfWorkOrderId = new JTextField();
			txfWorkOrderId.setHorizontalAlignment(SwingConstants.CENTER);
			txfWorkOrderId.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txfWorkOrderId.setEditable(false);
			txfWorkOrderId.setBounds(10, 11, 249, 36);
			txfWorkOrderId.setColumns(10);
		}
		return txfWorkOrderId;
	}
	public JTextField getTxfIDProducto() {
		if (txfIDProducto == null) {
			txfIDProducto = new JTextField();
			txfIDProducto.setBounds(305, 56, 97, 36);
			txfIDProducto.setColumns(10);
		}
		return txfIDProducto;
	}
	public JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spinner.setBounds(412, 56, 62, 36);
		}
		return spinner;
	}
	public JButton getBtComprobar() {
		if (btComprobar == null) {
			btComprobar = new JButton("Comprobar");
			btComprobar.setEnabled(false);
			btComprobar.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btComprobar.setBounds(484, 55, 89, 37);
		}
		return btComprobar;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 58, 249, 322);
			scrollPane.setViewportView(getProductosList());
		}
		return scrollPane;
	}
	public JLabel getLbError() {
		if (lbError == null) {
			lbError = new JLabel("");
			lbError.setForeground(Color.RED);
			lbError.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lbError.setVerticalAlignment(SwingConstants.TOP);
			lbError.setBounds(269, 103, 304, 88);
		}
		return lbError;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Escribir incidencia:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(269, 202, 304, 20);
		}
		return lblNewLabel;
	}
	public JButton getBtIncidencia() {
		if (btIncidencia == null) {
			btIncidencia = new JButton("Apuntar");
			btIncidencia.setEnabled(false);
			btIncidencia.setBounds(269, 357, 89, 23);
		}
		return btIncidencia;
	}
	public JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBounds(269, 233, 304, 88);
		}
		return textArea;
	}
	public JLabel getLbID() {
		if (lbID == null) {
			lbID = new JLabel("ID:");
			lbID.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbID.setBounds(269, 56, 26, 36);
		}
		return lbID;
	}
}
