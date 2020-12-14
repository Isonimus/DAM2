package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * CLASE DAO - MAPEA LA TABLA AUTOR.
 * CADA INSTANCIA DE ESTA CLASE ES UNA 
 * FILA EN LA BDD
 * 
 * @author Iker Laforga
 */

public class Autor extends DAO{

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
			
			setResultado(getSentencia().executeQuery(consulta)); 
			
		} catch (SQLException e) {
			
			System.out.println("AUTOR: Fallo al realizar la consulta.");
			e.printStackTrace();
		}
		
		return cargaResultSetToVector(getResultado());
	}
	
	//AMPLIACIÓN PARA LA GUI SWING
	//////////////////////////////
	public static ResultSet obtenerDatosMasMetadatosTabla() {
		
		try {
			
			String sqlQuery = "SELECT cod_autor as 'CODIGO', nombre as 'NOMBRE' FROM autor";
			resultado = Autor.sentencia.executeQuery(sqlQuery);
			
		} catch (SQLException e) {
			
			System.out.println("AUTOR: fallo al obtener datos + metadatos.");
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	//================//
	//    C.R.U.D
	//================//
	
	//CREATE
	public static String insertar(String autor) {
		
		int retorno;
		
		try{
			
			String sql = "SELECT max(`cod_autor`) FROM autor";
			resultado = sentencia.executeQuery(sql);
			
			int nuevaId = -1;
			
			while(resultado.next()) {
				
				nuevaId = (resultado.getInt(1) + 1);
			}
			
			String sql2 = "INSERT INTO autor (cod_autor, nombre) VALUES (" + nuevaId + ", '" + autor + "')";
			retorno = sentencia.executeUpdate(sql2);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			retorno = 0;
		}
		
		return (retorno > 0) ? "Autor " + autor + " añadido correctamente." : "Error al añadir el autor.";
	}
	
	//READ:
	//LEER TODOS
	public static Vector<Autor> listar() throws SQLException{
		
		return buscaResultadosConConsulta("Select * from autor");
	}
	
	//LEER POR ID
	public static Vector<Autor> buscarPorId(int id) throws SQLException{
			
		return buscaResultadosConConsulta("Select cod_autor, nombre from autor where cod_autor = " + id);
	}
	
	//LEER POR NOMBRE
	public static Vector<Autor> buscarPorNombre(String nombre) throws SQLException{
				
		return buscaResultadosConConsulta("Select cod_autor, nombre from autor where nombre LIKE '" + nombre + "'");
	}
	
	//UPDATE
	//SÓLO CAMBIA EL NOMBRE (LA ID NO VA A CAMBIAR)
	public static String actualizar(int id, String nombre) {
		
		int retorno;
		
		try{
			
			String sql = "UPDATE autor SET nombre = '" + nombre + "' WHERE cod_autor = " + id;
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Modificación de autor correcta." : "Error al actualizar el autor.";
	}
	
	//DELETE
	public static String eliminar(int id) {
		
		int retorno;
		
		try{
			
			String sql = "DELETE FROM autor WHERE cod_autor = " + id;
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Se ha borrado el autor." : "Error al eliminar el autor.";
	}
}
