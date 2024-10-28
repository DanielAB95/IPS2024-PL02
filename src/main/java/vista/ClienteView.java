package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import controlador.ClienteController;
import giis.demo.util.Database2;
import modelo.dto.Carrito;
import modelo.dto.ClienteDTO;
import modelo.dto.Producto;
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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClienteView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ClienteModel model;
	private JLabel lblProductos;
	JComboBox<Integer> comboBoxCantidad;
	private JLabel lblCantidad;
	private JLabel lblCategoria;
	private JTextField textCategoria;
	JButton btnAdd;
	Carrito carrito;
	JButton btnSiguiente;
	private ClienteController controller;
	private Database2 db;
	private JLabel lblUsuario;
	private JLabel lblNombreUsuario;
	private JPanel panelCarrito;
	private JLabel lblCarrito;
	private  DefaultListModel<String> listModel;
	private ClienteDTO dto;
	private JTable table;
	private DefaultTableModel tableModelCarrito;
	private DefaultTableModel tableModelProductos;
	private JLabel PrecioTotal;
	private JTextField textPrecioTotal;
	private JButton btnEliminar;
	private JPanel panelProductos;
	private JScrollPane scrollPane_1;
	private JTable tablaProductos;


	/**
	 * Create the frame.
	 */
	public ClienteView(Database2 db, ClienteDTO dto) {
		setTitle("Página de compra");
		this.db = db;
		this.dto = dto;
		this.carrito = new Carrito();
		this.listModel = new DefaultListModel<>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 998, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		model = new ClienteModel(this.db, this.dto, this.carrito);
		
		contentPane.add(getComboBoxCantidad());
		model.rellenaComboCantidad(getComboBoxCantidad());
		
		
		contentPane.add(getLblCantidad());
		contentPane.add(getLblProductos());
		contentPane.add(getLblCategoria());
		contentPane.add(getTextCategoria());
		contentPane.add(getBtnAdd());
		contentPane.add(getBtnSiguiente());
		contentPane.add(getLblUsuario());
		contentPane.add(getLblNombreUsuario());
		contentPane.add(getPanelCarrito());
		contentPane.add(getLblCarrito());
		contentPane.add(getPrecioTotal());
		contentPane.add(getTextPrecioTotal());
		contentPane.add(getBtnEliminar());
		contentPane.add(getPanelProductos());
		//contentPane.add(getList());
		
		model.rellenaTablaProductos(this.tableModelProductos);
		this.controller = new ClienteController(this, model); //añado el controlador
		
		controller.initView(); //inicializa los datos para visualizarlos desde el principio
		controller.initController(); //añade los action listeners
	}
	public JLabel getLblProductos() {
		if (lblProductos == null) {
			lblProductos = new JLabel("Productos: ");
			lblProductos.setBounds(10, 68, 91, 14);
		}
		return lblProductos;
	}
	public JComboBox<Integer> getComboBoxCantidad() {
		if (comboBoxCantidad == null) {
			comboBoxCantidad = new JComboBox<Integer>();			
			comboBoxCantidad.setBounds(446, 64, 77, 22);
		}
		return comboBoxCantidad;
	}
	public JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad: ");
			lblCantidad.setBounds(369, 68, 67, 14);
		}
		return lblCantidad;
	}
	public JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("Categoría: ");
			lblCategoria.setBounds(10, 39, 77, 14);
		}
		return lblCategoria;
	}
	public JTextField getTextCategoria() {
		if (textCategoria == null) {
			textCategoria = new JTextField();
			textCategoria.setEditable(false);
			textCategoria.setBounds(75, 36, 86, 20);
			textCategoria.setColumns(10);
		}
		return textCategoria;
	}
	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Añadir");		
			btnAdd.setForeground(Color.WHITE);
			btnAdd.setBackground(Color.BLACK);
			btnAdd.setBounds(533, 64, 89, 23);
		}
		return btnAdd;
	}
	public JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Comprar");
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setBackground(new Color(0, 204, 0));		
			btnSiguiente.setBounds(868, 427, 89, 23);
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
	private JPanel getPanelCarrito() {
		if (panelCarrito == null) {
			panelCarrito = new JPanel();
			panelCarrito.setBounds(712, 93, 245, 218);
			panelCarrito.setLayout(new BorderLayout(0, 0));
			JScrollPane scrollPane = new JScrollPane(getTable());
			panelCarrito.add(scrollPane);
			//panel.add(getTable());
		}
		return panelCarrito;
	}
	private JLabel getLblCarrito() {
		if (lblCarrito == null) {
			lblCarrito = new JLabel("Carrito: ");
			lblCarrito.setBounds(712, 68, 46, 14);
		}
		return lblCarrito;
	}
	
	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public ClienteDTO getDto() {
		return dto;
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
	private JLabel getPrecioTotal() {
		if (PrecioTotal == null) {
			PrecioTotal = new JLabel("Precio Total:");
			PrecioTotal.setBounds(868, 322, 77, 14);
		}
		return PrecioTotal;
	}
	public JTextField getTextPrecioTotal() {
		if (textPrecioTotal == null) {
			textPrecioTotal = new JTextField();
			textPrecioTotal.setEditable(false);
			textPrecioTotal.setBounds(868, 347, 89, 20);
			textPrecioTotal.setColumns(10);
		}
		return textPrecioTotal;
	}
	
	// clase que extiende DefaultTableModel
    static class MyTableModel extends DefaultTableModel {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyTableModel(Object[] data) {
            super(data,0);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            // solo la segunda columna (índice 1) es editable
            return column == 1; 
        }
    }
	public JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setBackground(new Color(255, 0, 0));
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setBounds(712, 346, 89, 23);
		}
		return btnEliminar;
	}
	private JPanel getPanelProductos() {
		if (panelProductos == null) {
			panelProductos = new JPanel();
			panelProductos.setBounds(10, 93, 612, 357);
			panelProductos.setLayout(new BorderLayout(0, 0));
			panelProductos.add(getScrollPane_1());
			
		}
		return panelProductos;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane(getTablaProductos());
		}
		return scrollPane_1;
	}
	public JTable getTablaProductos() {
		if (tablaProductos == null) {
					
			Object[] columnNames = {"  Nombre Producto  ", " € / Ud ", "   Descripción   "};
			tableModelProductos = new DefaultTableModel(columnNames, 0) {
				private static final long serialVersionUID = 1L;

				@Override
	            public boolean isCellEditable(int row, int column) {
	                // ninguna celda es editable
	                return false;
	            }
	        };
			
			tablaProductos = new JTable(tableModelProductos);
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			
			//ajusto tamaño de las columnas:
			TableColumnModel columnModel = tablaProductos.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(160);
			columnModel.getColumn(1).setPreferredWidth(50);
			columnModel.getColumn(2).setPreferredWidth(380);	
			
		}
		return tablaProductos;
	}
}
