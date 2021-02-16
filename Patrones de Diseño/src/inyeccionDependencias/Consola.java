package inyeccionDependencias;

public class Consola implements Jugable{
	
	private String modelo;
	
	public Consola(String modelo) {
		this.setModelo(modelo);
	}
	
	public void jugar() {
		
		System.out.println("El jugador está usando la consola modelo " + getModelo() + ".");
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}