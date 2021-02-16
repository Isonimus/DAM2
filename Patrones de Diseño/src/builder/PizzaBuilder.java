package builder;

/**
 * ESTE ES EL CONSTRUCTOR ABSTRACTO
 * TIENE COMO ATRIBUTO UN OBJETO DE 
 * LA CLASE PRODUCTO. CONTENE M�TODOS
 * ABSTRACTOS PARA LOS CONCRETE BUILDERS,
 * QUE LOS USAR�N PARA ASIGNAR AL PRODUCTO
 * SUS VALORES PREDETERMINADOS.
 * 
 * @author Iker Laforga
 *
 */

public abstract class PizzaBuilder {
	
	protected Pizza pizza; // EL PRODUCTO
	
	public Pizza getPizza() {
		return pizza;
	}
	
	public void crearNuevaPizza() {
		pizza = new Pizza();
	}
	
	// ABSTRACTOS, PARA LOS CONCRETE BUILDERS
	public abstract void buildMasa();
	public abstract void buildSalsa();
	public abstract void buildRelleno();
}
