package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.InformeVentasTipoPagoController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class InformeVentasTipoPagoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
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
					InformeVentasTipoPagoController controller = new InformeVentasTipoPagoController();
					InformeVentasTipoPagoView frame = new InformeVentasTipoPagoView(controller);
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
	public InformeVentasTipoPagoView(InformeVentasTipoPagoController controller) {
		setResizable(false);
		setTitle("Informe Ventas: tipo de pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getScpVentas());
		contentPane.add(getBtInforme());
		
		controller.setView(this);
		controller.init();
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Informe de Ventas por tipo de pago");
			lblNewLabel.setBounds(10, 22, 778, 25);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		}
		return lblNewLabel;
	}
	private JScrollPane getScpVentas() {
		if (scpVentas == null) {
			scpVentas = new JScrollPane();
			scpVentas.setBounds(10, 58, 747, 355);
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
			btInforme.setBounds(10, 424, 140, 38);
		}
		return btInforme;
	}
	
	//Metodos auxiliares
	public JTable getTablaVentas() {
		return this.tbVentas;
	}
	public JButton getButtonInforme() {
		return this.btInforme;
	}
}
