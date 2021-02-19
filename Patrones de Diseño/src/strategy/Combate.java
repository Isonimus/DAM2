package strategy;

public class Combate {

	public static void main(String[] args) {
		
		Turno turno = new Turno(new DisparoNormal());
		turno.ejecutar();
	}
}