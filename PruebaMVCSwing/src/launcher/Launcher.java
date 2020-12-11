package launcher;

import javax.swing.SwingUtilities;

import controlador.Controlador;
import modelo.Modelo;
import vista.VistaAutorSwing;
import vista.VistaCategoriaSwing;
import vista.VistaEditorialSwing;
import vista.VistaLibroSwing;
import vista.VistaPrincipalSwing;

public class Launcher {
	
	public Launcher() {
		
		super();
	}
	
	public void launch() {
		
		Modelo modelo = new Modelo(); //SERVICIOBBDD.MYSQL
		Controlador controlador = new Controlador(modelo);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// LANZAMIENTO DE LA GUI EN EL EVENT
				// DISPATCHING THREAD
				//new VistaAutorSwing(controlador);
				//new VistaCategoriaSwing(controlador);
				//new VistaEditorialSwing(controlador);
				//new VistaLibroSwing(controlador);
				new VistaPrincipalSwing(controlador);
			}
		});
	}

	public static void main(String[] args) {
		
		Launcher launcher = new Launcher();
		launcher.launch();
	}
}
