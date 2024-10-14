package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.FacturaController;
import modelo.dto.PaqueteWrapper;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class FacturaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JComboBox<PaqueteWrapper> comboBox;
	private JButton btGenerar;
	private FacturaController fc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacturaView frame = new FacturaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FacturaView() {
		setTitle("Facturación");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTextArea());
		contentPane.add(getComboBox());
		contentPane.add(getBtGenerar());
		
		setLocationRelativeTo(null);
		
		fc = new FacturaController(this);
		fc.init();
	}

	public JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setTabSize(4);
			textArea.setEditable(false);
			textArea.setBounds(10, 67, 394, 329);
		}
		return textArea;
	}
	public JComboBox<PaqueteWrapper> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<>();
			comboBox.setBounds(10, 11, 394, 45);
		}
		return comboBox;
	}
	public JButton getBtGenerar() {
		if (btGenerar == null) {
			btGenerar = new JButton("Generar");
			btGenerar.setBounds(414, 373, 89, 23);
		}
		return btGenerar;
	}
}
