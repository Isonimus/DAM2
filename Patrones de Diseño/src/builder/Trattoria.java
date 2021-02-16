package builder;

/**
 * CLASE DIRECTOR
 * GENERALIZA TODO
 * @author multi
 *
 */

public class Trattoria {
	
	private PizzaBuilder pizzaBuilder;
	
	public void setPizzaBuilder(PizzaBuilder pb) {
		pizzaBuilder = pb;
	}
	
	public Pizza getPizza() {
		return pizzaBuilder.getPizza();
	}
	
	public void prepararPizza() {
		
		pizzaBuilder.crearNuevaPizza();
		pizzaBuilder.buildMasa();
		pizzaBuilder.buildSalsa();
		pizzaBuilder.buildRelleno();
	}
}
