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
			sleep((int) ((Math.random() * 4000) + 1000));
			mesa.devolverCubiertos(posicion);
			sleep((int) ((Math.random() * 4000) + 1000));
			
		}catch(ExcepcionCubiertosNoDisponibles e) {
		
			System.out.println("El Filósofo " + posicion  + " no tiene cubiertos :(");
			
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
