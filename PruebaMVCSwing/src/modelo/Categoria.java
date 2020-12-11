package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * CLASE DAO - MAPEA LA TABLA CATEGORIA.
 * CADA INSTANCIA DE ESTA CLASE ES UNA 
 * FILA EN LA BDD
 * 
 * @author Iker Laforga
 */

public class Categoria extends DAO{

	//================//
	//  JAVA BEAN (|)
	//================//
	
	private int idCategoria;
	private String nombreCategoria;
	
	public Categoria(String nombreCategoria){
	
		setNombreCategoria(nombreCategoria);
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	//================//
	//    UTILIDAD
	//================//
	//REFACTOR-EXTENDS
	private static Vector<Categoria> cargaResultSetToVector(ResultSet resultado) throws SQLException {
		
		Vector<Categoria> categorias = new Vector<Categoria>();
		Categoria categoria;
		
		while(resultado.next()) {
			
			int idCategoria = resultado.getInt(1);
			String nombreCategoria = resultado.getString(2);
			categoria = new Categoria(nombreCategoria);
			categoria.setIdCategoria(idCategoria);
			categorias.add(categoria);
		}
		
		return categorias;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//REFACTOR -- EXTENDS
	private static Vector<Categoria> buscaResultadosConConsulta(String consulta) throws SQLException{
		
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
			
			String sqlQuery = "SELECT cod_categoria as 'CODIGO', nombre as 'NOMBRE' FROM categoria";
			resultado = Categoria.sentencia.executeQuery(sqlQuery);
			
		} catch (SQLException e) {
			
			System.out.println("CATEGORÍA: fallo al obtener datos + metadatos.");
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	//================//
	//    C.R.U.D
	//================//
	
	//CREATE
	public static String insertar(String categoria) {
		
		int retorno;
		
		try{
			
			String sql = "SELECT max(`cod_categoria`) FROM categoria";
			resultado = sentencia.executeQuery(sql);
			
			int nuevaId = -1;
			
			while(resultado.next()) {
				
				nuevaId = (resultado.getInt(1) + 1);
			}
			
			sql = "INSERT INTO categoria (cod_categoria, nombre) VALUES (" + nuevaId + ", '" + categoria + "')";
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Categoría " + categoria + " añadida correctamente." : "Error al añadir la categoría.";
	}
	
	//READ:
	//LEER TODOS
	public static Vector<Categoria> listar() throws SQLException{
		
		return buscaResultadosConConsulta("Select * from categoria");
	}
	
	//LEER POR ID
	public static Vector<Categoria> buscarPorId(int id) throws SQLException{
			
		return buscaResultadosConConsulta("Select cod_categoria, nombre from categoria where cod_categoria = " + id);
	}
	
	//LEER POR NOMBRE
	public static Vector<Categoria> buscarPorNombre(String nombre) throws SQLException{
				
		return buscaResultadosConConsulta("Select cod_categoria, nombre from categoria where nombre LIKE '" + nombre + "'");
	}
	
	//UPDATE
	//SÓLO CAMBIA EL NOMBRE (LA ID NO VA A CAMBIAR)
	public static String actualizar(int id, String nombre) {
		
		int retorno;
		
		try{
			
			String sql = "UPDATE categoria SET nombre = '" + nombre + "' WHERE cod_categoria = " + id;
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Categoría " + id + " actualizada correctamente." : "Error al actualizar la categoría.";
	}
	
	//DELETE
	public static String eliminar(int id) {
		
		int retorno;
		
		try{
			
			String sql = "DELETE FROM categoria WHERE cod_categoria = " + id;
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Categoría " + id + " eliminada correctamente." : "Error al eliminar la categoría.";
	}
}
