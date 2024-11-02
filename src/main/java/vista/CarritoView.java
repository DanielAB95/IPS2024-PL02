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
//import vista.ClienteView.MyTableModel;
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
	private JLabel lblUsuario;
	private JLabel lblNombreUsuario;
	private ClienteDTO dto;
	private JTable table;
	private DefaultTableModel tableModelCarrito;
	private JLabel lblNombre;
	private JLabel lblTelefono;
	private JLabel lblPais;
	private JLabel lblRegion;
	private JLabel lblCiudad;
	private JLabel lblCalle;
	private JLabel lblMetodoDePago;
	private JPanel panelRadioBotones;
	private JTextField textTelefono;
	private JTextField textNombre;
	private JTextField textPais;
	private JTextField textRegion;
	private JTextField textCiudad;
	private Database2 database;
	private JTextField textCalle;
	
	/**
	 * Create the frame.
	 * 
	 * 
	 * En esta ventana en principio se pediran
	 * los datos de envio y se seleccionara el metodo de pago
	 * 
	 * datos de envio:
	 * 		nombre usuario
	 * 		telefono
	 * 		pais 
	 * 		region
	 * 		ciudad
	 * 		calle
	 * 
	 * 
	 * 
	 * y luego debajo escoge el metodo de pago (siguiente ventana)
	 * (con radio buttons por ejemplo)
	 * 
	 * 
	 * 
	 * 
	 */
	public CarritoView(Carrito c, Database2 db, ClienteDTO dto) {
		setTitle("Confirmación de Compra: Datos de Envío");
		this.database = db;
		
		this.listModel = new DefaultListModel<>();
		this.carrito = c;
		this.dto = dto;
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
		contentPane.add(getLblNombre());
		contentPane.add(getLblTelefono());
		contentPane.add(getLblPais());
		contentPane.add(getLblRegion());
		contentPane.add(getLblCiudad());
		contentPane.add(getLblCalle());
		contentPane.add(getLblMetodoDePago());
		contentPane.add(getPanelRadioBotones());
		contentPane.add(getTextTelefono());
		contentPane.add(getTextNombre());
		contentPane.add(getTextPais());
		contentPane.add(getTextRegion());
		contentPane.add(getTextCiudad());
		contentPane.add(getTextCalle());
	
		controller.initView();
		controller.initController();
		modelo.añadeProductosListModel(tableModelCarrito);
		
	}
	private JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Carrito: ");
			lblProductos.setBounds(676, 35, 161, 14);
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
			lblUsuario.setBounds(12, 17, 71, 14);
		}
		return lblUsuario;
	}
	public JLabel getLblNombreUsuario() {
		if (lblNombreUsuario == null) {
			lblNombreUsuario = new JLabel("");
			lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNombreUsuario.setBounds(95, 17, 116, 14);
		}
		return lblNombreUsuario;
	}
	public JTable getTable() {
		if (table == null) {
			Object[] columnNames = {"   Producto   ", "  Cantidad  ", "€ "};
			
			tableModelCarrito = new MyTableModel(columnNames);
			table = new JTable(tableModelCarrito);
			
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(220);
			columnModel.getColumn(1).setPreferredWidth(85);
			columnModel.getColumn(2).setPreferredWidth(85);	
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
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(91, 60, 77, 23);
		}
		return lblNombre;
	}
	private JLabel getLblTelefono() {
		if (lblTelefono == null) {
			lblTelefono = new JLabel("Teléfono: ");
			lblTelefono.setBounds(91, 103, 77, 14);
		}
		return lblTelefono;
	}
	private JLabel getLblPais() {
		if (lblPais == null) {
			lblPais = new JLabel("País: ");
			lblPais.setBounds(91, 143, 77, 14);
		}
		return lblPais;
	}
	private JLabel getLblRegion() {
		if (lblRegion == null) {
			lblRegion = new JLabel("Región:");
			lblRegion.setBounds(91, 187, 77, 14);
		}
		return lblRegion;
	}
	private JLabel getLblCiudad() {
		if (lblCiudad == null) {
			lblCiudad = new JLabel("Ciudad: ");
			lblCiudad.setBounds(91, 235, 77, 14);
		}
		return lblCiudad;
	}
	private JLabel getLblCalle() {
		if (lblCalle == null) {
			lblCalle = new JLabel("Calle: ");
			lblCalle.setBounds(91, 276, 77, 14);
		}
		return lblCalle;
	}
	private JLabel getLblMetodoDePago() {
		if (lblMetodoDePago == null) {
			lblMetodoDePago = new JLabel("Método de Pago: ");
			lblMetodoDePago.setBounds(91, 340, 197, 14);
		}
		return lblMetodoDePago;
	}
	private JPanel getPanelRadioBotones() {
		if (panelRadioBotones == null) {
			panelRadioBotones = new JPanel();
			panelRadioBotones.setBounds(91, 365, 258, 85);
		}
		return panelRadioBotones;
	}
	public JTextField getTextTelefono() {
		if (textTelefono == null) {
			textTelefono = new JTextField();
			textTelefono.setBounds(178, 100, 171, 20);
			textTelefono.setColumns(10);
		}
		return textTelefono;
	}
	public JTextField getTextNombre() {
		if (textNombre == null) {
			textNombre = new JTextField();
			textNombre.setBounds(178, 61, 171, 20);
			textNombre.setColumns(10);
		}
		return textNombre;
	}
	public JTextField getTextPais() {
		if (textPais == null) {
			textPais = new JTextField();
			textPais.setBounds(178, 140, 171, 20);
			textPais.setColumns(10);
		}
		return textPais;
	}
	public JTextField getTextRegion() {
		if (textRegion == null) {
			textRegion = new JTextField();
			textRegion.setBounds(178, 184, 171, 20);
			textRegion.setColumns(10);
		}
		return textRegion;
	}
	public JTextField getTextCiudad() {
		if (textCiudad == null) {
			textCiudad = new JTextField();
			textCiudad.setBounds(178, 230, 171, 20);
			textCiudad.setColumns(10);
		}
		return textCiudad;
	}
	public Database2 getDatabase() {
		return this.database;
	}
	public JTextField getTextCalle() {
		if (textCalle == null) {
			textCalle = new JTextField();
			textCalle.setBounds(174, 274, 171, 19);
			textCalle.setColumns(10);
		}
		return textCalle;
	}
}
