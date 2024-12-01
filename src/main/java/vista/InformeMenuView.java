package vista;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.InformeMenuController;
import giis.demo.util.Database2;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

public class InformeMenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btInformeProductosReponer;
	private JButton btInformeVentasTipoPago;
	private JButton btInnformeAlmaceneros;
	private JButton btInformesPaquetes;
	private JPanel panel;
	private JButton btInformeVentasTipoUsuario;
	private JButton btInformeVentasMinorista;

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
		contentPane.add(getBtInformeProductosReponer());
		contentPane.add(getBtInnformeAlmaceneros());
		contentPane.add(getBtInformesPaquetes());
		contentPane.add(getPanel());
		setLocationRelativeTo(null);
		
		controller.setView(this);
		controller.init();
	}
	public JButton getBtInformeProductosReponer() {
		if (btInformeProductosReponer == null) {
			btInformeProductosReponer = new JButton("Productos a reponer");
			btInformeProductosReponer.setBounds(125, 133, 165, 50);
		}
		return btInformeProductosReponer;
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
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setName("");
			panel.setBorder(new TitledBorder(null, "Informe Ventas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(59, 194, 333, 56);
			panel.setLayout(new GridLayout(1, 0, 0, 0));
			panel.add(getBtInformeVentasTipoUsuario());
			panel.add(getBtInformeVentasTipoPago());
			panel.add(getBtInformeVentasMinorista());
		}
		return panel;
	}
	
	public JButton getBtInformeVentasTipoPago() {
		if (btInformeVentasTipoPago == null) {
			btInformeVentasTipoPago = new JButton("Tipo Pago");
		}
		return btInformeVentasTipoPago;
	}
	
	public JButton getBtInformeVentasTipoUsuario() {
		if (btInformeVentasTipoUsuario == null) {
			btInformeVentasTipoUsuario = new JButton("Tipo Usuario");
		}
		return btInformeVentasTipoUsuario;
	}
	public JButton getBtInformeVentasMinorista() {
		if (btInformeVentasMinorista == null) {
			btInformeVentasMinorista = new JButton("Minorista");
		}
		return btInformeVentasMinorista;
	}
}
