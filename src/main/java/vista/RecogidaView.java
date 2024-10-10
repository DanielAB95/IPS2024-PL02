package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.dto.Producto;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RecogidaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList<Producto> productosList;
	private DefaultListModel<Producto> listModel;
	private JButton btAceptar;
	private JButton btCancelar;
	private JTextField txfWorkOrderId;
	private JTextField textField;
	private JSpinner spinner;
	private JButton btComprobar;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getProductosList());
		contentPane.add(getBtAceptar());
		contentPane.add(getBtCancelar());
		contentPane.add(getTxfWorkOrderId());
		contentPane.add(getTextField());
		contentPane.add(getSpinner());
		contentPane.add(getBtComprobar());
	}
	public JList<Producto> getProductosList() {
		if (productosList == null) {
			listModel = new DefaultListModel<Producto>();
			productosList = new JList<>();
			productosList.setBounds(10, 58, 202, 322);
		}
		return productosList;
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
			txfWorkOrderId.setBounds(10, 11, 202, 36);
			txfWorkOrderId.setColumns(10);
		}
		return txfWorkOrderId;
	}
	public JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(222, 56, 180, 36);
			textField.setColumns(10);
		}
		return textField;
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
			btComprobar.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btComprobar.setBounds(484, 55, 89, 37);
		}
		return btComprobar;
	}
}
