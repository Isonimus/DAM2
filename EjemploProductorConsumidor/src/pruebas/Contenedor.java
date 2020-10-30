package pruebas;

/**
 * @(#)Contenedor.java
 * 
 *                     La sincronización que permite a Productor y Consumidor
 *                     operar correctamente, es decir, la que hace que
 *                     Consumidor espere hasta que haya un dato disponible, y
 *                     que productor no genere uno nuevo hasta que haya sido
 *                     consumido está en esta clase.
 * 
 *                     La sincronización se lleva a cabo pues usando los métodos
 *                     wait() y notify()/notifiAll().
 * 
 *                     Es importante el manejo de la bandera para que no se este
 *                     pidiendo y almacenando el tiempo simultaneamente, ya que
 *                     podría generar resultados inesperados.
 * 
 *                     Por medio de 'synchronized' adquiere un bloqueo en el
 *                     objeto que se ejecuta el método que impide que nadie más
 *                     inicie la ejecución en ese objeto de otro método que
 *                     también esté declarado como 'synchronized'. Cuando
 *                     comienza el método put() se bloquea el objeto de tal
 *                     forma que si alguien intenta invocar el método get() o
 *                     put() (ambos son synchronized) quedará en espera hasta
 *                     que el bloqueo se libere (cuando termine la ejecución del
 *                     método). Este mecanismo garantiza que los objetos
 *                     compartidos mantienen la consistencia.
 * 
 *                     - Es responsabilidad del programador pensar y gestionar
 *                     los bloqueos. - Los métodos synchronized son más costosos
 *                     en el sentido de que adquirir y liberarlos bloqueos
 *                     consume tiempo (este es el motivo por el que no están
 *                     sincronizados por defecto todos los métodos).
 **/

public class Contenedor {
	private int dato; // Contiene el valor, que se almacena con put() y se devuelve con get()
	private boolean hayDato = false; // Flag (bandera) que indica si el objeto tiene el dato o no.

	// En este método put() antes de almacenar el valor en la variable dato debe
	// asegurarse que el valor anterior ha sido consumido.
	public synchronized void put(int valor) {
		while (hayDato == true) { // Si todavía hay valor se suspende la ejecución del hilo mediante el método
									// wait().
			try {
				/**
				 * Se suspende el hilo indefinidamente hasta que se le envìe una 'señal' con el
				 * método notify() o notifyAll() Cuando la señalización mediante notify() lo
				 * produce el método get(), el método continua, asume que el dato ya fue
				 * consumido y posteriormente...
				 **/
				wait();
			} catch (InterruptedException e) {
			}
		}
		// Almacena el valor en dato y notifica que hay un dato disponible.
		dato = valor;
		hayDato = true;
		notifyAll();
	}

	// En este método get() chequea si hay un dato disponible (no lo hay si hayDato
	// es falso)
	public synchronized int get() {
		while (hayDato == false) {
			try {
				/**
				 * Si no hay dato espera hasta que le avisen. Una vez notificado desde el método
				 * put(), posteriormente...
				 **/
				wait();
			} catch (InterruptedException e) {
			}
		}
		// Cambia el valor de la bandera y devuelve el valor. Pero antes, notifica que
		// el dato ya ha sido consumido y que se puede almacenar otro.
		hayDato = false;
		notifyAll();
		return dato;
	}
}