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
	public static String insertar(Libro libro) {
		
		int retorno;
		
		try{
			
			
			String sql = "INSERT INTO libro (isbn, titulo, precio, stock, cod_categoria, cod_editorial) VALUES "
					+ "(" + libro.getIsbn() 
					+ ", '" + libro.getNombreLibro() 
					+ "', " + libro.getPrecio() 
					+ ", " + libro.getStock() 
					+ ", " + libro.getCategoria() 
					+ ", " + libro.getEditorial() + ")";
			
			retorno = sentencia.executeUpdate(sql);
			
		}catch(SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Libro " + libro.getNombreLibro() + " a�adido correctamente." : "Error al a�adir el libro.";
	}
	
	//READ:
	//LEER TODOS
	public static Vector<Libro> listar() throws SQLException{
		
		return buscaResultadosConConsulta("Select * from libro");
	}
	
	//LEER POR ID
	public static Vector<Libro> buscarPorId(int id) throws SQLException{
			
		return buscaResultadosConConsulta("Select isbn, titulo from libro where isbn = " + id);
	}
	
	//LEER POR NOMBRE
	public static Vector<Libro> buscarPorNombre(String titulo) throws SQLException{
				
		return buscaResultadosConConsulta("Select isbn, titulo from libro where titulo LIKE '" + titulo + "'");
	}
	
	//LEER AUTORES DE UN LIBRO
	public static Vector<Autor> listarAutores(int isbn) throws SQLException{
		
		Vector<Autor> autores = new Vector<Autor>();
		Vector<AutorLibro> relaciones = AutorLibro.buscarPorIsbn(isbn);
		
		for(AutorLibro relacion : relaciones) {
			
			autores.add(Autor.buscarPorId(relacion.getCodAutor()).get(0));
		}
		
		return autores;
	}
	
	//UPDATE
	public static String actualizar(int isbn, String propiedad, String valor) {
		
		int retorno;
		String sql;
		
		if(propiedad.equals("titulo")) {
			
			valor = "'" + valor + "'";	
		}
		
		try {
			
			sql = "UPDATE libro SET " + propiedad + " = " + valor + " WHERE isbn = " + isbn;
			retorno = sentencia.executeUpdate(sql);
			
		} catch (SQLException e) {
			
			retorno = 0;
		}
		
		return (retorno > 0) ? "Libro " + isbn + " editado correctamente." : "Error al editar el libro.";
	}
	
	//ACTUALIZAR AUTORES
	//A�ADIR AUTOR
	public static String addAutor(int idAutor, int isbn) {
		
		int retorno;
		String sql;
		
		try {
			
			sql = "INSERT INTO autor_libro (cod_autor, isbn) VALUES (" + idAutor + ", " + isbn + ")";
			retorno = sentencia.executeUpdate(sql);
			
		} catch (SQLException e) {
			
			retorno = 0;
		}

		return (retorno > 0) ? "Autor a�adido correctamente al libro " + isbn + "." : "Error al a�adir el autor al libro.";
	}
	
	//ELIMINAR AUTOR
	public static String dropAutor(int idAutor, int isbn) {
		
		int retorno;
		String sql;
		
		try {
			
			sql = "DELETE FROM autor_libro WHERE cod_autor = " + idAutor + " AND isbn = " + isbn;
			retorno = sentencia.executeUpdate(sql);
			
		} catch (SQLException e) {
			
			retorno = 0;
		}

		return (retorno > 0) ? "Autor eliminado correctamente del libro " + isbn + "." : "Error al eliminar el autor del libro.";
	}

	//DELETE
	public static String eliminar(int isbn) {
		
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
