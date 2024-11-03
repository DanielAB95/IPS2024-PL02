package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

//import controlador.ClienteController;
import giis.demo.util.Database2;
import modelo.dto.Carrito;
import modelo.dto.ClienteDTO;
import modelo.dto.Producto;
import modelo.modelo.ClienteModel;
import modelo.modelo.PedidoModel;

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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import controlador.ClienteController;
import controlador.PedidoController;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ScrollPaneConstants;

public class ClienteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCantidad;
	private JLabel lblCategoria;
	private JTextField textCategoria;
	JButton btnAdd;
	Carrito carrito;
	JButton btnSiguiente;
	private Database2 db;
	private JLabel lblUsuario;
	private JLabel lblNombreUsuario;
	private JLabel lblCarrito;
	private  DefaultListModel<String> listModel;
	private ClienteDTO dto;
	private DefaultTableModel tableModelCarrito;
	private JButton btnEliminar;
	private JPanel panelProductos;
	private JScrollPane pnProductos;
	private JList<String> listProductos;
	private JButton btAnterior;
	private JButton btInicio;
	private ClienteModel model;
	private ClienteController controller;
	private JScrollPane pnCarrito;
	private JSpinner spUnidades;
	private JTable tableCarrito;
	private JLabel lbPrecioTotal;
	private JTextField txPrecioTotal;
	


	/**
	 * Create the frame.
	 */
	public ClienteView(Database2 db, ClienteDTO dto) {
		setTitle("Página de compra");
		this.db = db;
		this.carrito = new Carrito();
		this.dto = dto;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		contentPane.add(getLblCantidad());
		contentPane.add(getLblCategoria());
		contentPane.add(getTextCategoria());
		contentPane.add(getBtnAdd());
		contentPane.add(getBtnSiguiente());
		contentPane.add(getLblUsuario());
		contentPane.add(getLblNombreUsuario());
		contentPane.add(getLblCarrito());
		contentPane.add(getBtnEliminar());
		contentPane.add(getPanelProductos());
		contentPane.add(getBtAnterior());
		contentPane.add(getBtInicio());
		contentPane.add(getPnCarrito());
		contentPane.add(getSpUnidades());
		contentPane.add(getLbPrecioTotal());
		contentPane.add(getTxPrecioTotal());
		
		model = new ClienteModel(db,carrito,this);
		controller = new ClienteController(this, model);
		controller.initController();
		controller.initView();
	}
	public JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad: ");
			lblCantidad.setBounds(490, 68, 67, 14);
		}
		return lblCantidad;
	}
	public JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Estas en:");
			lblCategoria.setBounds(162, 46, 77, 14);
		}
		return lblCategoria;
	}
	public JTextField getTextCategoria() {
		if (textCategoria == null) {
			textCategoria = new JTextField();
			textCategoria.setEditable(false);
			textCategoria.setBounds(162, 64, 219, 23);
			textCategoria.setColumns(10);
		}
		return textCategoria;
	}
	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Añadir");		
			btnAdd.setEnabled(false);
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setBackground(Color.BLACK);
			btnAdd.setBounds(630, 64, 89, 23);
		}
		return btnAdd;
	}
	public JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Comprar");
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setBackground(new Color(0, 204, 0));		
			btnSiguiente.setBounds(857, 427, 111, 23);
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
			lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNombreUsuario.setBounds(75, 11, 77, 14);
		}
		return lblNombreUsuario;
	}
	private JLabel getLblCarrito() {
		if (lblCarrito == null) {
			lblCarrito = new JLabel("Carrito: ");
			lblCarrito.setBounds(735, 68, 46, 14);
		}
		return lblCarrito;
	}
	
	private JPanel getPanelProductos() {
		if (panelProductos == null) {
			panelProductos = new JPanel();
			panelProductos.setBounds(10, 93, 612, 357);
			panelProductos.setLayout(new BorderLayout(0, 0));
			panelProductos.add(getPnProductos());
			
		}
		return panelProductos;
	}
	private JScrollPane getPnProductos() {
		if (pnProductos == null) {
			pnProductos = new JScrollPane();
			pnProductos.setViewportView(getListProductos());
		}
		return pnProductos;
	}
	private JList<String> getListProductos() {
		if (listProductos == null) {
			listProductos = new JList<String>();
			listProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listProductos;
	}
	private JButton getBtAnterior() {
		if (btAnterior == null) {
			btAnterior = new JButton("Volver Atras");
			btAnterior.setEnabled(false);
			btAnterior.setBounds(34, 64, 118, 23);
		}
		return btAnterior;
	}
	private JButton getBtInicio() {
		if (btInicio == null) {
			btInicio = new JButton("Inicio");
			btInicio.setEnabled(false);
			btInicio.setBounds(391, 64, 89, 23);
		}
		return btInicio;
	}
	
	private JScrollPane getPnCarrito() {
		if (pnCarrito == null) {
			pnCarrito = new JScrollPane();
			pnCarrito.setBounds(712, 93, 256, 218);
			pnCarrito.setViewportView(getTableCarrito());
		}
		return pnCarrito;
	}
	private JSpinner getSpUnidades() {
		if (spUnidades == null) {
			spUnidades = new JSpinner();
			spUnidades.setModel(new SpinnerNumberModel(1, 1, 99, 1));
			spUnidades.setBounds(552, 65, 57, 20);
		}
		return spUnidades;
	}
	
	private JLabel getLbPrecioTotal() {
		if (lbPrecioTotal == null) {
			lbPrecioTotal = new JLabel("Precio total:");
			lbPrecioTotal.setBounds(857, 323, 107, 19);
		}
		return lbPrecioTotal;
	}
	private JTextField getTxPrecioTotal() {
		if (txPrecioTotal == null) {
			txPrecioTotal = new JTextField();
			txPrecioTotal.setEditable(false);
			txPrecioTotal.setBounds(857, 346, 111, 23);
			txPrecioTotal.setColumns(10);
		}
		return txPrecioTotal;
	}
	
	public JTable getTableCarrito() {
		if (tableCarrito == null) {	
			Object[] columnNames = {"   Producto   ", "  Cantidad  ", " € "};
			tableModelCarrito = new MyTableModel(columnNames);
			tableCarrito = new JTable(tableModelCarrito);
			TableColumnModel columnModel = tableCarrito.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(220);
			columnModel.getColumn(1).setPreferredWidth(85);
			columnModel.getColumn(2).setPreferredWidth(85);
			tableCarrito.setBounds(712,64,160,236);
 			
		}
		return tableCarrito;
	}
	
	public DefaultTableModel getTableCarritoModel() {
		return this.tableModelCarrito;
	}
	
	//metodos Auxiliares
	
	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public ClienteDTO getDto() {
		return dto;
	}
	
	// clase que extiende DEfaultTableModel
	static class MyTableModel extends DefaultTableModel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public MyTableModel(Object[]data) {
			super(data,0);
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return column == 1;
		}
		
	}
	
	public JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setBackground(new Color(255, 0, 0));
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setBounds(712, 346, 107, 23);
		}
		return btnEliminar;
	}
	
	public JButton getButtonAnterior() {
		return this.btAnterior;
	}
	
	public JButton getButtonInicio() {
		return this.btInicio;
	}
	
	public JList<String> getListaProductos(){
		return this.listProductos;
	}
	
	public Database2 getDatabase() {
		return this.db;
	}
	
	public JSpinner getSpinnerUnidades() {
		return this.spUnidades;
	}
	
	public JTable getTablaCarrito() {
		return this.tableCarrito;
	}
	
	public JTextField getTextPrecioTotal() {
		return this.txPrecioTotal;
	}
}
