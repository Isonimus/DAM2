package controlador;

import java.sql.ResultSet;

import modelo.Modelo;

public class Controlador {

	private Modelo modelo;
	private ResultSet resultado;
	
	public Controlador(Modelo modelo) {
		
		super();
		this.modelo = modelo;
	}
	
	public ResultSet obtenerAutores () {
		
		resultado = modelo.obtenerAutores();
		return resultado;
	}
	
	public void terminar() {
		
		modelo.terminar();
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
}
