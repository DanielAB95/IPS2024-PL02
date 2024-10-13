package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ClienteController;
import giis.demo.util.Database2;
import modelo.dto.Carrito;
import modelo.dto.ClienteDTO;
import modelo.modelo.ClienteModel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;

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
	private Database2 db;
	private JLabel lblUsuario;
	private JLabel lblNombreUsuario;
	private JPanel panel;
	private JLabel lblCarrito;
	private JScrollPane scrollPane;
	private JList<String> list;
	private  DefaultListModel<String> listModel;
	private ClienteDTO dto;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					
//					
//					//creo bd
//					Database2 db =new Database2();
//					db.createDatabase(false);
//					
//					//lleno bd
//					db.loadDatabase();
//					
//					ClienteView frame = new ClienteView();
//					frame.setVisible(true);
//					
//					
//					//model.printProductos();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ClienteView(Database2 db, ClienteDTO dto) {
		this.db = db;
		this.dto = dto;
		
		this.listModel = new DefaultListModel<>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getComboBoxProductos());
		
		model = new ClienteModel(this.db, this.dto);
		
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
		contentPane.add(getLblUsuario());
		contentPane.add(getLblNombreUsuario());
		contentPane.add(getPanel());
		contentPane.add(getLblCarrito());
		//contentPane.add(getList());
		
		this.carrito = new Carrito();
		this.controller = new ClienteController(this, model); //añado el controlador
		
		controller.initView(); //inicializa los datos para visualizarlos desde el principio
		controller.initController(); //añade los action listeners
	}

	public JComboBox<String> getComboBoxProductos() {
		if (comboBoxProductos == null) {
			comboBoxProductos = new JComboBox<String>();			
			comboBoxProductos.setBounds(201, 64, 232, 22);
		}
		return comboBoxProductos;
	}
	public JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos: ");
			lblProductos.setBounds(201, 39, 91, 14);
		}
		return lblProductos;
	}
	public JComboBox<Integer> getComboBoxCantidad() {
		if (comboBoxCantidad == null) {
			comboBoxCantidad = new JComboBox<Integer>();			
			comboBoxCantidad.setBounds(463, 64, 77, 22);
		}
		return comboBoxCantidad;
	}
	public JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad: ");
			lblCantidad.setBounds(463, 39, 77, 14);
		}
		return lblCantidad;
	}
	public JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Categoría: ");
			lblCategoria.setBounds(201, 112, 77, 14);
		}
		return lblCategoria;
	}
	public JTextField getTextCategoria() {
		if (textCategoria == null) {
			textCategoria = new JTextField();
			textCategoria.setEditable(false);
			textCategoria.setBounds(201, 137, 86, 20);
			textCategoria.setColumns(10);
		}
		return textCategoria;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio: ");
			lblPrecio.setBounds(463, 112, 61, 14);
		}
		return lblPrecio;
	}
	public JTextField getTextPrecio() {
		if (textPrecio == null) {
			textPrecio = new JTextField();
			textPrecio.setEditable(false);
			textPrecio.setBounds(463, 137, 77, 20);
			textPrecio.setColumns(10);
		}
		return textPrecio;
	}
	private JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel("Descripción: ");
			lblDescripcion.setBounds(201, 190, 91, 14);
		}
		return lblDescripcion;
	}
	public JTextArea getTextDescripcion() {
		if (textDescripcion == null) {
			textDescripcion = new JTextArea();
			textDescripcion.setLineWrap(true);
			textDescripcion.setEditable(false);
			textDescripcion.setBounds(201, 215, 339, 85);
		}
		return textDescripcion;
	}
	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Añadir");		
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setBackground(Color.BLACK);
			btnAdd.setBounds(575, 64, 89, 23);
		}
		return btnAdd;
	}
	public JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setBackground(new Color(0, 204, 0));		
			btnSiguiente.setBounds(575, 277, 89, 23);
		}
		return btnSiguiente;
	}
	
	public Carrito getCarrito() {
		return carrito;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario: ");
			lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblUsuario.setBounds(10, 11, 55, 14);
		}
		return lblUsuario;
	}
	public JLabel getLblNombreUsuario() {
		if (lblNombreUsuario == null) {
			lblNombreUsuario = new JLabel("");
			lblNombreUsuario.setBounds(75, 11, 77, 14);
		}
		return lblNombreUsuario;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 64, 142, 236);
			panel.setLayout(null);
			panel.add(getScrollPane());
		}
		return panel;
	}
	private JLabel getLblCarrito() {
		if (lblCarrito == null) {
			lblCarrito = new JLabel("Carrito: ");
			lblCarrito.setBounds(10, 39, 46, 14);
		}
		return lblCarrito;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getList());
			scrollPane.setBounds(0, 0, 142, 236);
		}
		return scrollPane;
	}
	
//	private JScrollPane getScrollPane() {
//	    if (scrollPane == null) {
//	        scrollPane = new JScrollPane(getList_2()); 
//	        scrollPane.setPreferredSize(new Dimension(197, 190)); 
//	    }
//	    return scrollPane;
//	}
	
	private JList<String> getList() {
		if (list == null) {
			
			list = new JList<String>(getListModel());
			list.setBounds(85, 39, 1, 1);
		}
		return list;
	}
	
	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public ClienteDTO getDto() {
		return dto;
	}
}
