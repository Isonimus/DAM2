package launcher;

import controlador.Controlador;
import modelo.Modelo;
import vista.VistaPrincipal;

public class Launcher {
	
	public Launcher() {
		
		super();
	}
	
	public void launch() {
		
		Modelo modelo = new Modelo(); //SERVICIOBBDD.MYSQL
		Controlador controlador = new Controlador(modelo);
		VistaPrincipal vista = new VistaPrincipal(controlador);
		vista.getAccion();
	}

	public static void main(String[] args) {
		
		Launcher launcher = new Launcher();
		launcher.launch();
	}
}
