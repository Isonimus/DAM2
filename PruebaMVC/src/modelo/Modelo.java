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
	private String feedback;
	
	public Modelo() {
		
		super();
		
		this.conexion = ServicioBBDD.obtenerServicio().obtenerConexion();
		
		try {
			
			sentencia = conexion.createStatement();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		Autor.setConexionBDD(sentencia, resultado);
		Editorial.setConexionBDD(sentencia, resultado);
		Categoria.setConexionBDD(sentencia, resultado);
		Libro.setConexionBDD(sentencia, resultado);
	}
	
	public Vector<Autor> obtenerAutores() throws SQLException {
		
		return Autor.listar();
	}
	
	public String insertarAutor(String autor) {
		
		return Autor.Insertar(autor);
	}
	
	public String eliminarAutor(int id) {
		
		return Autor.Eliminar(id);
	}
	
	public Vector<Editorial> obtenerEditoriales() throws SQLException {
		
		return Editorial.listar();
	}
	
	public Vector<Categoria> obtenerCategorias() throws SQLException {
		
		return Categoria.listar();
	}
	
	public Vector<Libro> obtenerLibros() throws SQLException {
		
		return Libro.listar();
	}
	
	public String registrarNuevoAutor(String autor) {
		
		//THROWS...
		int retorno = 0;
		
		try {
			//RECOGER SIGUIENTE ID DESDE BDD
			//if(hayId){
			
			//insertar nuevo autor con esa ID;
			//retorno = sentencia.executeUpdate(arg0);
		//}
			
		}catch (Exception e) {
			
		}
		
		//feedback = dependiendo del valor de la operación.
		return "feedback";
	}
	
	public String borrarAutor(int codAutor) {
		
		int retorno = 0;
		
		return "feedback";
	}
	
	public ResultSet obtenerAutor(int codAutor) {
		
		return resultado;
	}
	
	public String editarAutor(int codAutor, String nombreAutor) {
		
		try {
			
			String sql = "";
			
		}catch(Exception e) {
			//SQLException e
			
		}
		
		return "feedback";
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
