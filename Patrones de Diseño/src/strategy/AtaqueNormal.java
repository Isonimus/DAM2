package strategy;
/**
 * ABSTRACCIÓN DE LA ESTRATEGIA I
 * @author Iker Laforga
 *
 */
public abstract class AtaqueNormal implements Estrategia{
	
	public void atacar() {
		
		elegirObjetivo();
		apuntar();
		disparar();
		terminarTurno();
	}
	
	public void elegirObjetivo() {
		
	}
	
	public void apuntar() {
		
	}
	
	public void disparar() {
		
	}
	
	public void terminarTurno() {
		
	}
}