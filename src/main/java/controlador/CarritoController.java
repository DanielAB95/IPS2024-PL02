package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import modelo.modelo.CarritoModel;
import vista.CarritoView;
import vista.PagoConTarjetaView;
import vista.PagoPorTransferenciaView;

public class CarritoController {
	
	
	//private ClienteView cView; erw
	private CarritoView view;
	private CarritoModel modelo;
	
	public CarritoController(CarritoView v, CarritoModel m) {
		this.view = v;
		this.modelo = m;
		//this.cView = new ClienteView(view.getDatabase());
	}
	
	public void initView() {
		//view.getTextPrecioTotal().setText( String.valueOf(modelo.calcularPrecioTotal()) );
		actualizaLblTotal();
		
		if (modelo.doesClientExist(modelo.getDto().nombreUsusario)) {
			rellenaDatosCliente();
		}
		
	}
	
	private void rellenaDatosCliente() {
		Object[] datosCliente = modelo.getClient(modelo.getDto().nombreUsusario);
		view.getTextNombre().setText((String) datosCliente[2]);
		view.getTextTelefono().setText((String) datosCliente[3]);
		view.getTextPais().setText((String) datosCliente[4]);
		view.getTextRegion().setText((String) datosCliente[5]);
		view.getTextCiudad().setText((String) datosCliente[6]);
		view.getTextCalle().setText((String) datosCliente[7]);
		
		// hacer que al iniciar sesion si exista el usuario y 
		// que se pueda acceder como invitado
	}
	
	
	public void initController() {
		
		
		//btn eliminar
		view.getBtnEliminar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int filaSeleccionada = view.getTable().getSelectedRow();
				if (filaSeleccionada != -1) {
					String nombreProducto = (String) view.getTable().getValueAt(filaSeleccionada, 0);
					view.getTableModel().removeRow(filaSeleccionada);
					view.getCarrito().removeFromCarrito(nombreProducto);
					
					if (modelo.doesClientExist(modelo.getDto().nombreUsusario)) {
							modelo.eliminaProductoCarrito(nombreProducto, view.getDto().nombreUsusario);
					}
					
					actualizaLblTotal();
				}
			}
		});
		
		//btn confirmar
		view.getBtnConfirmar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (modelo.checkStockCarrito() && checkTodosLosCamposRellenados() && checkMetodoDePago()) {
					if (modelo.doesClientExist(modelo.getDto().nombreUsusario))
						confirmarPedido();
					else {
						creaNuevoCliente();
						confirmarPedido();
					}
				}
			}					
		});
		
		//action listener apra la JTABLE
		view.getTableModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // verificar si el cambio es en la columna 1 (Cantidad)
                if (e.getColumn() == 1 && e.getType() == TableModelEvent.UPDATE) {
                    int fila = e.getFirstRow(); //devuelve la fila que cambio
                    
                    // obtener la cantidad modificada
                    int nuevaCantidad = Integer.valueOf((String) view.getTableModel().getValueAt(fila, 1));
                    
                    if (nuevaCantidad > 0) {
	                    String nuevoPrecio = modelo.getPrecioPorNombre(view.getDto().tipoCliente, (String) view.getTableModel().getValueAt(fila, 0), nuevaCantidad);
	                    
	                    view.getTableModel().setValueAt(nuevoPrecio, fila, 2);
	                    
	                    view.getCarrito().cambiaCantidadCarrito((String)view.getTableModel().getValueAt(fila, 0), nuevaCantidad);
	                   
	                    if (!view.getTextNombre().getText().equals("Invitado") && modelo.doesClientExist(modelo.getDto().nombreUsusario)) { //!modelo.getDto().nombreUsusario.equals("Invitado")
	                    	modelo.modificarCantidadCarrito((String)view.getTableModel().getValueAt(fila, 0), nuevaCantidad, view.getDto().nombreUsusario);
	                    }
	                    	
	                    actualizaLblTotal();
                    
                    } else {
                    	JOptionPane.showMessageDialog(view, "La cantidad del Producto debe ser mayor que 0.");
                    	view.getTableModel().setValueAt("1", fila, 1);
                    }
                }
            }
        });
	}
	
	private void creaNuevoCliente() {
		
		String[] clientData = new String[9];
		clientData[0] = UUID.randomUUID().toString();
		clientData[1] = "INVITADO" + clientData[0].substring(0,10);
		clientData[2] = view.getTextNombre().getText();
		clientData[3] = view.getTextTelefono().getText();
		clientData[4] = view.getTextPais().getText();
		clientData[5] = view.getTextRegion().getText();
		clientData[6] = view.getTextCiudad().getText();
		clientData[7] = view.getTextCalle().getText();
		clientData[8] = "INVITADO"; 
		
		modelo.createNewClient(clientData);
		//modelo.getDto().setName(clientData[1]);
		modelo.getDto().nombreUsusario = clientData[1];
	}
	
	private void confirmarPedido() {
		if (modelo.checkStockCarrito()) {
			if (view.getRdbtnTransferencia().isSelected()) {
				
				PagoPorTransferenciaView vista = new PagoPorTransferenciaView(modelo);
				vista.setLocationRelativeTo(view);
				view.dispose();
				
				if (!view.getLblNombreUsuario().getText().equals("Invitado")) { //!view.getLblNombreUsuario().getText().equals("Invitado")
					modelo.borraCarritoCliente(view.getDto().nombreUsusario); //puede moverse a controlador de la siguiente ventana
				}
				vista.setVisible(true);
				
			} else if (view.getRdbtnContrarrembolso().isSelected()){
				
				JOptionPane.showMessageDialog(view, "¡Gracias por tu compra!" +
	                       " Hemos recibido tu pedido y se enviará a la dirección proporcionada");
				
				if (!view.getLblNombreUsuario().getText().equals("Invitado")) {
					modelo.borraCarritoCliente(view.getDto().nombreUsusario); 
				}
				modelo.confirmarPedido("Contrarrembolso");
			} else {
				
				PagoConTarjetaView vista = new PagoConTarjetaView(modelo);
				vista.setLocationRelativeTo(view);
				view.dispose();
				
				if (!view.getLblNombreUsuario().getText().equals("Invitado")) {
					modelo.borraCarritoCliente(view.getDto().nombreUsusario); //puede moverse a controlador de la siguiente ventana
				}
				vista.setVisible(true);
			}
		}
	}
	
	private boolean checkTodosLosCamposRellenados() {
		if (view.getTextCalle().getText().isEmpty() ||
				view.getTextCiudad().getText().isEmpty() || 
				view.getTextNombre().getText().isEmpty() || 
				view.getTextPais().getText().isEmpty() ||
				view.getTextRegion().getText().isEmpty() ||
				view.getTextTelefono().getText().isEmpty()) {
			
			JOptionPane.showMessageDialog(view, "Por favor, rellene todos los campos");
			return false;
		}
			
		return true;
	}
	
