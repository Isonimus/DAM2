package inyeccionDependencias;

public abstract class FactoryDispositivos {
	// OBJETO 'CONTENEDOR'
	// PREPARAMOS NUESTRO ENUM CON LOS TIPOS DE DISPOSITIVO
	// QUE SE PUEDE PEDIR A LA FACTORY
	public enum tiposDispositivos {CONSOLA, PC;}	
	public FactoryDispositivos() {}
	
	public static Jugable getDispositivo(tiposDispositivos tipo) {
		
		switch(tipo) {
			
			case CONSOLA:
				return new Consola("Ps5");
				
			default:
				return new PC("Pepino");
		}
	}
}
