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
		
		this.conexion = ServicioBBDD.obtenerServicio(ServicioBBDD.MYSQL).obtenerConexion();
		
		try {
			
			sentencia = conexion.createStatement();
			
			Autor.setConexionBDD(sentencia, resultado);
			Editorial.setConexionBDD(sentencia, resultado);
			Categoria.setConexionBDD(sentencia, resultado);
			Libro.setConexionBDD(sentencia, resultado);
			
		} catch (SQLException e) {
			
			System.out.println("Error: Fallo al crear recursos de BDD.");
			e.printStackTrace();
		}
	}
	
	// AUTOR
	public Vector<Autor> obtenerAutores() throws SQLException {
		
		return Autor.listar();
	}
	
	public Vector<Autor> obtenerAutor() throws SQLException {
		
		return Autor.listar(); //TODO
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
	
	//AMPLIACIÓN PARA LA GUI SWING
	public ResultSet obtenerDatosMasMetadatosAutor() {
		
		return Autor.obtenerDatosMasMetadatosTabla();
	}
	
	//EDITORIAL
	public Vector<Editorial> obtenerEditoriales() throws SQLException {
		
		return Editorial.listar();
	}
	
	public Vector<Editorial> obtenerEditorial() throws SQLException {
		
		return Editorial.listar(); //TODO
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
	
	//AMPLIACIÓN PARA LA GUI SWING
	public ResultSet obtenerDatosMasMetadatosEditorial() {
		
		return Editorial.obtenerDatosMasMetadatosTabla();
	}
	
	//CATEGORÍA
	public Vector<Categoria> obtenerCategorias() throws SQLException {
		
		return Categoria.listar();
	}
	
	public Vector<Categoria> obtenerCategoria() throws SQLException {
		
		return Categoria.listar(); //TODO
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
	
	//AMPLIACIÓN PARA LA GUI SWING
	public ResultSet obtenerDatosMasMetadatosCategoria() {
		
		return Categoria.obtenerDatosMasMetadatosTabla();
	}
		
	//LIBRO
	public Vector<Libro> obtenerLibros() throws SQLException {
		
		return Libro.listar();
	}
	
	public Vector<Libro> obtenerLibro(int isbn) throws SQLException {
		
		return Libro.listar(); //TODO
	}
	
	public Vector<Autor> obtenerAutoresLibro(int isbn) throws SQLException{
		
		return Libro.listarAutores(isbn); 
	}
	
	public String dropAutorLibro(int idAutor, int isbn) {
		
		return Libro.dropAutor(idAutor, isbn);
	}
	
	public String insertarLibro(Libro libro) {
		
		return Libro.insertar(libro);
	}
	
	public String actualizarLibro(int isbn, String propiedad, String valor) {
		
		return Libro.actualizar(isbn, propiedad, valor);
	}
	
	//GUI SWING
	public String actualizarLibro(int isbn, Libro libro) {
		
		return Libro.actualizar(isbn, libro);
	}
	
	public String addAutorLibro(int idAutor, int isbn) {
		
		return Libro.addAutor(idAutor, isbn);
	}
	
	public String eliminarLibro(int isbn) {
		
		return Libro.eliminar(isbn);
	}
	
	//AMPLIACIÓN PARA LA GUI SWING
	public ResultSet obtenerDatosMasMetadatosLibro() {
		
		return Libro.obtenerDatosMasMetadatosTabla();
	}
	
	public ResultSet obtenerDatosMasMetadatosAutoresLibro(int isbn) {
		
		return Libro.obtenerDatosMasMetadatosAutoresLibro(isbn);
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
