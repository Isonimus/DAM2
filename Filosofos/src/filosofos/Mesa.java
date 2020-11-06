package filosofos;

import java.util.Set;
import java.util.TreeSet;

public class Mesa {
	
	//MODELIZACIÓN DE LOS CUBIERTOS:
	private Set<Integer> cubiertos = new TreeSet<Integer>();
	
	public Mesa() {
		
		cubiertos.add(0);
		cubiertos.add(1);
		cubiertos.add(2);
		cubiertos.add(3);
		cubiertos.add(4);
	}
	
	public synchronized void getCubiertos(int posicion) throws ExcepcionCubiertosNoDisponibles {
		
		int izquierdo = posicion;
		int derecho;
		
		if(posicion < 4) {
			
			derecho = posicion +1;
			
		}else {
			
			derecho = 0;
		}
		
		if(cubiertos.contains(izquierdo) && cubiertos.contains(derecho)){
			
			cubiertos.remove(izquierdo);
			cubiertos.remove(derecho);
			System.out.println("El Filósofo " + posicion  + " está comiendo.");
			notify();
	
		}else {
			
			throw new ExcepcionCubiertosNoDisponibles("Cubiertos no disponibles");
		}
	}
	
	public synchronized void devolverCubiertos(int posicion) {
		
		int izquierdo = posicion;
		int derecho;
		
		if(posicion < 4) {
			
			derecho = posicion +1;
			
		}else {
			
			derecho = 0;
		}
		
		cubiertos.add(izquierdo);
		cubiertos.add(derecho);
		notify();
	}
}
