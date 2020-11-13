package filosofos;

public class LaTabernaDePlaton {
	
	LaTabernaDePlaton(){
		super();
	}
	
	public static void main(String[] args) {
		
		Mesa mesa = new Mesa();
		Filosofo f1 = new Filosofo(0, "Descartes", mesa);
		Filosofo f2 = new Filosofo(1, "Nietzsche", mesa);
		Filosofo f3 = new Filosofo(2, "Kant", mesa);
		Filosofo f4 = new Filosofo(3, "Hegel", mesa);
		Filosofo f5 = new Filosofo(4, "Sócrates", mesa);
		
		f1.start();
		f2.start();
		f3.start();
		f4.start();
		f5.start();
	}
}
