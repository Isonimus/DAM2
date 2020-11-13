package filosofos;

public class Filosofo extends Thread{
	
	public int posicion;
	private Mesa mesa;
	
	public Filosofo(int posicion, String nombre, Mesa mesa) {
		
		super();
		setName(nombre);
		this.posicion = posicion;
		this.mesa = mesa;
	}
	
	private void comer() {
			
		try{
			
			mesa.getCubiertos(posicion, getName());
			sleep((int) ((Math.random() * 4000) + 1000)); //TIEMPO COMIENDO
			mesa.devolverCubiertos(posicion, getName());
			sleep((int) ((Math.random() * 4000) + 1000)); //TIEMPO FUMANDO
			
		}catch(ExcepcionCubiertosNoDisponibles e) {
		
			System.out.println(getName() + " no tiene cubiertos :(");
			
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
