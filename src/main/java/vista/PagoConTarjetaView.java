package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.PagoConTarjetaController;
import modelo.modelo.CarritoModel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PagoConTarjetaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNumeroTarjeta;
	private JLabel lblFecha;
	private JLabel lblCvv;
	private JTextField textTarjeta;
	private JTextField textVencimiento;
	private JTextField textCvv;
	private JButton btnFinalizar;
	private PagoConTarjetaController controller;
	private CarritoModel model;
	

	/**
	 * Create the frame.
	 */
	public PagoConTarjetaView(CarritoModel m) {
		this.model = m;
		setTitle("Pago con Tarjeta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNumeroTarjeta());
		contentPane.add(getLblFecha());
		contentPane.add(getLblCvv());
		contentPane.add(getTextTarjeta());
		contentPane.add(getTextVencimiento());
		contentPane.add(getTextCvv());
		contentPane.add(getBtnFinalizar());
		
		this.controller = new PagoConTarjetaController(model,this);
		controller.initController();
	}

	private JLabel getLblNumeroTarjeta() {
		if (lblNumeroTarjeta == null) {
			lblNumeroTarjeta = new JLabel("Número de Tarjeta: ");
			lblNumeroTarjeta.setFont(new Font("Dialog", Font.BOLD, 16));
			lblNumeroTarjeta.setBounds(237, 97, 199, 15);
		}
		return lblNumeroTarjeta;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha de Vencimiento: ");
			lblFecha.setFont(new Font("Dialog", Font.BOLD, 16));
			lblFecha.setBounds(237, 180, 250, 15);
		}
		return lblFecha;
	}
	private JLabel getLblCvv() {
		if (lblCvv == null) {
			lblCvv = new JLabel("CVV (Código de Seguridad): ");
			lblCvv.setFont(new Font("Dialog", Font.BOLD, 16));
			lblCvv.setBounds(237, 272, 250, 15);
		}
		return lblCvv;
	}
	private JTextField getTextTarjeta() {
		if (textTarjeta == null) {
			textTarjeta = new JTextField();
			textTarjeta.setBounds(496, 92, 216, 28);
			textTarjeta.setColumns(10);
		}
		return textTarjeta;
	}
	private JTextField getTextVencimiento() {
		if (textVencimiento == null) {
			textVencimiento = new JTextField();
			textVencimiento.setBounds(496, 175, 216, 28);
			textVencimiento.setColumns(10);
		}
		return textVencimiento;
	}
	private JTextField getTextCvv() {
		if (textCvv == null) {
			textCvv = new JTextField();
			textCvv.setBounds(496, 267, 114, 28);
			textCvv.setColumns(10);
		}
		return textCvv;
	}
	public JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar Compra");
			
			btnFinalizar.setBackground(Color.GREEN);
			btnFinalizar.setForeground(Color.WHITE);
			btnFinalizar.setBounds(352, 392, 216, 25);
		}
		return btnFinalizar;
	}
}
