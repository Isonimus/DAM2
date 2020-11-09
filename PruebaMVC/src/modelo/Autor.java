package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * CLASE DAO - MAPEA LA TABLA AUTOR
 * CADA INSTANCIA DE ESTA CLASE ES UNA FILA
 * EN LA BDD
 * @author Iker Laforga
 * GETACTION = CONTROLADOR
 */

public class Autor {

	//================//
	//  JAVA BEAN (|)
	//================//
	
	private int idAutor;
	private String nombreAutor;
	
	public Autor(String nombreAutor){
	
		setNombreAutor(nombreAutor);
	}
	
	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	//================//
	// ACCESO A DATOS
	//================//
	private ResultSet resultado;
	private Statement sentencia;
	
	public void setConexionBDD(Statement sentencia, ResultSet resultado) {
		
		setResultado(resultado);
		setSentencia(sentencia);
	}
	
	private static Vector<Autor> cargaResultSetToVector(ResultSet resultado) throws SQLException {
		
		Vector<Autor> autores = new Vector<Autor>();
		Autor autor;
		
		while(resultado.next()) {
			
		}
		
		return autores;
	}
	
	private static Vector<Autor> buscaResultadosConConsulta(String consulta){
		
		return null;
	}

	public ResultSet getResultado() {
		return resultado;
	}

	public void setResultado(ResultSet resultado) {
		this.resultado = resultado;
	}

	public Statement getSentencia() {
		return sentencia;
	}

	public void setSentencia(Statement sentencia) {
		this.sentencia = sentencia;
	}
	
	//================//
	//    C.R.U.D
	//================//
	
	//LECTURAS
	//public static
	//3 - leer todos, leer por ID, leer por nombre ('LIKE')
	//return (retorno > 0) ? "Si si" : "Si no";
}
