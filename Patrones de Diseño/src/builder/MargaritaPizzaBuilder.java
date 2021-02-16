package builder;

/**
 * ESTE ES UN BUILDER CONCRETO. IMPLEMENTA LOS 
 * MÉTODOS ABSTRACTOS DEL ABSTRACT BUILDER
 * A SU FORMA CONCRETA, PARA PRODUCIR PIZZAS
 * DE UN ÚNICO TIPO (Margarita)
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
		pizza.setSalsa("Tomate y orégano");
	}

	@Override
	public void buildRelleno() {
		pizza.setRelleno("Prosciutto y albahaca");
	}
}
