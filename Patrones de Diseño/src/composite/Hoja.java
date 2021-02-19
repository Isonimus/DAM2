package composite;

import java.util.Iterator;

/**
 * 
 * @author Iker Laforga
 *
 */

public class Hoja extends Componente{
	
	private String nombre;
	private int calorias;
	
	public Hoja(String nombre, int calorias) {
		
		this.nombre = nombre;
		this.calorias = calorias;
	}
	
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}

	@Override
	public int getCaloriasTotales() {
		// TODO Auto-generated method stub
		return calorias;
	}

	@Override
	public void anadir(Componente c) {
		
		System.out.println("No puedes añadir nada a un ingrediente básico.");
	}

	@Override
	public void eliminar(Componente c) {
		
		System.out.println("No puedes quitar nada a un ingrediente básico.");
	}

	@Override
	public Iterator<Componente> crearIterador() {
	return null;}
}