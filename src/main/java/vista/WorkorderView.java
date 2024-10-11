package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WorkorderView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbAlmacenero;
	private JTextField txAlmacenero;
	private JLabel lblPedido;
	private JTextField txPedido;
	private JScrollPane productoPanel;
	private JLabel lbProductos;
	private JTable tabProductos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkorderView frame = new WorkorderView();
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
	public WorkorderView() {
		setTitle("Workorder");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 844, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbAlmacenero());
		contentPane.add(getTxAlmacenero());
		contentPane.add(getLblPedido());
		contentPane.add(getTxPedido());
		contentPane.add(getProductoPanel());
		contentPane.add(getLbProductos());
	}
	private JLabel getLbAlmacenero() {
		if (lbAlmacenero == null) {
			lbAlmacenero = new JLabel("Almacenero responsable:");
			lbAlmacenero.setBounds(10, 53, 185, 39);
		}
		return lbAlmacenero;
	}
	private JTextField getTxAlmacenero() {
		if (txAlmacenero == null) {
			txAlmacenero = new JTextField();
			txAlmacenero.setEditable(false);
			txAlmacenero.setBounds(184, 57, 221, 30);
			txAlmacenero.setColumns(10);
		}
		return txAlmacenero;
	}
	private JLabel getLblPedido() {
		if (lblPedido == null) {
			lblPedido = new JLabel("Pedido:");
			lblPedido.setBounds(10, 11, 136, 30);
		}
		return lblPedido;
	}
	private JTextField getTxPedido() {
		if (txPedido == null) {
			txPedido = new JTextField();
			txPedido.setEditable(false);
			txPedido.setBounds(181, 11, 224, 35);
			txPedido.setColumns(10);
		}
		return txPedido;
	}
	private JScrollPane getProductoPanel() {
		if (productoPanel == null) {
			productoPanel = new JScrollPane();
			productoPanel.setBounds(0, 133, 828, 246);
			productoPanel.setViewportView(getTabProductos());
		}
		return productoPanel;
	}
	private JLabel getLbProductos() {
		if (lbProductos == null) {
			lbProductos = new JLabel("Productos:");
			lbProductos.setBounds(20, 103, 192, 39);
		}
		return lbProductos;
	}
	
	private JTable getTabProductos() {
		if (tabProductos == null) {
			tabProductos = new JTable();
		}
		return tabProductos;
	}
	
	//Metodos auxiliares
	
	public JTextField getTextPedido() {
		return this.txPedido;
	}
	
	public JTextField getTextAlmacenero() {
		return this.txAlmacenero;
	}
	
	public JTable getTablaProductos() {
		return this.tabProductos;
	}
	
	public JFrame getFrame() {
		return this;
	}
	
	
}
