package filosofos;

public class TestHilos extends Thread{
	
	public String nombre;
	public boolean saludado = false;
	
	public TestHilos(String nombre){
		
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			System.out.println("Soy el hilo " + nombre + ".");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		
		TestHilos t1 = new TestHilos("" + 1);
		TestHilos t2 = new TestHilos("" + 2);
		TestHilos t3 = new TestHilos("" + 3);
		TestHilos t4 = new TestHilos("" + 4);
		TestHilos t5 = new TestHilos("" + 5);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

}
