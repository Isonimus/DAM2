package mediator;

/**
 * LA INTERFAZ MEDIADOR SE ENCARGA DE
 * ENVIAR MENSAJES ENTRE LOS OBJETOS
 * 
 * @author Iker Laforga
 *
 */
public interface Mediador {
	
	public void enviar(String mensaje, Colega colega);
}
