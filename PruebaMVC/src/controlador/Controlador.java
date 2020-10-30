package controlador;

import modelo.Modelo;
import vista.Vista;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	
	public Controlador(Modelo modelo, Vista vista) {
		
		super();
		this.modelo = modelo;
		this.vista = vista;
		init();
	}
	
	private void init(){
		
		System.out.println("Inicializando Controlador...");
		this.vista.setControlador(this);
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Vista getVista() {
		return vista;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}
}
