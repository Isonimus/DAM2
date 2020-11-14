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
	
	public String actualizarAutor(int id, String nombre) {
		
		return modelo.actualizarAutor(id, nombre);
	}
	
	public String eliminarAutor(int id) {
		
		return modelo.eliminarAutor(id);
	}
	
	public Vector<Editorial> obtenerEditoriales () throws SQLException {
		
		return  modelo.obtenerEditoriales();
	}
	
	public String insertarEditorial(String editorial) {
		
		return modelo.insertarEditorial(editorial);
	}
	
	public String actualizarEditorial(int id, String nombre) {
		
		return modelo.actualizarEditorial(id, nombre);
	}
	
	public String eliminarEditorial(int id) {
		
		return modelo.eliminarEditorial(id);
	}
	
	public Vector<Categoria> obtenerCategorias () throws SQLException {
		
		return  modelo.obtenerCategorias();
	}
	
	public String insertarCategoria(String categoria) {
		
		return modelo.insertarCategoria(categoria);
	}
	
	public String actualizarCategoria(int id, String nombre) {
		
		return modelo.actualizarCategoria(id, nombre);
	}
	
	public String eliminarCategoria(int id) {
		
		return modelo.eliminarCategoria(id);
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
