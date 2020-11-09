package filosofos;

public class LaTabernaDePlaton {
	
	LaTabernaDePlaton(){
		super();
	}
	
	public static void main(String[] args) {
		
		Mesa mesa = new Mesa();
		Filosofo f1 = new Filosofo(0, mesa);
		Filosofo f2 = new Filosofo(1, mesa);
		Filosofo f3 = new Filosofo(2, mesa);
		Filosofo f4 = new Filosofo(3, mesa);
		Filosofo f5 = new Filosofo(4, mesa);
		
		f1.start();
		f2.start();
		f3.start();
		f4.start();
		f5.start();
	}
}
