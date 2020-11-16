package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * CLASE DAO - MAPEA LA TABLA LIBRO.
 * CADA INSTANCIA DE ESTA CLASE ES UNA 
 * FILA EN LA BDD
 * 
 * @author Iker Laforga
 */

public class Libro extends DAO{

	//================//
	//  JAVA BEAN (|)
	//================//
	
	private int isbn;
	private String nombreLibro;
	private double precio;
	private int stock;
	private int categoria;
	private int editorial;
	
	public Libro(String nombreLibro){
	
		setNombreLibro(nombreLibro);
	}
	
	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}
	
	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getEditorial() {
		return editorial;
	}

	public void setEditorial(int editorial) {
		this.editorial = editorial;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	//================//
	//    UTILIDAD
	//================//
	private static Vector<Libro> cargaResultSetToVector(ResultSet resultado) throws SQLException {
		
		Vector<Libro> libros = new Vector<Libro>();
		Libro libro;
		
		while(resultado.next()) {
			
			int isbn = resultado.getInt(1);
			String nombreLibro = resultado.getString(2);
			double precio = resultado.getDouble(3);
			int stock = resultado.getInt(4);
			int categoria = resultado.getInt(5);
			int editorial = resultado.getInt(6);
		
			libro = new Libro(nombreLibro);
			libro.setIsbn(isbn);
			libro.setPrecio(precio);
			libro.setStock(stock);
			libro.setCategoria(categoria);
			libro.setEditorial(editorial);
			libros.add(libro);
		}
		
		return libros;
	}
	
	private static Vector<Libro> buscaResultadosConConsulta(String consulta) throws SQLException{
		
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
	public static String insertar(String libro) {
		
		int retorno;
		
		try{
			
			String sql = "SELECT max(`cod_autor`) FROM autor";
			resultado = sentencia.executeQuery(sql);
			
			int nuevaId = -1;
			
			while(resultado.next()) {
				
				nuevaId = resultado.getInt(1);
			}
			
			sql = "INSERT INTO autor (cod_autor, nombre) VALUES (" + nuevaId + ", " + libro + ")";
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Autor " + libro + " añadido correctamente." : "Error al añadir el autor.";
	}
	
	//READ:
	//LEER TODOS
	public static Vector<Libro> listar() throws SQLException{
		
		return buscaResultadosConConsulta("Select * from libro");
	}
	
	//LEER POR ID
	public static Vector<Libro> buscarPorId(String id) throws SQLException{
			
		return buscaResultadosConConsulta("Select isbn, titulo from libro where isbn = " + id);
	}
	
	//LEER POR NOMBRE
	public static Vector<Libro> buscarPorNombre(String titulo) throws SQLException{
				
		return buscaResultadosConConsulta("Select isbn, titulo from libro where titulo LIKE '" + titulo + "'");
	}
	
	//UPDATE

	
	//DELETE
	public static String eliminar(String isbn) {
		
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
