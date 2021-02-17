package memento;

public class Juego {
	
	private String nombre;
	private int checkPoint;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombreJuego) {
		this.nombre = nombreJuego;
		
	}

	public int getCheckPoint() {
		return checkPoint;
	}

	public void setCheckPoint(int checkPoint) {
		this.checkPoint = checkPoint;
	}
	
	@Override
	public String toString() {
		return "Juego: " + getNombre() + " - CheckPoint: " + getCheckPoint() + ".";
	}
}
