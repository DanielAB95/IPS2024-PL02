package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.dto.ProductoWrapper;
import modelo.dto.WorkorderDTO;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class EmpaquetadoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btCancelar;
	private JButton btAceptar;
	private JScrollPane scrollPane;
	private JList<ProductoWrapper> listProductos;
	private JComboBox<WorkorderDTO> cbWorkorders;
	private JTextArea textArea;
	private JButton btApuntar;
	private JLabel lbIncidencia;
	private JTextField textField;
	private JLabel lbID;
	private JButton btComprobar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpaquetadoView frame = new EmpaquetadoView();
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
	public EmpaquetadoView() {
		setTitle("Empaquetado");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtCancelar());
		contentPane.add(getBtAceptar());
		contentPane.add(getScrollPane());
		contentPane.add(getCbWorkorders());
		contentPane.add(getTextArea());
		contentPane.add(getBtApuntar());
		contentPane.add(getLbIncidencia());
		contentPane.add(getTextField());
		contentPane.add(getLbID());
		contentPane.add(getBtComprobar());
		setLocationRelativeTo(null);
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
	public JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.setForeground(Color.WHITE);
			btAceptar.setBackground(Color.GREEN);
			btAceptar.setBounds(385, 357, 89, 23);
		}
		return btAceptar;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 62, 365, 318);
			scrollPane.setViewportView(getListProductos());
		}
		return scrollPane;
	}
	public JList getListProductos() {
		if (listProductos == null) {
			listProductos = new JList();
		}
		return listProductos;
	}
	public JComboBox getCbWorkorders() {
		if (cbWorkorders == null) {
			cbWorkorders = new JComboBox();
			cbWorkorders.setBounds(10, 11, 365, 40);
		}
		return cbWorkorders;
	}
	public JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBounds(385, 216, 186, 96);
		}
		return textArea;
	}
	public JButton getBtApuntar() {
		if (btApuntar == null) {
			btApuntar = new JButton("Apuntar");
			btApuntar.setBounds(385, 323, 89, 23);
		}
		return btApuntar;
	}
	public JLabel getLbIncidencia() {
		if (lbIncidencia == null) {
			lbIncidencia = new JLabel("Incidencia:");
			lbIncidencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lbIncidencia.setBounds(385, 182, 188, 23);
		}
		return lbIncidencia;
	}
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(425, 61, 148, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	public JLabel getLbID() {
		if (lbID == null) {
			lbID = new JLabel("ID:");
			lbID.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lbID.setBounds(385, 63, 30, 17);
		}
		return lbID;
	}
	public JButton getBtComprobar() {
		if (btComprobar == null) {
			btComprobar = new JButton("Comprobar");
			btComprobar.setBounds(484, 92, 89, 23);
		}
		return btComprobar;
	}
}
