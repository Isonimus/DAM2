package memento;
/**
 * SE ENCARGAR DE REALIZAR EL GUARDADO DEL
 * ESTADO DEL OBJETO EN CUESTIÓN (EN ESTE CASO,
 * DE TIPO 'JUEGO') Y DE RESTAURAR EL ESTADO A TRAVÉS
 * DE UN OBJETO 'MEMENTO'
 * 
 * @author Iker Laforga
 *
 */
public class Originator {
	
	private Juego estado;
	
	public Originator() {}

	public void setEstado(Juego juego) {
		this.estado = juego;
	}
	
	public Juego getEstado() {
		return estado;
	}

	public Memento guardar() {
		return new Memento(estado);
	}

	public void restaurar(Memento memento) {
		this.estado = memento.getEstado();
		
	}
}
