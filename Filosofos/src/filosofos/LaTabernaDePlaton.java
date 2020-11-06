package filosofos;

public class LaTabernaDePlaton {

	public static void main(String[] args) {
		
		Mesa mesa = new Mesa();
		Filosofo f1 = new Filosofo(0, mesa);
		Filosofo f2 = new Filosofo(1, mesa);
		Filosofo f3 = new Filosofo(2, mesa);
		Filosofo f4 = new Filosofo(3, mesa);
		Filosofo f5 = new Filosofo(4, mesa);
		
		f1.run();
		f2.run();
		f3.run();
		f4.run();
		f5.run();
	}
}
