package vista;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.InformeMenuController;
import giis.demo.util.Database2;

public class InformeMenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btInnformeAlmaceneros;
	private JButton btInformesPaquetes;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					InformeMenuController controller = new InformeMenuController(new Database2());
					InformeMenuView frame = new InformeMenuView(controller);
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
	public InformeMenuView(InformeMenuController controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnNewButton());
		contentPane.add(getBtnNewButton_1());
		contentPane.add(getBtInnformeAlmaceneros());
		contentPane.add(getBtInformesPaquetes());
		setLocationRelativeTo(null);
		
		controller.setView(this);
		controller.init();
	}
	public JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("New button");
			btnNewButton.setBounds(125, 194, 165, 50);
		}
		return btnNewButton;
	}
	public JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("New button");
			btnNewButton_1.setBounds(125, 133, 165, 50);
		}
		return btnNewButton_1;
	}
	public JButton getBtInnformeAlmaceneros() {
		if (btInnformeAlmaceneros == null) {
			btInnformeAlmaceneros = new JButton("Informe Almaceneros");
			btInnformeAlmaceneros.setBounds(125, 72, 165, 50);
		}
		return btInnformeAlmaceneros;
	}
	public JButton getBtInformesPaquetes() {
		if (btInformesPaquetes == null) {
			btInformesPaquetes = new JButton("Paquetes Listos");
			btInformesPaquetes.setBounds(125, 11, 165, 50);
		}
		return btInformesPaquetes;
	}
}
