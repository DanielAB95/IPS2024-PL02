package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.InformeVentasTipoMinoristaController;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class InformeVentasTipoMinoristaView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbInforme;
	private JScrollPane scpVentas; 
	private JTable tbVentas;
	private JButton btInforme;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeVentasTipoMinoristaController controller = new InformeVentasTipoMinoristaController();
					InformeVentasTipoMinoristaView frame = new InformeVentasTipoMinoristaView(controller);
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
	public InformeVentasTipoMinoristaView(InformeVentasTipoMinoristaController controller) {
		setResizable(false);
		setTitle("Informe Ventas : tipo minorista");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbInforme());
		contentPane.add(getScpVentas());
		contentPane.add(getBtInforme());
		
		controller.setView(this);
		controller.init();
	}

	private JLabel getLbInforme() {
		if (lbInforme == null) {
			lbInforme = new JLabel("Informe Ventas por minorista");
			lbInforme.setBounds(5, 18, 816, 25);
			lbInforme.setFont(new Font("Tahoma", Font.BOLD, 24));
			lbInforme.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbInforme;
	}
	private JScrollPane getScpVentas() {
		if (scpVentas == null) {
			scpVentas = new JScrollPane();
			scpVentas.setBounds(20, 54, 758, 364);
			scpVentas.setViewportView(getTbVentas());
		}
		return scpVentas;
	}
	private JTable getTbVentas() {
		if (tbVentas == null) {
			tbVentas = new JTable();
		}
		return tbVentas;
	}
	
	private JButton getBtInforme() {
		if (btInforme == null) {
			btInforme = new JButton("Volver a Informes");
			btInforme.setBounds(30, 429, 231, 42);
		}
		return btInforme;
	}
	
	//metodos Auxiliares
	
	public JTable getTablaVentas() {
		return this.tbVentas;
	}
	
	public JButton getButtonInforme() {
		return this.btInforme;
	}
	
}
