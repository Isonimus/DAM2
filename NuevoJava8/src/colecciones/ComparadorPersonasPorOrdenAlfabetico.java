package colecciones;

import java.util.Comparator;

import conduccion.Persona;

public class ComparadorPersonasPorOrdenAlfabetico implements Comparator<Persona> {

	@Override
	public int compare(Persona persona1, Persona persona2) {
		if (!persona1.getApellido().equals(persona2.getApellido())) {
			return persona1.getApellido().compareTo(persona2.getApellido());
		} else {
			return persona1.getNombre().compareTo(persona2.getNombre());
		}
	}
	
	

}
