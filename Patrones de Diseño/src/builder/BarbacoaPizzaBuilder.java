package builder;
/**
 * ESTE ES UN BUILDER CONCRETO. IMPLEMENTA LOS 
 * M�TODOS ABSTRACTOS DEL ABSTRACT BUILDER
 * A SU FORMA CONCRETA, PARA PRODUCIR PIZZAS
 * DE UN �NICO TIPO (BARBACOA)
 * 
 * @author Iker Laforga
 *
 */
public class BarbacoaPizzaBuilder extends PizzaBuilder{
	
	// IMPLEMENTACI�N DE TODOS 
	// LOS M�TODOS ABSTRACTOS
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
