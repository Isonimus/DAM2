<<<<<<< HEAD
package strategy;

public class Combate {

	public static void main(String[] args) {
		
		Turno turno = new Turno(new DisparoNormal());
		turno.ejecutar();
	}
}
=======
package strategy;

public class Combate {

	public static void main(String[] args) {
		
		Turno turno = new Turno(new ArcoHielo());
		turno.ejecutar();
	}

}
>>>>>>> refs/remotes/origin/master
