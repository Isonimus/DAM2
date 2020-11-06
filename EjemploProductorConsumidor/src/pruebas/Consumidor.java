package pruebas;

/**
 * @(#)Consumidor.java
 * 
 *                     El consumidor no se preocupa si el dato ya está
 *                     disponible en el contenedor o no.
 **/

public class Consumidor extends Thread {
	private String nombre;
	private Contenedor contenedor;

	public Consumidor(String nombre, Contenedor c) {
		super(nombre);
		this.contenedor = c;
	}

	public void run() {
		int value = 0;
		for (int i = 0; i < ProductorConsumidorTest.NUM_ITERACIONES; i++) {
			value = contenedor.get(); // Recupera el dato para mostrarlo en consola.
			// System.out.println("Consumidor. get: " + value);
		}
	}
}