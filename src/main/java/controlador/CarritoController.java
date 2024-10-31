package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import modelo.modelo.CarritoModel;
import vista.CarritoView;

public class CarritoController {
	
	
	
	private CarritoView view;
	private CarritoModel modelo;
	
	public CarritoController(CarritoView v, CarritoModel m) {
		this.view = v;
		this.modelo = m;
	}
	
	public void initView() {
		view.getTextPrecioTotal().setText( String.valueOf(modelo.calcularPrecioTotal()) );
		view.getLblNombreUsuario().setText(modelo.getDto().getName());
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
					
					actualizaLblTotal();
				}
			}
		});
		
		//btn confirmar
		view.getBtnConfirmar().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				modelo.confirmarPedido();
				
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
                    
                    String nuevoPrecio = modelo.getPrecioPorNombre((String) view.getTableModel().getValueAt(fila, 0), nuevaCantidad);
                    
                    view.getTableModel().setValueAt(nuevoPrecio, fila, 2);
                    
                    view.getCarrito().cambiaCantidadCarrito((String)view.getTableModel().getValueAt(fila, 0), nuevaCantidad);
                   
                    actualizaLblTotal();
                    
                    
                }
            }
        });
	}
	
	private void actualizaLblTotal() {
		String formattedNumber = String.format("%.2f", view.getCarrito().getTotal());
		view.getTextPrecioTotal().setText( formattedNumber );
	}
}
