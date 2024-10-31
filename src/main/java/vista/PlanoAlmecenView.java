package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;

public class PlanoAlmecenView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbPlanosAlmacen;


	/**
	 * Create the frame.
	 */
	public PlanoAlmecenView() {
		setTitle("Almacenero: Planos ALmacen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 960);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLbPlanosAlmacen());
	}

	private JLabel getLbPlanosAlmacen() {
		if (lbPlanosAlmacen == null) {
			lbPlanosAlmacen = new JLabel("");
			lbPlanosAlmacen.setBounds(0, 0, 619, 916);
		}
		return lbPlanosAlmacen;
	}
	
	//Metodos auxiliares
	
	public JFrame getFrame() {
		return this;
	}
	
	public JLabel getLabelAlmacen() {
		return this.lbPlanosAlmacen;
	}
}
