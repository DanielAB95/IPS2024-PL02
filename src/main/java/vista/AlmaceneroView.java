package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AlmaceneroView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbAlmacenero;
	private JTextField txAlmacenero;
	private JButton btPedidosPendientes;

	/**
	 * Create the frame.
	 */
	public AlmaceneroView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbAlmacenero());
		contentPane.add(getTxAlmacenero());
		contentPane.add(getBtPedidosPendientes());
	}

	private JLabel getLbAlmacenero() {
		if (lbAlmacenero == null) {
			lbAlmacenero = new JLabel("Almacenero en terminal:");
			lbAlmacenero.setBounds(10, 11, 140, 33);
		}
		return lbAlmacenero;
	}
	private JTextField getTxAlmacenero() {
		if (txAlmacenero == null) {
			txAlmacenero = new JTextField();
			txAlmacenero.setBounds(163, 17, 232, 20);
			txAlmacenero.setColumns(10);
		}
		return txAlmacenero;
	}
	private JButton getBtPedidosPendientes() {
		if (btPedidosPendientes == null) {
			btPedidosPendientes = new JButton("Pedidos Pendientes");
			btPedidosPendientes.setBounds(270, 183, 154, 67);
		}
		return btPedidosPendientes;
	}
	
	//metodos Auxiliares
	
	public JTextField getTextAlmacenero() {
		return this.txAlmacenero;
	}
	
	public JButton getButtonPedidosPendientes() {
		return this.btPedidosPendientes;
	}
	
	public JFrame getFrame() {
		return this;
	}
}
