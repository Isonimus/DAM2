package filosofos;

public class Filosofo extends Thread{
	
	public int posicion;
	private Mesa mesa;
	
	public Filosofo(int posicion, Mesa mesa) {
		
		super();
		this.posicion = posicion;
		this.mesa = mesa;
	}
	
	private void comer() {
			
		try{
			
			mesa.getCubiertos(posicion);
			mesa.devolverCubiertos(posicion);
			System.out.println("El Filósofo " + posicion  + " está fumando.");
			sleep((int) ((Math.random() * 4000) + 1000));
			
		}catch(ExcepcionCubiertosNoDisponibles e) {
		
				try {
					
					wait();
					
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			comer();
		}
	}
}
