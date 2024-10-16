package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CarritoController;
import giis.demo.util.Database2;
import modelo.dto.Carrito;
import modelo.dto.ClienteDTO;
import modelo.modelo.CarritoModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class CarritoView extends JFrame {

	private JPanel contentPane;
	private JLabel lblProductos;
	private JLabel lblCantidad;
	private JTextField textCantidad;
	private JLabel lblPrecioProducto;
	private JTextField textProductos;
	private JButton btnEliminar;
	private JLabel lblPrecioTotal;
	private JTextField textPrecioTotal;
	private JButton btnConfirmar;
	private CarritoModel modelo;
	
	private Carrito carrito;
	private JPanel panel;
	private JList<String> list_2;
	private JScrollPane scrollPane;
	private  DefaultListModel<String> listModel;
	private CarritoController controller;
	private Database2 database;
	private JLabel lblUsuario;
	private JLabel lblNombreUsuario;
	private ClienteDTO dto;
	
	/**
	 * Create the frame.
	 */
	public CarritoView(Carrito c, Database2 db, ClienteDTO dto) {
		this.database = db;
		this.dto = dto;
		
		this.listModel = new DefaultListModel<>();
		this.carrito = c;
		this.modelo = new CarritoModel(carrito, this, this.database, this.dto);
		this.controller = new CarritoController(this, modelo);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblProductos());
		contentPane.add(getLblCantidad());
		contentPane.add(getTextCantidad());
		contentPane.add(getLblPrecioProducto());
		contentPane.add(getTextProductos());
		contentPane.add(getBtnEliminar());
		contentPane.add(getLblPrecioTotal());
		contentPane.add(getTextPrecioTotal());
		contentPane.add(getBtnConfirmar());
		contentPane.add(getPanel());
		contentPane.add(getLblUsuario());
		contentPane.add(getLblNombreUsuario());
	
		controller.initView();
		controller.initController();
		
	}
	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos Seleccionados: ");
			lblProductos.setBounds(10, 33, 161, 14);
		}
		return lblProductos;
	}
	private JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad: ");
			lblCantidad.setBounds(217, 33, 79, 14);
		}
		return lblCantidad;
	}
	public JTextField getTextCantidad() {
		if (textCantidad == null) {
			textCantidad = new JTextField();
			
			textCantidad.setBounds(217, 58, 86, 20);
			textCantidad.setColumns(10);
		}
		return textCantidad;
	}
	private JLabel getLblPrecioProducto() {
		if (lblPrecioProducto == null) {
			lblPrecioProducto = new JLabel("Precio Producto: ");
			lblPrecioProducto.setBounds(313, 33, 101, 14);
		}
		return lblPrecioProducto;
	}
	public JTextField getTextProductos() {
		if (textProductos == null) {
			textProductos = new JTextField();
			textProductos.setEditable(false);
			textProductos.setBounds(313, 58, 86, 20);
			textProductos.setColumns(10);
		}
		return textProductos;
	}
	public JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar Producto");
			
			btnEliminar.setBackground(new Color(178, 34, 34));
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setBounds(409, 57, 155, 23);
		}
		return btnEliminar;
	}
	private JLabel getLblPrecioTotal() {
		if (lblPrecioTotal == null) {
			lblPrecioTotal = new JLabel("Precio Total: ");
			lblPrecioTotal.setBounds(313, 203, 86, 14);
		}
		return lblPrecioTotal;
	}
	
	public JTextField getTextPrecioTotal() {
		if (textPrecioTotal == null) {
			textPrecioTotal = new JTextField();
			textPrecioTotal.setEditable(false);
			textPrecioTotal.setBounds(313, 228, 86, 20);
			textPrecioTotal.setColumns(10);
		}
		return textPrecioTotal;
	}
	
	public JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar Compra");
			
			btnConfirmar.setBackground(new Color(50, 205, 50));
			btnConfirmar.setForeground(Color.WHITE);
			btnConfirmar.setBounds(409, 227, 155, 23);
		}
		return btnConfirmar;
	}
	
	
	private JPanel getPanel() {
	    if (panel == null) {
	        panel = new JPanel();
	        panel.setBounds(10, 58, 197, 190);
	        panel.setLayout(new BorderLayout()); 
	        panel.add(getScrollPane(), BorderLayout.CENTER); 
	    }
	    return panel;
	}

	public JList<String> getList_2() {
	    if (list_2 == null) {
	        //String nombres[] = modelo.rellenaListaProductos();
	    	modelo.añadeProductosListModel(getListModel());
	        list_2 = new JList<String>(getListModel());
	        
	        list_2.setVisibleRowCount(10); 
	    }
	    return list_2;
	}

	private JScrollPane getScrollPane() {
	    if (scrollPane == null) {
	        scrollPane = new JScrollPane(getList_2()); 
	        scrollPane.setPreferredSize(new Dimension(197, 190)); 
	    }
	    return scrollPane;
	}
	public DefaultListModel<String> getListModel() {
		return listModel;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("Usuario: ");
			lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblUsuario.setBounds(10, 8, 71, 14);
		}
		return lblUsuario;
	}
	public JLabel getLblNombreUsuario() {
		if (lblNombreUsuario == null) {
			lblNombreUsuario = new JLabel("");
			lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNombreUsuario.setBounds(91, 8, 116, 14);
		}
		return lblNombreUsuario;
	}
}
