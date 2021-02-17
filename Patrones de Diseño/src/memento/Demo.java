package memento;

public class Demo {
	
	public static void main(String[] args) {
		
		String nombreJuego = "Minecraft";
		
		Juego juego = new Juego();
		juego.setNombre(nombreJuego);
		juego.setCheckPoint(1); // NO SE GUARDA
		
		Caretaker caretaker = new Caretaker();
		Originator originator = new Originator();
		
		juego = new Juego(); // SE CREA NUEVA INSTANCIA PARA PODER MODIFICAR EL CHECKPOINT (APUNTARÍA A MISMA REF EN MEMORIA)
		juego.setNombre(nombreJuego);
		juego.setCheckPoint(2);
		originator.setEstado(juego); //SE GUARDA
		
		juego = new Juego();
		juego.setNombre(nombreJuego);
		juego.setCheckPoint(3);
		originator.setEstado(juego);
		
		caretaker.addMemento(originator.guardar()); // POSICIÓN 0 (CHECKPOINT 3)
													// RETORNA UN OBJETO DE TIPO MEMENTO
													// QUE SE GUARDA EN LA LISTA
		juego = new Juego();
		juego.setNombre(nombreJuego);
		juego.setCheckPoint(4);
		
		originator.setEstado(juego);
		caretaker.addMemento(originator.guardar()); // POSICIÓN 1 (CHECKPOINT 4)
		
		juego = new Juego();
		juego.setNombre(nombreJuego);
		juego.setCheckPoint(5); // NO SE GUARDA
		
		originator.setEstado(juego);
		originator.restaurar(caretaker.getMemento(1));
		
		juego = originator.getEstado();
		System.out.println(juego);
	}
}
