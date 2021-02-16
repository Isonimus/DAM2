package builder;

/**
 * ESTE ES UN BUILDER CONCRETO. IMPLEMENTA LOS 
 * M�TODOS ABSTRACTOS DEL ABSTRACT BUILDER
 * A SU FORMA CONCRETA, PARA PRODUCIR PIZZAS
 * DE UN �NICO TIPO (Margarita)
 * 
 * @author Iker Laforga
 *
 */

public class MargaritaPizzaBuilder extends PizzaBuilder{

	@Override
	public void buildMasa() {
		pizza.setMasa("Fina");
	}

	@Override
	public void buildSalsa() {
		pizza.setSalsa("Tomate y or�gano");
	}

	@Override
	public void buildRelleno() {
		pizza.setRelleno("Prosciutto y albahaca");
	}
}
