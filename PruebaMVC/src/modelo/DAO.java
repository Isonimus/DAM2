package modelo;

import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DAO{
	
	protected static ResultSet resultado;
	protected static Statement sentencia;
	
	public DAO() {
		
		super();
	}
		
	public static void setConexionBDD(Statement sentencia, ResultSet resultado) {
			
		setResultado(resultado);
		setSentencia(sentencia);
	}

	public static ResultSet getResultado() {
		return DAO.resultado;
	}

	public static void setResultado(ResultSet resultado) {
		DAO.resultado = resultado;
	}

	public static Statement getSentencia() {
		return DAO.sentencia;
	}

	public static void setSentencia(Statement sentencia) {
		DAO.sentencia = sentencia;
	}
}
