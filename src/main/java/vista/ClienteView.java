package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ClienteController;
import giis.demo.util.Database;
import giis.demo.util.Database2;
import modelo.dto.Carrito;
import modelo.modelo.ClienteModel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;

public class ClienteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxProductos;
	private ClienteModel model;
	private JLabel lblProductos;
	JComboBox<Integer> comboBoxCantidad;
	private JLabel lblCantidad;
	private JLabel lblCategoria;
	private JTextField textCategoria;
	private JLabel lblPrecio;
	private JTextField textPrecio;
	private JLabel lblDescripcion;
	private JTextArea textDescripcion;
	JButton btnAdd;
	Carrito carrito;
	JButton btnSiguiente;
	private ClienteController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					//creo bd
					Database2 db =new Database2();
					db.createDatabase(false);
					
					//lleno bd
					db.loadDatabase();
					
					ClienteView frame = new ClienteView();
					frame.setVisible(true);
					
					
					//model.printProductos();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClienteView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getComboBoxProductos());
		
		model = new ClienteModel();
		
		contentPane.add(getComboBoxCantidad());
		model.rellenaComboCantidad(getComboBoxCantidad()); //relleno el combo de cantidad
				
		model.rellenaComboProductos(getComboBoxProductos()); //relleno el combo de productos
		
		contentPane.add(getLblCantidad());
		contentPane.add(getLblProductos());
		contentPane.add(getLblCategoria());
		contentPane.add(getTextCategoria());
		contentPane.add(getLblPrecio());
		contentPane.add(getTextPrecio());
		contentPane.add(getLblDescripcion());
		contentPane.add(getTextDescripcion());
		contentPane.add(getBtnAdd());
		contentPane.add(getBtnSiguiente());
		
		this.carrito = new Carrito();
		this.controller = new ClienteController(this, model); //añado el controlador
		
		controller.initView(); //inicializa los datos para visualizarlos desde el principio
		controller.initController(); //añade los action listeners
	}

	public JComboBox<String> getComboBoxProductos() {
		if (comboBoxProductos == null) {
			comboBoxProductos = new JComboBox<String>();			
			comboBoxProductos.setBounds(10, 64, 232, 22);
		}
		return comboBoxProductos;
	}
	public JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos: ");
			lblProductos.setBounds(10, 39, 91, 14);
		}
		return lblProductos;
	}
	public JComboBox<Integer> getComboBoxCantidad() {
		if (comboBoxCantidad == null) {
			comboBoxCantidad = new JComboBox<Integer>();			
			comboBoxCantidad.setBounds(272, 64, 77, 22);
		}
		return comboBoxCantidad;
	}
	public JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad: ");
			lblCantidad.setBounds(272, 39, 77, 14);
		}
		return lblCantidad;
	}
	public JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Categoría: ");
			lblCategoria.setBounds(10, 112, 77, 14);
		}
		return lblCategoria;
	}
	public JTextField getTextCategoria() {
		if (textCategoria == null) {
			textCategoria = new JTextField();
			textCategoria.setEditable(false);
			textCategoria.setBounds(10, 137, 86, 20);
			textCategoria.setColumns(10);
		}
		return textCategoria;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio: ");
			lblPrecio.setBounds(272, 112, 61, 14);
		}
		return lblPrecio;
	}
	public JTextField getTextPrecio() {
		if (textPrecio == null) {
			textPrecio = new JTextField();
			textPrecio.setEditable(false);
			textPrecio.setBounds(272, 137, 77, 20);
			textPrecio.setColumns(10);
		}
		return textPrecio;
	}
	private JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel("Descripción: ");
			lblDescripcion.setBounds(10, 190, 91, 14);
		}
		return lblDescripcion;
	}
	public JTextArea getTextDescripcion() {
		if (textDescripcion == null) {
			textDescripcion = new JTextArea();
			textDescripcion.setLineWrap(true);
			textDescripcion.setEditable(false);
			textDescripcion.setBounds(10, 215, 339, 85);
		}
		return textDescripcion;
	}
	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Añadir");		
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setBackground(Color.BLACK);
			btnAdd.setBounds(385, 64, 89, 23);
		}
		return btnAdd;
	}
	public JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setBackground(new Color(0, 204, 0));		
			btnSiguiente.setBounds(385, 277, 89, 23);
		}
		return btnSiguiente;
	}
	
	public Carrito getCarrito() {
		return carrito;
	}
}
