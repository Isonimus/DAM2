<<<<<<< HEAD
package strategy;

/**
 * ABSTRACCIÓN DE LA ESTRATEGIA II
 * @author Iker Laforga
 *
 */

public abstract class AtaqueHielo implements Estrategia{
	
	public void atacar() {
		
		elegirObjetivo();
		apuntar();
		crearHielo();
		disparar();
		congelar();
		terminarTurno();
	}
	
	public void elegirObjetivo() {
		
	}
	
	public void apuntar() {
		
	}
	
	public void crearHielo() {
		
	}
	
	public void disparar() {
		
	}
	
	public void congelar() {
		
	}
	
	public void terminarTurno() {
		
	}
}
=======
package strategy;

public abstract class AtaqueHielo implements Estrategia{
	
	public void atacar() {
		
		elegirObjetivo();
		apuntar();
		crearHielo();
		disparar();
		congelar();
		terminarTurno();
	}
	
	public void elegirObjetivo() {
		
	}
	
	public void apuntar() {
		
	}
	
	public void crearHielo() {
		
	}
	
	public void disparar() {
		
	}
	
	public void congelar() {
		
	}
	
	public void terminarTurno() {
		
	}
}
>>>>>>> refs/remotes/origin/master
