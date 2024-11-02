package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.ClienteLoginController;
import giis.demo.util.Database2;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ClienteLoginView extends JFrame {

	private JPanel contentPane;
	private Database2 db;
	private JLabel lblNombreUsuario;
	private JTextField textNombreUsuario;
	private JButton btnSiguiente;
	private ClienteLoginController controler;
	private JButton btnInvitado;

	/**
	 * Create the frame.
	 */
	public ClienteLoginView(Database2 db) {
		setTitle("Login");
		this.db = db;
		this.controler = new ClienteLoginController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNombreUsuario());
		contentPane.add(getTextNombreUsuario());
		contentPane.add(getBtnSiguiente());
		contentPane.add(getBtnInvitado());
		
		controler.init();
	}
	private JLabel getLblNombreUsuario() {
		if (lblNombreUsuario == null) {
			lblNombreUsuario = new JLabel("Nombre de Usuario: ");
			lblNombreUsuario.setBounds(64, 94, 145, 14);
		}
		return lblNombreUsuario;
	}
	
	public JTextField getTextNombreUsuario() {
		if (textNombreUsuario == null) {
			textNombreUsuario = new JTextField();
			textNombreUsuario.setBounds(257, 92, 179, 20);
			textNombreUsuario.setColumns(10);
		}
		return textNombreUsuario;
	}
	public JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			
			btnSiguiente.setBackground(new Color(50, 205, 50));
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setBounds(415, 227, 123, 23);
		}
		return btnSiguiente;
	}
	
	public Database2 getDb() {
		return db;
	}
	public JButton getBtnInvitado() {
		if (btnInvitado == null) {
			btnInvitado = new JButton("Continúa como Invitado");
			btnInvitado.setBounds(12, 226, 230, 25);
		}
		return btnInvitado;
	}
}