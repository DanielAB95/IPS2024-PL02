package controlador;

import modelo.modelo.WorkorderModel;
import vista.WorkorderView;

public class WorkorderController {
	
	private WorkorderView view;
	private WorkorderModel model;
	
	public WorkorderController(WorkorderModel model) {
		this.model = model; 
		
	}
	
	public void setView(WorkorderView workorderView) {
		this.view = workorderView;
	}
	
	public void init() {
		
	}
}
