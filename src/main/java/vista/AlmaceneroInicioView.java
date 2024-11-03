package vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.AlmaceneroController;
import giis.demo.util.Database2;
import modelo.modelo.AlmaceneroModel;

public class AlmaceneroInicioView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txIdAlmacenero;
	private JLabel lbIdAlmacenero;
	private JButton btContinuar;
	private AlmaceneroModel model;
	private AlmaceneroController controller;
	private Database2 db;

	
	/**
	 * Create the frame.
	 */
	public AlmaceneroInicioView(Database2 db) {
		setTitle("Inicio sesion");
		this.db = db;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 854);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		model = new AlmaceneroModel(db);
		controller = new AlmaceneroController(this, model);
		controller.initController();
		 
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxIdAlmacenero());
		contentPane.add(getLbIdAlmacenero());
		contentPane.add(getBtContinuar());
		setLocationRelativeTo(null);
	}

	private JTextField getTxIdAlmacenero() {
		if (txIdAlmacenero == null) {
			txIdAlmacenero = new JTextField();
			txIdAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			txIdAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			txIdAlmacenero.setBounds(171, 234, 277, 136);
			txIdAlmacenero.setColumns(10);
		}
		return txIdAlmacenero;
	}
	private JLabel getLbIdAlmacenero() {
		if (lbIdAlmacenero == null) {
			lbIdAlmacenero = new JLabel("Escriba su id:");
			lbIdAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lbIdAlmacenero.setBounds(171, 144, 264, 73);
		}
		return lbIdAlmacenero;
	}
	
	private JButton getBtContinuar() {
		if (btContinuar == null) {
			btContinuar =  new JButton("Continuar");
			btContinuar.setFont(new Font("Tahoma", Font.PLAIN, 30));
			btContinuar.setBounds(195, 692, 264, 102);
		}
		return btContinuar;
	}
	
	//Metodos Auxiliares
	
	public JTextField getTextIdAlmacenero() {
		return this.txIdAlmacenero;
	}
	
	public JButton getButtonContinuar() {
		return this.getBtContinuar();
	}
	
	public JFrame getFrame() {
		return this;
	}
	
	public Database2 getDatabase() {
		return this.db;
	}
	
}
