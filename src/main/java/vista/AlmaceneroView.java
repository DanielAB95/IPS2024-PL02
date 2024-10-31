package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.util.Database2;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlmaceneroView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbAlmacenero;
	private JTextField txAlmacenero;
	private JButton btPedidosPendientes;
	private Database2 db;
	private JButton btPlanosAlmacen;

	/**
	 * Create the frame.
	 */
	public AlmaceneroView(Database2 db) {
		setTitle("Almacenero: Inicio");
		this.db = db;
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 960);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbAlmacenero());
		contentPane.add(getTxAlmacenero());
		contentPane.add(getBtPedidosPendientes());
		contentPane.add(getBtPlanosAlmacen());
	}

	private JLabel getLbAlmacenero() {
		if (lbAlmacenero == null) {
			lbAlmacenero = new JLabel("Almacenero en terminal:");
			lbAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lbAlmacenero.setBounds(166, 69, 374, 57);
		}
		return lbAlmacenero;
	}
	private JTextField getTxAlmacenero() {
		if (txAlmacenero == null) {
			txAlmacenero = new JTextField();
			txAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			txAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			txAlmacenero.setBounds(114, 136, 426, 72);
			txAlmacenero.setColumns(10);
		}
		return txAlmacenero;
	}
	private JButton getBtPedidosPendientes() {
		if (btPedidosPendientes == null) {
			btPedidosPendientes = new JButton("Pedidos Pendientes");
			btPedidosPendientes.setFont(new Font("Tahoma", Font.PLAIN, 30));
			btPedidosPendientes.setBounds(134, 270, 374, 144);
		}
		return btPedidosPendientes;
	}
	
	private JButton getBtPlanosAlmacen() {
		if (btPlanosAlmacen == null) {
			btPlanosAlmacen = new JButton("Planos Almacen");
			btPlanosAlmacen.setFont(new Font("Tahoma", Font.PLAIN, 30));
			btPlanosAlmacen.setBounds(166, 771, 308, 113);
		}
		return btPlanosAlmacen;
	}
	
	//metodos Auxiliares
	
	public JTextField getTextAlmacenero() {
		return this.txAlmacenero;
	}
	
	public JButton getButtonPedidosPendientes() {
		return this.btPedidosPendientes;
	}
	
	public JButton getButtonPlanosAlmacen() {
		return this.btPlanosAlmacen;
	}
	
	public JFrame getFrame() {
		return this;
	}
	
	public Database2 getDatabase() {
		return this.db;
	}
	
}
