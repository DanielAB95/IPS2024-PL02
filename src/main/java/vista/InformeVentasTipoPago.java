package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InformeVentasTipoPago extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JScrollPane scpVentas;
	private JTable tbVentas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InformeVentasTipoPago frame = new InformeVentasTipoPago();
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
	public InformeVentasTipoPago() {
		setTitle("Informe Ventas: tipo de pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblNewLabel(), BorderLayout.NORTH);
		contentPane.add(getScpVentas(), BorderLayout.CENTER);
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Informe de Ventas por tipo de pago");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return lblNewLabel;
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
}
