package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * CLASE DAO - MAPEA LA TABLA AUTOR.
 * CADA INSTANCIA DE ESTA CLASE ES UNA 
 * FILA EN LA BDD
 * 
 * @author Iker Laforga
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
	private static ResultSet resultado;
	private static Statement sentencia;
	
	public static void setConexionBDD(Statement sentencia, ResultSet resultado) {
		
		setResultado(resultado);
		setSentencia(sentencia);
	}
	
	//================//
	//    UTILIDAD
	//================//
	private static Vector<Autor> cargaResultSetToVector(ResultSet resultado) throws SQLException {
		
		Vector<Autor> autores = new Vector<Autor>();
		Autor autor;
		
		while(resultado.next()) {
			
			int idAutor = resultado.getInt(1);
			String nombreAutor = resultado.getString(2);
			autor = new Autor(nombreAutor);
			autor.setIdAutor(idAutor);
			autores.add(autor);
		}
		
		return autores;
	}
	
	private static Vector<Autor> buscaResultadosConConsulta(String consulta) throws SQLException{
		
		try {
			
			resultado = sentencia.executeQuery(consulta);
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: Fallo al realizar la consulta.");
			e.printStackTrace();
		}
		
		return cargaResultSetToVector(resultado);
	}
	
	public static ResultSet getResultado() {
		
		return resultado;
	}

	public static void setResultado(ResultSet resultado) {
		
		Autor.resultado = resultado;
	}

	public static Statement getSentencia() {
		
		return sentencia;
	}

	public static void setSentencia(Statement sentencia) {
		
		Autor.sentencia = sentencia;
	}
	
	//================//
	//    C.R.U.D
	//================//
	
	//LECTURAS
	//return (retorno > 0) ? "Si si" : "Si no";
	
	//CREATE
	public static String Insertar(String autor) {
		
		int retorno;
		
		try{
			
			String sql = "SELECT max(`cod_autor`) FROM autor";
			resultado = sentencia.executeQuery(sql);
			
			int nuevaId = -1;
			
			while(resultado.next()) {
				
				nuevaId = resultado.getInt(1);
			}
			
			sql = "INSERT INTO autor (cod_autor, nombre) VALUES (" + nuevaId + ", " + autor + ")";
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Autor " + autor + "añadido correctamente." : "Error al añadir el autor.";
	}
	
	//READ:
	//LEER TODOS
	public static Vector<Autor> listarAutores() throws SQLException{
		
		return buscaResultadosConConsulta("Select * from autor");
	}
	
	//LEER POR ID
	public static Vector<Autor> buscarAutorPorId(int idAutor) throws SQLException{
			
		return buscaResultadosConConsulta("Select cod_autor, nombre from autor where cod_autor = " + idAutor);
	}
	
	//LEER POR NOMBRE
	public static Vector<Autor> buscarAutorPorNombre(String nombreAutor) throws SQLException{
				
		return buscaResultadosConConsulta("Select cod_autor, nombre from autor where nombre LIKE '" + nombreAutor + "'");
	}
	
	//UPDATE
	//SÓLO CAMBIA EL NOMBRE (LA ID NO VA A CAMBIAR)
	
	//DELETE
	public static String Eliminar(int idAutor) {
		
		int retorno;
		
		try{
			
			String sql = "DELETE FROM autor WHERE cod_autor = " + idAutor;
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Autor " + idAutor + "eliminado correctamente." : "Error al eliminar el autor.";
	}
}
