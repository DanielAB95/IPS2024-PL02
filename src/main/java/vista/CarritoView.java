package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import controlador.CarritoController;
import giis.demo.util.Database2;
import modelo.dto.Carrito;
import modelo.dto.ClienteDTO;
import modelo.modelo.CarritoModel;
import vista.ClienteView.MyTableModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class CarritoView extends JFrame {

	private JPanel contentPane;
	private JLabel lblProductos;
	private JButton btnEliminar;
	private JLabel lblPrecioTotal;
	private JTextField textPrecioTotal;
	private JButton btnConfirmar;
	private CarritoModel modelo;
	
	private Carrito carrito;
	private JPanel panel;
	private JScrollPane scrollPane;
	private  DefaultListModel<String> listModel;
	private CarritoController controller;
	private Database2 database;
	private JLabel lblUsuario;
	private JLabel lblNombreUsuario;
	private ClienteDTO dto;
	private JTable table;
	private DefaultTableModel tableModelCarrito;
	
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
		setBounds(100, 100, 998, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblProductos());
		contentPane.add(getBtnEliminar());
		contentPane.add(getLblPrecioTotal());
		contentPane.add(getTextPrecioTotal());
		contentPane.add(getBtnConfirmar());
		contentPane.add(getPanel());
		contentPane.add(getLblUsuario());
		contentPane.add(getLblNombreUsuario());
	
		controller.initView();
		controller.initController();
		modelo.añadeProductosListModel(tableModelCarrito);
		
	}
	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Carrito: ");
			lblProductos.setBounds(775, 35, 161, 14);
		}
		return lblProductos;
	}
	public JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			
			btnEliminar.setBackground(new Color(178, 34, 34));
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setBounds(676, 286, 86, 23);
		}
		return btnEliminar;
	}
	private JLabel getLblPrecioTotal() {
		if (lblPrecioTotal == null) {
			lblPrecioTotal = new JLabel("Precio Total: ");
			lblPrecioTotal.setBounds(886, 262, 86, 14);
		}
		return lblPrecioTotal;
	}
	
	public JTextField getTextPrecioTotal() {
		if (textPrecioTotal == null) {
			textPrecioTotal = new JTextField();
			textPrecioTotal.setEditable(false);
			textPrecioTotal.setBounds(886, 287, 86, 20);
			textPrecioTotal.setColumns(10);
		}
		return textPrecioTotal;
	}
	
	public JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar Pago");
			
			btnConfirmar.setBackground(new Color(50, 205, 50));
			btnConfirmar.setForeground(Color.WHITE);
			btnConfirmar.setBounds(775, 427, 197, 23);
		}
		return btnConfirmar;
	}
	
	
	private JPanel getPanel() {
	    if (panel == null) {
	        panel = new JPanel();
	        panel.setBounds(676, 60, 296, 190);
	        panel.setLayout(new BorderLayout()); 
	        panel.add(getScrollPane(), BorderLayout.NORTH); 
	    }
	    return panel;
	}

	private JScrollPane getScrollPane() {
	    if (scrollPane == null) {
	        scrollPane = new JScrollPane(getTable()); 
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
	public JTable getTable() {
		if (table == null) {
			Object[] columnNames = {"   Producto   ", "Cantidad ", "€ "};
			//tableModel = new DefaultTableModel(columnNames, 0);
			tableModelCarrito = new MyTableModel(columnNames);
			table = new JTable(tableModelCarrito);
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(200);
			columnModel.getColumn(1).setPreferredWidth(85);
			columnModel.getColumn(1).setPreferredWidth(85);	
			table.setBounds(712, 64, 160, 236);
			
		}
		return table;
	}
	
	public DefaultTableModel getTableModel() {
		return tableModelCarrito;
	}
	
	public Carrito getCarrito() {
		return this.carrito;
	}
}
