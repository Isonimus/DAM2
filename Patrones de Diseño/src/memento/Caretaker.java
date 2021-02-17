package memento;

import java.util.ArrayList;

/**
 * SE ENCARGA DE GUARDAR TODOS LOS CHECKPOINTS
 * QUE SE GENEREN, Y DE DAR ACCESO A LOS QUE YA
 * ESTÁN GUARDADOS
 * 
 * @author Iker Laforga
 *
 */

public class Caretaker {
	
	private ArrayList<Memento> mementos = new ArrayList<>();
	
	public void addMemento(Memento guardar) {
		mementos.add(guardar);
	}

	public Memento getMemento(int i) {
		return mementos.get(i);
	}
}
