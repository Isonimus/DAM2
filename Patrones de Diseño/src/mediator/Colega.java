package mediator;

/**
 * CLASE COLEGA
 * OBLIGA A IMPLEMENTAR LOS MÉTODOS 
 * PARA ENVIAR Y RECIBIR MENSAJES,
 * Y TIENE CONOCIMIENTO DEL MEDIADOR
 * 
 * @author Iker Laforga
 *
 */
public abstract class Colega {
	
	public Mediador mediador;
	public String nombre;
	
	public Colega(String nombre, Mediador mediador) {
		
		this.nombre = nombre;
		this.mediador = mediador;
	}
	
	public abstract void enviar(String mensaje);
	public abstract void mensajeRecibido(String mensaje);
}
