package launcher;

import controlador.Controlador;
import modelo.Motor;
import vista.EsquemaColor;
import vista.InterfazLibreria;

/**
 * Inicializa la aplicación
 * LIBRERÍA ONLINE.
 * 
 * @author Iker Laforga
 *
 */
public class Launcher {

	public Launcher(){
		
		super();
	}
	
	/**
	 * Genera vistas (GUI), modelo (motor) y controlador (escucha)
	 * y los conecta entre sí.
	 * 
	 */
	public void launch(){
			
		InterfazLibreria gui = new InterfazLibreria(EsquemaColor.ESTILO_DEFAULT);
		Motor motor = new Motor();
		@SuppressWarnings("unused")
		Controlador controller = new Controlador(gui, motor);		
	}
	
	public static void main(String args[]) {
		
		Launcher launcher = new Launcher();
		launcher.launch();
	}
}
