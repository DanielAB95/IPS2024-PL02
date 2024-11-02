package vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.PedidoController;
import giis.demo.util.Database2;
import modelo.modelo.PedidoModel;


public class PedidoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane tablePanel;
	private PedidoModel model;
	private PedidoController controller;
	private JLabel lbAlmacenero;
	private JTextField txAlmacenero;
	private Database2 db;
	private JLabel lbPedidosPendientes;
	private JTable tbPedidos;
	private JPanel pnMenu;
	private JButton btGenerarWorkOrder;
	private JButton btRecogida;
	private JButton btnEmpaquetado;
	private JButton btnNewButton_3;


	/**
	 * Create the frame.
	 */
	public PedidoView(Database2 db, int idAlmacenero) {
		setResizable(false);
		this.db = db;
		setTitle("Almacenero: Generar WorkOrder");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 854);
		setLocationRelativeTo(null);
		contentPane =  new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTablePanel());
		contentPane.add(getLbAlmacenero());
		contentPane.add(getTextField());
		contentPane.add(getLbPedidosPendientes());
		contentPane.add(getPnMenu());
		
		model = new PedidoModel(db, idAlmacenero);
		controller = new PedidoController(this, model);
		controller.initController();
		controller.initView();
	}

	private JScrollPane getTablePanel() {
	    if (tablePanel == null) {
	        tablePanel = new JScrollPane(); 
	        tablePanel.setBounds(18, 239, 436, 467);
	        tablePanel.setPreferredSize(new Dimension(300, 300));
	        tablePanel.setViewportView(getTbPedidos());
	    }
	    return tablePanel;
	}
	
	private JLabel getLbAlmacenero() {
		if (lbAlmacenero == null) {
			lbAlmacenero = new JLabel("Almacenero en sesion:");
			lbAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lbAlmacenero.setBounds(18, 11, 426, 75);
		}
		return lbAlmacenero;
	}
	private JTextField getTextField() {
		if (txAlmacenero == null) {
			txAlmacenero = new JTextField();
			txAlmacenero.setFont(new Font("Tahoma", Font.PLAIN, 30));
			txAlmacenero.setHorizontalAlignment(SwingConstants.CENTER);
			txAlmacenero.setEditable(false);
			txAlmacenero.setBounds(18, 97, 436, 75);
			txAlmacenero.setColumns(10);
		}
		return txAlmacenero;
	}
	
	private JLabel getLbPedidosPendientes() {
		if (lbPedidosPendientes == null) {
			lbPedidosPendientes = new JLabel("No hay pedidos pendientes");
			lbPedidosPendientes.setVisible(false);
			lbPedidosPendientes.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lbPedidosPendientes.setBounds(18, 193, 377, 42);
		}
		return lbPedidosPendientes;
	}
	
	private JTable getTbPedidos() {
		if (tbPedidos == null) {
			tbPedidos = new JTable();
			tbPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return tbPedidos;
	}
	
	private JPanel getPnMenu() {
		if (pnMenu == null) {
			pnMenu = new JPanel();
			pnMenu.setBounds(0, 726, 464, 100);
			pnMenu.setLayout(new GridLayout(0, 4, 0, 0));
			pnMenu.add(getBtGenerarWorkOrder());
			pnMenu.add(getBtRecogida());
			pnMenu.add(getBtnEmpaquetado());
			pnMenu.add(getBtnNewButton_3());
		}
		return pnMenu;
	}
	private JButton getBtGenerarWorkOrder() {
		if (btGenerarWorkOrder == null) {
			btGenerarWorkOrder = new JButton("WorkOrder");
			btGenerarWorkOrder.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		return btGenerarWorkOrder;
	}
	private JButton getBtRecogida() {
		if (btRecogida == null) {
			btRecogida = new JButton("Recogida");
		}
		return btRecogida;
	}
	private JButton getBtnEmpaquetado() {
		if (btnEmpaquetado == null) {
			btnEmpaquetado = new JButton("Empaquetado");
		}
		return btnEmpaquetado;
	}
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("New button");
		}
		return btnNewButton_3;
	}
	
	//Metodos Auxiliares
	
	public JTextField getTextAlmacenero() {
		return this.txAlmacenero;
	}
	
	public Database2 getDatabase() {
		return this.db;
	}
	
	public JTable getTablaPedidos(){
		return this.tbPedidos;
	}
	
	public JLabel getLabelPedidosPendientes() {
		return this.lbPedidosPendientes;
	}
	
	public JButton getButtonGenerarWorkOrders() {
		return this.btGenerarWorkOrder;
	}
	
	public JButton getButtonRecogida() {
		return this.btRecogida;
	}
	
	public JButton getButtonEmpaquetado() {
		return this.btnEmpaquetado;
	}
}
