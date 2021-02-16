package builder;
/**
 * ESTE ES UN BUILDER CONCRETO. IMPLEMENTA LOS 
 * MÉTODOS ABSTRACTOS DEL ABSTRACT BUILDER
 * A SU FORMA CONCRETA, PARA PRODUCIR PIZZAS
 * DE UN ÚNICO TIPO (BARBACOA)
 * 
 * @author Iker Laforga
 *
 */
public class BarbacoaPizzaBuilder extends PizzaBuilder{
	
	// IMPLEMENTACIÓN DE TODOS 
	// LOS MÉTODOS ABSTRACTOS
	@Override
	public void buildMasa() {
		pizza.setMasa("Gruesa");
	}

	@Override
	public void buildSalsa() {
		pizza.setSalsa("Barbacoa");
	}

	@Override
	public void buildRelleno() {
		pizza.setRelleno("Carne y pollo marinado");
	}
}
