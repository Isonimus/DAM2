package memento;

/**
 * CLASE 'CÁPSULA' PARA GUARDAR EL ESTADO
 * DE LA INSTANCIA DE TIPO 'JUEGO', Y PODER 
 * RECUPERAR ESE ESTADO CUANDO SEA NECESARIO
 * 
 * @author Iker Laforga
 *
 */

public class Memento {
	
	private Juego estado;
	
	public Memento(Juego estado) {
		this.estado = estado;
	}

	public Juego getEstado() {
		return estado;
	}
}
