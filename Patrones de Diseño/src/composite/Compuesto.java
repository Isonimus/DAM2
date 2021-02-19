package composite;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Iker Laforga
 *
 */

public class Compuesto extends Componente{
	
	private String nombre;
	ArrayList<Componente> listaComponentes = new ArrayList<Componente>();
	
	public Compuesto (String nombre){
		this.nombre = nombre;
	}

	@Override
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public int getCaloriasTotales() {
		
		int total = 0;
		
		for(Iterator<Componente> i = crearIterador(); i.hasNext();) {
			
			total += ((Componente) i.next()).getCaloriasTotales();
		}
		return total;
	}

	@Override
	public void anadir(Componente c) {
		
		listaComponentes.add(c);
	}

	@Override
	public void eliminar(Componente c) {
		
		listaComponentes.remove(c);
	}

	@Override
	public Iterator<Componente> crearIterador() {
		
		return listaComponentes.iterator();
	}
}
