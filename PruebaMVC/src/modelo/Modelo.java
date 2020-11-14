package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import servicio.ServicioBBDD;

public class Modelo {
	
	private Connection conexion;
	private Statement sentencia;
	private ResultSet resultado;
	
	public Modelo() {
		
		super();
		
		this.conexion = ServicioBBDD.obtenerServicio().obtenerConexion();
		
		try {
			
			sentencia = conexion.createStatement();
			
			Autor.setConexionBDD(sentencia, resultado);
			Editorial.setConexionBDD(sentencia, resultado);
			Categoria.setConexionBDD(sentencia, resultado);
			Libro.setConexionBDD(sentencia, resultado);
			
		} catch (SQLException e) {
			
			System.out.println("Error en la conexión.");
			e.printStackTrace();
		}
		
	}
	
	// AUTOR
	public Vector<Autor> obtenerAutores() throws SQLException {
		
		return Autor.listar();
	}
	
	public String insertarAutor(String autor) {
		
		return Autor.insertar(autor);
	}
	
	public String actualizarAutor(int id, String nombre){
		
		return Autor.actualizar(id, nombre);
	}
	
	public String eliminarAutor(int id) {
		
		return Autor.eliminar(id);
	}
	
	//EDITORIAL
	public Vector<Editorial> obtenerEditoriales() throws SQLException {
		
		return Editorial.listar();
	}
	
	public String insertarEditorial(String editorial) {
		
		return Editorial.insertar(editorial);
	}
	
	public String actualizarEditorial(int id, String nombre){
		
		return Editorial.actualizar(id, nombre);
	}
	
	public String eliminarEditorial(int id) {
		
		return Editorial.eliminar(id);
	}
	
	//CATEGORÍA
	public Vector<Categoria> obtenerCategorias() throws SQLException {
		
		return Categoria.listar();
	}
	
	public String insertarCategoria(String categoria) {
		
		return Categoria.insertar(categoria);
	}
	
	public String actualizarCategoria(int id, String nombre){
		
		return Categoria.actualizar(id, nombre);
	}
	
	public String eliminarCategoria(int id) {
		
		return Categoria.eliminar(id);
	}
	
	//LIBRO
	public Vector<Libro> obtenerLibros() throws SQLException {
		
		return Libro.listar();
	}
	
	public String eliminarLibro(String isbn) {
		
		return Libro.eliminar(isbn);
	}
	
	public void terminar() {
		
		if(resultado != null) {
			
			try {
				
				resultado.close();
				System.out.println("Cerrando ResultSet...");
				
			} catch (SQLException e) {
				
				System.out.println("No ha podido cerrarse el ResultSet.");
				e.printStackTrace();
			}
		}
		
		if(sentencia != null) {
			
			try {
				
				sentencia.close();
				System.out.println("Cerrando Statement...");
				
			} catch (SQLException e) {
				
				System.out.println("No ha podido cerrarse el Statement.");
				e.printStackTrace();
			}
		}
		
		if(conexion != null) {
			
			try {
				
				conexion.close();
				System.out.println("Cerrando Conexión...");
				
			} catch (SQLException e) {
				
				System.out.println("No ha podido cerrarse la conexión.");
				e.printStackTrace();
			}
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conn) {
		this.conexion = conn;
	}

	public Statement getSentencia() {
		return sentencia;
	}

	public void setSentencia(Statement sentencia) {
		this.sentencia = sentencia;
	}

	public ResultSet getResultado() {
		return resultado;
	}

	public void setResultado(ResultSet resultado) {
		this.resultado = resultado;
	}
}
