package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.InformeVentasTipoMinoristaController;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InformeVentasTipoMinoristaView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbInforme;
	private JScrollPane scpVentas;
	private JTable tbVentas;

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
		setTitle("Informe Ventas : tipo minorista");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLbInforme(), BorderLayout.NORTH);
		contentPane.add(getScpVentas(), BorderLayout.CENTER);
		
		controller.setView(this);
		controller.init();
	}

	private JLabel getLbInforme() {
		if (lbInforme == null) {
			lbInforme = new JLabel("Informe Ventas por tipo de  minorista");
			lbInforme.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lbInforme.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbInforme;
	}
	private JScrollPane getScpVentas() {
		if (scpVentas == null) {
			scpVentas = new JScrollPane();
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
	
	//metodos Auxiliares
	
	public JTable getTablaVentas() {
		return this.tbVentas;
	}
}
