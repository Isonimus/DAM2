package strategy;

public class ArcoNormal extends AtaqueNormal{
	
	private int dmg = 15;
	
	public ArcoNormal() {}
	
	public void elegirObjetivo() {
		System.out.println("Eligiendo objetivo...");
	}
	
	public void apuntar() {
		System.out.println("Afinando la puntería...");
	}
	
	public void disparar() {
		System.out.println("Tensando el arco...");
		System.out.println("¡Disparo!");
	}
	
	public void terminarTurno() {
		System.out.println("Has alcanzado al objetivo.");
		System.out.println("El objetivo recibe " + dmg + "puntos de daño.");
		System.out.println("Turno terminado.");
	}
}
