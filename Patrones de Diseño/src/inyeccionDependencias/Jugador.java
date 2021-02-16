package inyeccionDependencias;

/**
 * INYECCIÓN DE DEPENDENCIAS
 * 
 * 1 - LAS CLASES DEBEN CONTENER CONCEPTOS ABSTRACTOS
 * EN VEZ DE IMPLEMENTACIONES CONCRETAS PARA EVITAR ACOPLAMIENTO
 * 
 * 2 - LOS OBJETOS QUE REQUIEREN LAS CLASES DEBEN SER APORTADOS
 * (INYECTADOS) POR UNA TERCERA PARTE, EN VEZ DE SER CREADOS
 * POR LA CLASE QUE LOS REQUIERE (DE NUEVO, AYUDA A DESACOPLAR EL 
 * CÓDIGO DE UNA IMPLEMENTACIÓN CONCRETA)
 * 
 * @author Iker Laforga
 *
 */
public class Jugador {
	
	Jugable dispositivo;
	
	public Jugador(Jugable dispositivo) {
		
		this.dispositivo = dispositivo;
	}
	
	public void jugar() {
		dispositivo.jugar();
	}

}
