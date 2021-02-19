package mediator;
/**
 * IMPLEMENTACIÓN CONCRETA DEL MEDIADOR.
 * CONTIENE LA LÓGICA DE LA COMUNICACIÓN ENTRE
 * COLEGAS, Y SU RESPONSABILIDAD ES HACER LLEGAR
 * LOS MENSAJES AL DESTINATARIO ADECUADO.
 * 
 * @author Iker Laforga
 *
 */
public class MediadorConcreto implements Mediador{
	
	private ColegaConcreto col1; //LOS PEERS
	private ColegaConcreto col2;
	
	@Override
	public void enviar(String mensaje, Colega colega) {
		
		if(colega == col1) {
			
			col2.mensajeRecibido(mensaje);
			
		}else if(colega == col2) {
			
			col1.mensajeRecibido(mensaje);
		}
	}
	
	public void setColega1(ColegaConcreto col1) {
		this.col1 = col1;
	}

	public void setColega2(ColegaConcreto col2) {
		this.col2 = col2;
	}
}
