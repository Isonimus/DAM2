package inyeccionDependencias;

public class ViernesNoche {

	public static void main(String[] args) {
		
		Jugable dispositivo = FactoryDispositivos.getDispositivo(FactoryDispositivos.tiposDispositivos.PC);
		Jugador AlexelCapo = new Jugador(dispositivo);
		AlexelCapo.jugar();
	}
}
