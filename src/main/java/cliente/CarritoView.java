package cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CarritoView extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JLabel lblProductos;
	private JLabel lblCantidad;
	private JTextField textField;
	private JLabel lblPrecioProducto;
	private JTextField textProductos;
	private JButton btnEliminar;
	private JLabel lblPrecioTotal;
	private JTextField textField_1;
	private JButton btnConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarritoView frame = new CarritoView();
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
	public CarritoView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextArea());
		contentPane.add(getLblProductos());
		contentPane.add(getLblCantidad());
		contentPane.add(getTextField());
		contentPane.add(getLblPrecioProducto());
		contentPane.add(getTextProductos());
		contentPane.add(getBtnEliminar());
		contentPane.add(getLblPrecioTotal());
		contentPane.add(getTextField_1());
		contentPane.add(getBtnConfirmar());
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setBounds(10, 48, 197, 202);
		}
		return textArea;
	}
	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos Seleccionados: ");
			lblProductos.setBounds(10, 23, 161, 14);
		}
		return lblProductos;
	}
	private JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad: ");
			lblCantidad.setBounds(217, 23, 79, 14);
		}
		return lblCantidad;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(217, 50, 86, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblPrecioProducto() {
		if (lblPrecioProducto == null) {
			lblPrecioProducto = new JLabel("Precio Producto: ");
			lblPrecioProducto.setBounds(313, 23, 101, 14);
		}
		return lblPrecioProducto;
	}
	private JTextField getTextProductos() {
		if (textProductos == null) {
			textProductos = new JTextField();
			textProductos.setEditable(false);
			textProductos.setBounds(313, 50, 86, 20);
			textProductos.setColumns(10);
		}
		return textProductos;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar Producto");
			btnEliminar.setBounds(409, 49, 155, 23);
		}
		return btnEliminar;
	}
	private JLabel getLblPrecioTotal() {
		if (lblPrecioTotal == null) {
			lblPrecioTotal = new JLabel("Precio Total: ");
			lblPrecioTotal.setBounds(313, 193, 86, 14);
		}
		return lblPrecioTotal;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setEditable(false);
			textField_1.setBounds(313, 218, 86, 20);
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar Compra");
			btnConfirmar.setBounds(409, 217, 155, 23);
		}
		return btnConfirmar;
	}
}
