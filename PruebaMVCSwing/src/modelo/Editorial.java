package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * CLASE DAO - MAPEA LA TABLA EDITORIAL.
 * CADA INSTANCIA DE ESTA CLASE ES UNA 
 * FILA EN LA BDD
 * 
 * @author Iker Laforga
 */

public class Editorial extends DAO{

	//================//
	//  JAVA BEAN (|)
	//================//
	
	private int idEditorial;
	private String nombreEditorial;
	
	public Editorial(String nombreEditorial){
	
		setNombreEditorial(nombreEditorial);
	}
	
	public int getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}

	public String getNombreEditorial() {
		return nombreEditorial;
	}

	public void setNombreEditorial(String nombreEditorial) {
		this.nombreEditorial = nombreEditorial;
	}
	
	public String toString() {
		return getNombreEditorial();
	}
	
	//================//
	//    UTILIDAD
	//================//
	//REFACTOR
	private static Vector<Editorial> cargaResultSetToVector(ResultSet resultado) throws SQLException {
		
		Vector<Editorial> editoriales = new Vector<Editorial>();
		Editorial editorial;
		
		while(resultado.next()) {
			
			int idEditorial = resultado.getInt(1);
			String nombreEditorial = resultado.getString(2);
			editorial = new Editorial(nombreEditorial);
			editorial.setIdEditorial(idEditorial);
			editoriales.add(editorial);
		}
		
		return editoriales;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//REFACTOR
	private static Vector<Editorial> buscaResultadosConConsulta(String consulta) throws SQLException{
		
		try {
			
			resultado = sentencia.executeQuery(consulta);
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: Fallo al realizar la consulta.");
			e.printStackTrace();
		}
		
		return cargaResultSetToVector(resultado);
	}
	
	//AMPLIACIÓN PARA LA GUI SWING
	//////////////////////////////
	public static ResultSet obtenerDatosMasMetadatosTabla() {
		
		try {
			
			String sqlQuery = "SELECT cod_editorial as 'CODIGO', nombre as 'NOMBRE' FROM editorial";
			resultado = Editorial.sentencia.executeQuery(sqlQuery);
			
		} catch (SQLException e) {
			
			System.out.println("Editorial: fallo al obtener datos + metadatos.");
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	//================//
	//    C.R.U.D
	//================//
	
	//CREATE
	public static String insertar(String editorial) {
		
		int retorno;
		
		try{
			
			String sql = "SELECT max(`cod_editorial`) FROM editorial";
			resultado = sentencia.executeQuery(sql);
			
			int nuevaId = -1;
			
			while(resultado.next()) {
				
				nuevaId = (resultado.getInt(1) + 1);
			}
			
			sql = "INSERT INTO editorial (cod_editorial, nombre) VALUES (" + nuevaId + ", '" + editorial + "')";
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Editorial " + editorial + " añadida correctamente." : "Error al añadir la editorial.";
	}
	
	//READ:
	//LEER TODOS
	public static Vector<Editorial> listar() throws SQLException{
		
		return buscaResultadosConConsulta("Select * from editorial");
	}
	
	//LEER POR ID
	public static Vector<Editorial> buscarPorId(int id) throws SQLException{
			
		return buscaResultadosConConsulta("Select cod_editorial, nombre from editorial where cod_editorial = " + id);
	}
	
	//LEER POR NOMBRE
	public static Vector<Editorial> buscarPorNombre(String nombre) throws SQLException{
				
		return buscaResultadosConConsulta("Select cod_autor, nombre from autor where nombre LIKE '" + nombre + "'");
	}
	
	//UPDATE
	//SÓLO CAMBIA EL NOMBRE (LA ID NO VA A CAMBIAR)
	public static String actualizar(int id, String nombre) {
		
		int retorno;
		
		try{
			
			String sql = "UPDATE editorial SET nombre = '" + nombre + "' WHERE cod_editorial = " + id;
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Modificación de editorial correcta." : "Error al actualizar la editorial.";
	}
	
	//DELETE
	public static String eliminar(int id) {
		
		int retorno;
		
		try{
			
			String sql = "DELETE FROM editorial WHERE cod_editorial = " + id;
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Se ha borrado la editorial." : "Error al eliminar la editorial.";
	}
}
