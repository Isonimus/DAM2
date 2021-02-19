package mediator;
/**
 * IMPLEMENTACIÓN CONCRETA
 * DEL COLEGA
 * @author Iker Laforga
 *
 */
public class ColegaConcreto extends Colega{
	
	public ColegaConcreto(String nombre, Mediador mediador) {
		super(nombre, mediador);
	}

	@Override
	public void enviar(String mensaje) {
		
		mediador.enviar(mensaje, this); // ES EL MEDIADOR EL QUE SE ENCARGA
										// DE REALIZAR LA COMUNICACIÓN
										// DE SALIDA
	}

	@Override
	public void mensajeRecibido(String mensaje) {
		
		System.out.println(nombre + " ha recibido el mensaje: '" + mensaje + "'.");
	}
}
