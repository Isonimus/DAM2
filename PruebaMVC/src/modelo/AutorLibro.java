package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AutorLibro extends DAO{
	
	//================//
	//  JAVA BEAN (|)
	//================//
	private int codAutor;
	private int isbn;
	
	public AutorLibro(int codAutor, int isbn) {
		
		setCodAutor(codAutor);
		setIsbn(isbn);
	}

	public int getCodAutor() {
		return codAutor;
	}

	public void setCodAutor(int codAutor) {
		this.codAutor = codAutor;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
	//================//
	//    UTILIDAD
	//================//
	private static Vector<AutorLibro> cargaResultSetToVector(ResultSet resultado) throws SQLException {
		
		Vector<AutorLibro> relaciones = new Vector<AutorLibro>();
		AutorLibro relacion;
		
		while(resultado.next()) {
			
			int codAutor = resultado.getInt(1);
			int isbn = resultado.getInt(2);
			relacion = new AutorLibro(codAutor, isbn);
			relaciones.add(relacion);
		}
		
		return relaciones;
	}
	
	private static Vector<AutorLibro> buscaResultadosConConsulta(String consulta) throws SQLException{
		
		try {
			
			resultado = sentencia.executeQuery(consulta);
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: Fallo al realizar la consulta.");
			e.printStackTrace();
		}
		
		return cargaResultSetToVector(resultado);
	}
	
	//================//
	//    C.R.U.D
	//================//
	//CREATE
	public static String insertar(AutorLibro relacion) {
		
		int retorno;
		
		try{
			
			String sql = "INSERT INTO autor_libro (cod_autor, isbn) VALUES "
					+ "(" + relacion.getCodAutor() 
					+ ", " + relacion.getIsbn() 
					+ ")";
			
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Autor " + relacion.getCodAutor() + " añadido correctamente al libro " + relacion.getIsbn() + "." : "Error al añadir el autor al libro.";
	}
	
	//READ:
	//LEER TODOS
	public static Vector<AutorLibro> listar() throws SQLException{
		
		return buscaResultadosConConsulta("Select * from autor_libro");
	}
	
	//LEER POR ISBN
	public static Vector<AutorLibro> buscarPorIsbn(int isbn) throws SQLException{
			
		return buscaResultadosConConsulta("Select cod_autor, isbn from autor_libro where isbn = " + isbn);
	}
	
	
	//LEER POR AUTOR
	public static Vector<AutorLibro> buscarPorCodAutor(int codAutor) throws SQLException{
				
		return buscaResultadosConConsulta("Select cod_autor, isbn from autor_libro where cod_autor = " + codAutor);
	}
	
	//DELETE TODO
	public static String eliminar(int codAutor, int isbn) {
		
		int retorno;
		
		try{
			
			String sql = "DELETE FROM libro WHERE isbn = '" + isbn + "'";
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Libro " + isbn + " eliminado correctamente." : "Error al eliminar el libro.";
	}
}