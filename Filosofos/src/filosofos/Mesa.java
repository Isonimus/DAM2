package filosofos;

public class Mesa {
	
	private boolean[] cubiertos;
	//private boolean[] cubiertos = {true, true, true, true, true};
	
	public Mesa() {
		
		cubiertos = new boolean[5];
		cubiertos[0] = true;
		cubiertos[1] = true;
		cubiertos[2] = true;
		cubiertos[3] = true;
		cubiertos[4] = true;
	}
	
	public synchronized void getCubiertos(int posicion, String nombre) throws ExcepcionCubiertosNoDisponibles {
		
		int izquierdo = posicion;
		int derecho;
		
		if(posicion < 4) {
			
			derecho = posicion +1;
			
		}else {
			
			derecho = 0;
		}
		
		if(cubiertos[izquierdo] && cubiertos[derecho]){
			
			cubiertos[izquierdo] = false;
			cubiertos[derecho] = false;
			System.out.println(nombre + " está comiendo.");
			notify();
	
		}else {
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			throw new ExcepcionCubiertosNoDisponibles("Cubiertos no disponibles");
		}
	}
	
	public synchronized void devolverCubiertos(int posicion, String nombre) {
		
		int izquierdo = posicion;
		int derecho;
		
		if(posicion < 4) {
			
			derecho = posicion +1;
			
		}else {
			
			derecho = 0;
		}
		
		cubiertos[izquierdo] = true;
		cubiertos[derecho] = true;
		System.out.println(nombre + " está fumando.");
		notify();
	}
}
