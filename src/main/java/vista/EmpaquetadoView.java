package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.EmpaquetadoController;
import modelo.dto.ProductoWrapper;
import modelo.dto.WorkorderWrapper;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EmpaquetadoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btCancelar;
	private JButton btAceptar;
	private JScrollPane scrollPane;
	private JList<ProductoWrapper> listProductos;
	private JComboBox<WorkorderWrapper> cbWorkorders;
	private DefaultListModel<ProductoWrapper> listModel;
	private JTextArea textArea;
	private JButton btApuntar;
	private JLabel lbIncidencia;
	private JTextField textField;
	private JLabel lbID;
	private JButton btComprobar;
	private EmpaquetadoController ec;
	private JLabel lbInfo;

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
		setBounds(100, 100, 620, 430);
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
		contentPane.add(getLbInfo());
		setLocationRelativeTo(null);
		
		ec = new EmpaquetadoController(this);
		ec.init();
	}
	public JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.setBackground(Color.RED);
			btCancelar.setForeground(Color.WHITE);
			btCancelar.setBounds(506, 357, 88, 23);
		}
		return btCancelar;
	}
	public JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Empaquetar");
			btAceptar.setEnabled(false);
			btAceptar.setForeground(Color.WHITE);
			btAceptar.setBackground(Color.GREEN);
			btAceptar.setBounds(385, 357, 111, 23);
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
	public JList<ProductoWrapper> getListProductos() {
		if (listProductos == null) {
			listModel = new DefaultListModel<ProductoWrapper>();
			listProductos = new JList<>(listModel);
		}
		return listProductos;
	}
	public DefaultListModel<ProductoWrapper> getListModel(){
		return listModel;
	}
	public JComboBox<WorkorderWrapper> getCbWorkorders() {
		if (cbWorkorders == null) {
			cbWorkorders = new JComboBox<>();
			cbWorkorders.setBounds(10, 11, 365, 40);
		}
		return cbWorkorders;
	}
	public JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBounds(385, 216, 209, 96);
		}
		return textArea;
	}
	public JButton getBtApuntar() {
		if (btApuntar == null) {
			btApuntar = new JButton("Apuntar");
			btApuntar.setEnabled(false);
			btApuntar.setBounds(385, 323, 101, 23);
		}
		return btApuntar;
	}
	public JLabel getLbIncidencia() {
		if (lbIncidencia == null) {
			lbIncidencia = new JLabel("Incidencia:");
			lbIncidencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lbIncidencia.setBounds(385, 182, 209, 23);
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
			btComprobar.setEnabled(false);
			btComprobar.setBounds(425, 92, 148, 23);
		}
		return btComprobar;
	}
	public JLabel getLbInfo() {
		if (lbInfo == null) {
			lbInfo = new JLabel("");
			lbInfo.setVerticalAlignment(SwingConstants.TOP);
			lbInfo.setBounds(385, 126, 188, 45);
		}
		return lbInfo;
	}
}
