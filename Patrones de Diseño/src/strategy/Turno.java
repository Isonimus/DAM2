package strategy;
/**
 * EL CONTEXTO DE LA ESTRATEGIA
 * @author Iker Laforga
 *
 */
public class Turno {
	
	private Estrategia estrategia;
	
	public Turno(Estrategia estrategia) {
		this.estrategia = estrategia;
	}
	
	public void ejecutar() {
		this.estrategia.atacar();
	}	
}