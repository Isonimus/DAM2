package mediator;

public class Discord {
	
	public static void main(String[] args) {
		
		MediadorConcreto mediador = new MediadorConcreto();
		
		ColegaConcreto joseLuis = new ColegaConcreto("José Luis", mediador);
		ColegaConcreto iker = new ColegaConcreto("Iker", mediador);
		
		mediador.setColega1(joseLuis);
		mediador.setColega2(iker);
		
		joseLuis.enviar("Estás re suspenso, amigo");
		iker.enviar("Ohvaya :(");
	}
}
