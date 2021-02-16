package builder;

//DEMO
public class ViernesNoche {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Trattoria trattoria = new Trattoria(); //EL DIRECTOR
		PizzaBuilder builderBarbacoa = new BarbacoaPizzaBuilder();
		PizzaBuilder builderMargarita = new MargaritaPizzaBuilder(); 
		
		trattoria.setPizzaBuilder(builderBarbacoa);
		trattoria.prepararPizza();
		
		Pizza pizza = trattoria.getPizza();
		
		System.out.println(pizza);
	}
}
