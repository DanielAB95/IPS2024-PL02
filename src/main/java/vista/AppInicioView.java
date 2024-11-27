package vista;


import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.AppInicioController;
import giis.demo.util.Database2;


public class AppInicioView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCliente;
	private JButton btnAlmacen;
	private Database2 database;
	private AppInicioController control;
	private JButton btInformes;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					
					//creo bd solo una vez
					Database2 db =new Database2();
					db.createDatabase(false);
				
					//lleno bd solo una vez					
					db.loadDatabase();

//					Database2 db = new Database2();
//
//                    // Verificar si la base de datos ya existe
//                    if (!dbExists(db.getUrl())) {
//                        // Crea la base de datos solo si no existe
//                        db.createDatabase(false);
//                        // Carga datos iniciales solo si se creó la base de datos
//                        db.loadDatabase();
//                  } else {
//                   	//db.loadDatabase();

                   //}
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
		contentPane.add(getBtInformes());
		
		control.initController();
		setLocationRelativeTo(null);
		
		
		//mostrarClientes();
	}
	
	
	private static boolean dbExists(String dbUrl) {
        // Comprueba si el archivo de base de datos existe
        File dbFile = new File(dbUrl.replace("jdbc:sqlite:", ""));
        return dbFile.exists();
    }
	
	private void mostrarClientes() {
		// SQL_GET_CLIENTES
		List<Object[]> usuarios = database.executeQueryArray("select * from cliente");
		System.out.println("----------------- CLIENTES ------------------");
		
		for (int i = 0; i < usuarios.size(); i++) {
			for (int j = 0; j < usuarios.get(i).length; j++) {
				System.out.print(usuarios.get(i)[j] + " ");
			}
			System.out.println();
		}
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
	public JButton getBtnAlmacen() {
		if (btnAlmacen == null) {
			btnAlmacen = new JButton("Almacen");
			btnAlmacen.setBounds(10, 227, 89, 23);
		}
		return btnAlmacen;
	}
	public JButton getBtInformes() {
		if (btInformes == null) {
			btInformes = new JButton("Gerente");
			btInformes.setBounds(172, 227, 89, 23);
		}
		return btInformes;
	}
}
