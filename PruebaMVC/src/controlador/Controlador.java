package controlador;

import java.sql.SQLException;
import java.util.Vector;

import modelo.Autor;
import modelo.Categoria;
import modelo.Editorial;
import modelo.Libro;
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
	
	public String insertarAutor(String autor) {
		
		return modelo.insertarAutor(autor);
	}
	
	public String eliminarAutor(int id) {
		
		return modelo.eliminarAutor(id);
	}
	
	public Vector<Editorial> obtenerEditoriales () throws SQLException {
		
		return  modelo.obtenerEditoriales();
	}
	
	public Vector<Categoria> obtenerCategorias () throws SQLException {
		
		return  modelo.obtenerCategorias();
	}
	
	public Vector<Libro> obtenerLibros () throws SQLException {
		
		return  modelo.obtenerLibros();
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
