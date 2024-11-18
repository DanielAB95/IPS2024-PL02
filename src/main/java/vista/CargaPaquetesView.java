package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import vista.ClienteView.MyTableModel;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CargaPaquetesView extends JFrame {

	private JPanel contentPane;
	private JPanel pnBotones;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JComboBox<String> cbRegion;
	private JLabel lbVehiculo;
	private JTextField textField;
	private JLabel lbZonaReparto;
	private JScrollPane scpnPaquetes;
	private JTable tbPedidos;
	private JButton btEscanear;
	private JButton btFinalizar;
	private DefaultTableModel tableModelPaquetes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargaPaquetesView frame = new CargaPaquetesView();
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
	public CargaPaquetesView() {
		setResizable(false);
		setTitle("Carga Paquetes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 854);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPnBotones());
		contentPane.add(getCbRegion());
		contentPane.add(getLbVehiculo());
		contentPane.add(getTextField());
		contentPane.add(getLbZonaReparto());
		contentPane.add(getScpnPaquetes());
		contentPane.add(getBtEscanear());
		contentPane.add(getBtFinalizar());
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setBounds(0, 745, 464, 59);
			pnBotones.setLayout(new GridLayout(1, 5, 0, 0));
			pnBotones.add(getBtnNewButton_4());
			pnBotones.add(getBtnNewButton_3());
			pnBotones.add(getBtnNewButton_2());
			pnBotones.add(getBtnNewButton_1());
			pnBotones.add(getBtnNewButton());
		}
		return pnBotones;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("New button");
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("New button");
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("New button");
		}
		return btnNewButton_2;
	}
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("New button");
		}
		return btnNewButton_3;
	}
	private JButton getBtnNewButton_4() {
		if (btnNewButton_4 == null) {
			btnNewButton_4 = new JButton("New button");
		}
		return btnNewButton_4;
	}
	private JComboBox<String> getCbRegion() {
		if (cbRegion == null) {
			cbRegion = new JComboBox<String>();
			cbRegion.setBounds(83, 164, 261, 49);
		}
		return cbRegion;
	}
	private JLabel getLbVehiculo() {
		if (lbVehiculo == null) {
			lbVehiculo = new JLabel("Matricula:");
			lbVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbVehiculo.setBounds(103, 26, 126, 33);
		}
		return lbVehiculo;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.BOLD, 18));
			textField.setBounds(103, 59, 197, 49);
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLbZonaReparto() {
		if (lbZonaReparto == null) {
			lbZonaReparto = new JLabel("Zona reparto:");
			lbZonaReparto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbZonaReparto.setBounds(83, 139, 99, 26);
		}
		return lbZonaReparto;
	}
	private JScrollPane getScpnPaquetes() {
		if (scpnPaquetes == null) {
			scpnPaquetes = new JScrollPane();
			scpnPaquetes.setBounds(10, 237, 444, 377);
			scpnPaquetes.setViewportView(getTbPedidos());
		}
		return scpnPaquetes;
	}
	private JTable getTbPedidos() {
		if (tbPedidos == null) {
			Object[] columnNames = {"   Id   ", "  Fecha  ", "Cliente"};
			tableModelPaquetes = new MyTableModel(columnNames);
			tbPedidos = new JTable(tableModelPaquetes);
			TableColumnModel columnModel = tbPedidos.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(40);
			columnModel.getColumn(1).setPreferredWidth(60);
			columnModel.getColumn(2).setPreferredWidth(100);
		}
		return tbPedidos;
	}
	private JButton getBtEscanear() {
		if (btEscanear == null) {
			btEscanear = new JButton("Escanear");
			btEscanear.setBounds(33, 647, 149, 65);
		}
		return btEscanear;
	}
	private JButton getBtFinalizar() {
		if (btFinalizar == null) {
			btFinalizar = new JButton("Finalizar");
			btFinalizar.setBounds(270, 647, 159, 65);
		}
		return btFinalizar;
	}
	
	//Metodos auxiliares
	
	public JButton getButtonFinalizar() {
		return this.btFinalizar;
	}
	
	public JButton getButtonEscanear() {
		return this.btEscanear;
	}
	
	public JTable getTablePaquetes() {
		return this.tbPedidos;
	}
}
