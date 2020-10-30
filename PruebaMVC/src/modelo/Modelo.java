package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Modelo {
	
	private Connection conexion;
	private Statement sentencia;
	private ResultSet resultado;
	
	public Modelo() {
		
		super();
		init();
	}
	
	private void init() {
		
		System.out.println("Iniclaizando Modelo...");
	}
	
	public void crearConexion() {
		
	}
	
	public void cerrarConexion() {
		
		
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
