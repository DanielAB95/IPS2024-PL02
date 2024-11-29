package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import controlador.CargaPaqueteController;
import vista.ClienteView.MyTableModel;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


public class CargaPaqueteView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnBotones;
	private JButton btCargaPaquetes;
	private JButton btEmpaquetado;
	private JButton btRecogida;
	private JButton btWorkOrder;
	private JButton btVolver;
	private JComboBox<String> cbRegion;
	private JLabel lbVehiculo;
	private JTextField txMatricula;
	private JLabel lbZonaReparto;
	private JScrollPane scpnPaquetes;
	private JTable tbPedidos;
	private JButton btEscanear;
	private JButton btFinalizar;
	private DefaultTableModel tableModelPaquetes;
	private JButton btRecepcionVehiculo;
	private JButton btnNewButton;
	private JLabel lbAlmacenero;
	private JTextField txAlmacenero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargaPaqueteController controller = new CargaPaqueteController();
					CargaPaqueteView frame = new CargaPaqueteView(controller);
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
	public CargaPaqueteView(CargaPaqueteController controller) {
		setResizable(false);
		setTitle("Carga Paquetes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPnBotones());
		contentPane.add(getCbRegion());
		contentPane.add(getLbVehiculo());
		contentPane.add(getTxMatricula());
		contentPane.add(getLbZonaReparto());
		contentPane.add(getScpnPaquetes());
		contentPane.add(getBtEscanear());
		contentPane.add(getBtFinalizar());
		contentPane.add(getBtRecepcionVehiculo());
		contentPane.add(getLbAlmacenero());
		contentPane.add(getTxAlmacenero());
		
		 controller.setView(this);
		 controller.init();
	}
	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setBounds(0, 602, 434, 59);
			pnBotones.setLayout(new GridLayout(0, 3, 0, 0));
			pnBotones.add(getBtVolver());
			pnBotones.add(getBtWorkOrder());
			pnBotones.add(getBtRecogida());
			pnBotones.add(getBtEmpaquetado());
			pnBotones.add(getBtCargaPaquetes());
			pnBotones.add(getBtnNewButton());
		}
		return pnBotones;
	}
	private JButton getBtCargaPaquetes() {
		if (btCargaPaquetes == null) {
			btCargaPaquetes = new JButton("Carga");
			btCargaPaquetes.setEnabled(false);
		}
		return btCargaPaquetes;
	}
	private JButton getBtEmpaquetado() {
		if (btEmpaquetado == null) {
			btEmpaquetado = new JButton("Empaquetado");
		}
		return btEmpaquetado;
	}
	private JButton getBtRecogida() {
		if (btRecogida == null) {
			btRecogida = new JButton("Recogida");
		}
		return btRecogida;
	}
	private JButton getBtWorkOrder() {
		if (btWorkOrder == null) {
			btWorkOrder = new JButton("WorkOrder");
		}
		return btWorkOrder;
	}
	private JButton getBtVolver() {
		if (btVolver == null) {
			btVolver = new JButton("Volver");
			btVolver.setEnabled(false);
		}
		return btVolver;
	}
	private JComboBox<String> getCbRegion() {
		if (cbRegion == null) {
			cbRegion = new JComboBox<String>();
			cbRegion.setBounds(58, 163, 261, 33);
			
		}
		return cbRegion;
	}
	private JLabel getLbVehiculo() {
		if (lbVehiculo == null) {
			lbVehiculo = new JLabel("Matricula:");
			lbVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbVehiculo.setBounds(58, 55, 126, 33);
		}
		return lbVehiculo;
	}
	private JTextField getTxMatricula() {
		if (txMatricula == null) {
			txMatricula = new JTextField();
			txMatricula.setFont(new Font("Tahoma", Font.BOLD, 18));
			txMatricula.setBounds(58, 85, 197, 39);
			txMatricula.setColumns(10);
		}
		return txMatricula;
	}
	private JLabel getLbZonaReparto() {
		if (lbZonaReparto == null) {
			lbZonaReparto = new JLabel("Zona reparto:");
			lbZonaReparto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbZonaReparto.setBounds(58, 137, 99, 26);
		}
		return lbZonaReparto;
	}
	private JScrollPane getScpnPaquetes() {
		if (scpnPaquetes == null) {
			scpnPaquetes = new JScrollPane();
			scpnPaquetes.setBounds(10, 270, 419, 236);
			scpnPaquetes.setViewportView(getTbPedidos());
		}
		return scpnPaquetes;
	}
	private JTable getTbPedidos() {
		if (tbPedidos == null) {
			Object[] columnNames = {"   Id   ", "  Fecha  ", "Cliente"};
			tableModelPaquetes = new MyTableModel(columnNames);
			tbPedidos = new JTable(tableModelPaquetes);
			tbPedidos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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
			btEscanear.setEnabled(false);
			btEscanear.setBounds(8, 517, 149, 65);
		}
		return btEscanear;
	}
	private JButton getBtFinalizar() {
		if (btFinalizar == null) {
			btFinalizar = new JButton("Finalizar");
			btFinalizar.setEnabled(false);
			btFinalizar.setBounds(254, 517, 159, 65);
		}
		return btFinalizar;
	}
	
	private JButton getBtRecepcionVehiculo() {
		if (btRecepcionVehiculo == null) {
			btRecepcionVehiculo = new JButton("Recepcion Vehiculo");
			btRecepcionVehiculo.setBounds(118, 210, 159, 49);
		}
		return btRecepcionVehiculo;
	}
	
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setEnabled(false);
		}
		return btnNewButton;
	}
	
	private JLabel getLbAlmacenero() {
		if (lbAlmacenero == null) {
			lbAlmacenero = new JLabel("Almacenero en sesion:");
			lbAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lbAlmacenero.setBounds(54, 14, 152, 33);
		}
		return lbAlmacenero;
	}
	private JTextField getTxAlmacenero() {
		if (txAlmacenero == null) {
			txAlmacenero = new JTextField();
			txAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txAlmacenero.setEditable(false);
			txAlmacenero.setBounds(186, 17, 213, 27);
			txAlmacenero.setColumns(10);
		}
		return txAlmacenero;
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
	
	public JTextField getTextMatricula() {
		return this.getTxMatricula();
	}
	
	public JComboBox<String> getComboBoxZonaReparto(){
		return this.getCbRegion();
	}
	
	public JButton getButtonRecepcionVehiculos() {
		return this.btRecepcionVehiculo;
	}
	
	public DefaultTableModel getTablePaquetesModel() {
		return this.tableModelPaquetes;
	}
	
	public JButton getButtonRecogida() {
		return this.btRecogida;
	}
	
	public JButton getButtonEmpaquetado() {
		return this.btEmpaquetado;
	}
	
	public JButton getButtonWorkOrder() {
		return this.btWorkOrder;
	}
	
	public JTextField getTextAlmacenero() {
		return this.txAlmacenero;
	}
	
}
