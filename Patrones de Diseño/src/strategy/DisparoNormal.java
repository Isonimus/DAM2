package strategy;
/**
 * IMPLEMENTACI�N CONCRETA DE LA ESTRATEGIA I
 * @author Iker Laforga
 *
 */
public class DisparoNormal extends AtaqueNormal{
	
	private int dmg = 15;
	
	public DisparoNormal() {}
	
	public void elegirObjetivo() {
		System.out.println("Eligiendo objetivo...");
	}
	
	public void apuntar() {
		System.out.println("Afinando la punter�a...");
	}
	
	public void disparar() {
		System.out.println("Tensando el arco...");
		System.out.println("�Disparo!");
	}
	
	public void terminarTurno() {
		System.out.println("Has alcanzado al objetivo.");
		System.out.println("El objetivo recibe " + dmg + " puntos de da�o.");
		System.out.println("Turno terminado.");
	}
}
