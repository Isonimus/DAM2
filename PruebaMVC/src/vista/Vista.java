package vista;

import controlador.Controlador;

public class Vista {
	
	private Controlador controlador;
	
	public Vista(){
		
		super();
		init();
	}
	
	private void init(){
		
		System.out.println("Inicializando Vista...");
	}

	public Controlador getControlador() {
		
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		
		this.controlador = controlador;
		System.out.println("Controlador añadido a la Vista.");
	}

}
