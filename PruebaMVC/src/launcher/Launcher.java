package launcher;

import controlador.Controlador;
import modelo.Modelo;
import vista.Vista;

public class Launcher {
	
	public Launcher() {
		
		super();
	}
	
	public void launch() {
		
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		@SuppressWarnings("unused")
		Controlador controlador = new Controlador(modelo, vista);
	}

	public static void main(String[] args) {
		
		Launcher launcher = new Launcher();
		launcher.launch();
	}

}
