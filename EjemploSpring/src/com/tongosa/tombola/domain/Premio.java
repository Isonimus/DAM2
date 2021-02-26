package com.tongosa.tombola.domain;

public class Premio {

	private String nombre;
	private float valor;
	
	
	public Premio() {
		super();
		this.nombre = "";
		this.valor = 0f;
	}

	public Premio(String nombre, float valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}


	@Override
	public String toString() {
		return "Premio [nombre=" + nombre + ", valor=" + valor + "]";
	}
	
	
	
	
}
