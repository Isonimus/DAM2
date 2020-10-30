package pruebas;

/**
 * @(#)Productor.java
 * 
 *                    El productor no se preocupa si el dato ya ha sido
 *                    consumido o no. Simplemente lo coloca en el contenedor.
 **/

public class Productor extends Thread {
	private Contenedor contenedor; // Almacena los datos que se van produciendo.

	public Productor(Contenedor c) {
		this.contenedor = c;
		// setPriority(MAX_PRIORITY);
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			contenedor.put(i); // Se almacena el dato en el contenedor.
			System.out.println("Productor.put: " + i);
			try {
				sleep((int) (Math.random() * 100)); // Espera una cantidad de tiempo aleatoria (hasta 100 milisegundos)
			} catch (InterruptedException e) {
			}
		}
	}
}