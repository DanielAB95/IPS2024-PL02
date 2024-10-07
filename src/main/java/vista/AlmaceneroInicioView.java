package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.AlmaceneroController;
import giis.demo.util.Database2;
import modelo.modelo.AlmaceneroModel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AlmaceneroInicioView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txIdAlmacenero;
	private JLabel lbIdAlmacenero;
	private JButton btContinuar;
	private AlmaceneroModel model;
	private AlmaceneroController controller;
	private AlmaceneroView view;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//creo bd
					Database2 db =new Database2();
					db.createDatabase(false);
					
					//lleno bd
					db.loadDatabase();
					
					AlmaceneroInicioView frame = new AlmaceneroInicioView();
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
	public AlmaceneroInicioView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 132);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		model = new AlmaceneroModel();
		view = new AlmaceneroView();
		controller = new AlmaceneroController(this, view, model);
		//controller.initView();
		controller.initController();
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxIdAlmacenero());
		contentPane.add(getLbIdAlmacenero());
		contentPane.add(getBtContinuar());
	}

	private JTextField getTxIdAlmacenero() {
		if (txIdAlmacenero == null) {
			txIdAlmacenero = new JTextField();
			txIdAlmacenero.setBounds(101, 13, 118, 28);
			txIdAlmacenero.setColumns(10);
		}
		return txIdAlmacenero;
	}
	private JLabel getLbIdAlmacenero() {
		if (lbIdAlmacenero == null) {
			lbIdAlmacenero = new JLabel("Escriba su id:");
			lbIdAlmacenero.setBounds(10, 11, 118, 32);
		}
		return lbIdAlmacenero;
	}
	
	private JButton getBtContinuar() {
		if (btContinuar == null) {
			btContinuar =  new JButton("Continuar");
			btContinuar.setBounds(52, 52, 138, 30);
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
	
}
