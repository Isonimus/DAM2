package strategy;

public class ArcoHielo extends AtaqueHielo{
	
	private int dmg = 20;
	
	public ArcoHielo() {}
	
	public void elegirObjetivo() {
		
		System.out.println("Eligiendo objetivo...");
		System.out.println("Empieza a levantarse una fría brisa.");
	}
	
	public void apuntar() {
		System.out.println("Afinando la puntería...");
		System.out.println("El viento se arremolina a tu alrededor.");
	}
	
	public void crearHielo() {
		System.out.println("Se forman pequeños cristales de hielo en la punta de la flecha.");
	}
	
	public void disparar() {
		System.out.println("Tensando el arco...");
		System.out.println("La cuerda cruje bajo tus dedos.");
		System.out.println("¡Disparo!");
	}
	
	public void congelar() {
		System.out.println("La flecha se llena de escarcha a medida que avanza...");
	}
	
	public void terminarTurno() {
		System.out.println("Has alcanzado al objetivo.");
		System.out.println("El objetivo recibe " + dmg + " puntos de daño.");
		System.out.println("¡Ahora tu objetivo está congelado!");
		System.out.println("Turno terminado.");
	}
}
