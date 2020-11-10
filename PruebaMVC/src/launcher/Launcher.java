package launcher;

import controlador.Controlador;
import modelo.Modelo;
import vista.VistaAutor;

public class Launcher {
	
	public Launcher() {
		
		super();
	}
	
	public void launch() {
		
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo);
		VistaAutor vista = new VistaAutor(controlador);
		vista.getAccion();
		
	}

	public static void main(String[] args) {
		
		Launcher launcher = new Launcher();
		launcher.launch();
	}

}
