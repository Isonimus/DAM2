package controlador;

import java.sql.SQLException;
import java.util.Vector;

import modelo.Autor;
import modelo.Modelo;

public class Controlador {

	private Modelo modelo;
	
	public Controlador(Modelo modelo) {
		
		super();
		this.modelo = modelo;
	}
	
	public Vector<Autor> obtenerAutores () throws SQLException {
		
		return  modelo.obtenerAutores();
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
