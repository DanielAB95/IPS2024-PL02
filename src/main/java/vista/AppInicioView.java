package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.AppInicioController;
import giis.demo.util.Database2;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppInicioView extends JFrame {

	private JPanel contentPane;
	private JButton btnCliente;
	private JButton btnAlmacen;
	private Database2 database;
	private AppInicioController control;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					//creo bd solo una vez
					Database2 db =new Database2();
					db.createDatabase(false);
					
					//lleno bd solo una vez
					db.loadDatabase();
					
					AppInicioView frame = new AppInicioView(db);
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
	public AppInicioView(Database2 db) {
		
		//creo bd solo una vez
		this.database = db;
		
		
		this.control = new AppInicioController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnCliente());
		contentPane.add(getBtnAlmacen());
		
		control.initController();
	}
	
	public Database2 getDatabase() {
		return this.database;
	}


	public JButton getBtnCliente() {
		if (btnCliente == null) {
			btnCliente = new JButton("Cliente");
			
			btnCliente.setBounds(335, 227, 89, 23);
		}
		return btnCliente;
	}
	private JButton getBtnAlmacen() {
		if (btnAlmacen == null) {
			btnAlmacen = new JButton("Almacen");
			btnAlmacen.setBounds(10, 227, 89, 23);
		}
		return btnAlmacen;
	}
}
