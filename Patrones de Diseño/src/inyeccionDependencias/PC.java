package inyeccionDependencias;

public class PC implements Jugable{
	private String modelo;
	
	public PC(String modelo) {
		this.setModelo(modelo);
	}
	
	public void jugar() {
		
		System.out.println("El jugador est� usando el PC modelo " + getModelo() + ".");
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}
