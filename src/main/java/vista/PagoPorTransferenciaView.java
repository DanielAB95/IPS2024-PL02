package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.PagoPorTransferenciaController;
import modelo.modelo.CarritoModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PagoPorTransferenciaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CarritoModel model; 
	private JLabel lblNewLabel;
	private JLabel lblIban;
	private JTextField txtEs;
	private JButton btnFinalizarCompra;
	private PagoPorTransferenciaController controller;
	

	/**
	 * Create the frame.
	 */
	public PagoPorTransferenciaView( CarritoModel modelo ) {
		setTitle("Pago por Transferencia");
		
		this.model = modelo;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblIban());
		contentPane.add(getTxtEs());
		contentPane.add(getBtnFinalizarCompra());
		
		this.controller = new PagoPorTransferenciaController(this.model, this);
		controller.initController();
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Para realizar el pago mediante transferencia bancaria, por favor, envíe el importe exacto al siguiente número de cuenta:\n");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			lblNewLabel.setBounds(24, 83, 954, 123);
		}
		return lblNewLabel;
	}
	private JLabel getLblIban() {
		if (lblIban == null) {
			lblIban = new JLabel("IBAN:");
			lblIban.setFont(new Font("Dialog", Font.BOLD, 18));
			lblIban.setBounds(256, 218, 85, 42);
		}
		return lblIban;
	}
	private JTextField getTxtEs() {
		if (txtEs == null) {
			txtEs = new JTextField();
			txtEs.setText("   ES91 2100 0418 4502 0005 1332");
			txtEs.setFont(new Font("Dialog", Font.PLAIN, 16));
			txtEs.setEditable(false);
			txtEs.setBounds(359, 218, 307, 48);
			txtEs.setColumns(10);
		}
		return txtEs;
	}
	public JButton getBtnFinalizarCompra() {
		if (btnFinalizarCompra == null) {
			btnFinalizarCompra = new JButton("Finalizar Compra");
			btnFinalizarCompra.setForeground(Color.WHITE);
			btnFinalizarCompra.setBackground(Color.GREEN);
			btnFinalizarCompra.setBounds(364, 383, 219, 34);
		}
		return btnFinalizarCompra;
	}
}