//	private boolean checkUsuarioValido() {
//		boolean result =  modelo.doesClientExist(view.getTextNombreUsuario().getText());
//		if (result) {
//			JOptionPane.showMessageDialog(view, "Nombre de usuario no válido, ya existe");
//			return false;
//		} else 
//			return true;
//	}
	
	private boolean checkMetodoDePago() {
		if (view.getRdbtnTransferencia().isSelected()
				|| view.getRdbtnContrarrembolso().isSelected()
				|| view.getRdbtnTarjetaDeCrdito().isSelected()) {
			return true;
		} else {
			JOptionPane.showMessageDialog(view, "Por favor, Seleccione un método de pago");
			return false;
		}
	}
	
	private void actualizaLblTotal() {
		
		String formattedNumber = String.format("%.2f", view.getCarrito().getTotalConIVA(view.getDto().tipoCliente));
		view.getTextPrecioTotal().setText( formattedNumber );
		
		
		formattedNumber = String.format("%.2f", view.getCarrito().getTotalSinIVA(view.getDto().tipoCliente));
		view.getTextPrecioTotalSinIVA().setText(formattedNumber);
		
		formattedNumber = String.format("%.2f", view.getCarrito().getIVAtotalAñadido(view.getDto().tipoCliente));
		view.getTextIVAtotal().setText(formattedNumber);
	}
}
