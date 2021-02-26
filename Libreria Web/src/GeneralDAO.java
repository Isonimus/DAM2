import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

public class GeneralDAO {
	
	public GeneralDAO() {
		
	}
	
	public static ResultSet getTop10Books(Connection conexion) { //RECIBE CONEXION
		
		String consulta = "SELECT L.isbn as ISBN, L.titulo as TITULO, CA.nombre as CATEGORIA, ED.nombre as EDITORIAL,\r\n"
				+ "(SELECT GROUP_CONCAT(\" \", autor.nombre) FROM autor_libro JOIN autor ON autor_libro.cod_autor = autor.cod_autor WHERE autor_libro.isbn = L.isbn) as AUTORES,\r\n"
				+ "sum(C.cantidad) as VENDIDOS \r\n"
				+ "FROM libreria.libro as L\r\n"
				+ "JOIN libreria.detalle_compra as C\r\n"
				+ "ON L.isbn = C.isbn_libro\r\n"
				+ "JOIN libreria.categoria as CA ON L.cod_categoria = CA.cod_categoria\r\n"
				+ "JOIN libreria.editorial as ED ON L.cod_editorial = ED.cod_editorial\r\n"
				+ "GROUP BY L.isbn\r\n"
				+ "ORDER BY sum(C.cantidad) DESC";
		
		//return getResultado();
		return extraerDatos(conexion, consulta);
	}
	
	public static ResultSet getAllBooks(Connection conexion) { //RECIBE CONEXION
		
		String consulta = "SELECT L.isbn as ISBN, L.titulo as TITULO, CA.nombre as CATEGORIA, ED.nombre as EDITORIAL,\n"
				+ "(SELECT GROUP_CONCAT(\" \", autor.nombre) FROM autor_libro JOIN autor ON autor_libro.cod_autor = autor.cod_autor WHERE autor_libro.isbn = L.isbn) as AUTORES,\n"
				+ "l.precio as PRECIO, l.stock as STOCK\n"
				+ "FROM libreria.libro as L\n"
				+ "JOIN libreria.categoria as CA ON L.cod_categoria = CA.cod_categoria\n"
				+ "JOIN libreria.editorial as ED ON L.cod_editorial = ED.cod_editorial WHERE L.stock > 0";
		
		return extraerDatos(conexion, consulta);
	}
	
	public static ResultSet searchBooks(Connection conexion, String busqueda, String tipoBusqueda) { //RECIBE CONEXION
		String consulta;
		String especificacion;
		Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
		
		//SI QUIERE BUSCAR POR ISBN Y EL VALOR ES NUMÉRICO
		if(tipoBusqueda.equals("isbn") && pattern.matcher(busqueda).matches()) {
			
			especificacion = "AND L.isbn = " + busqueda;
		
		//SI QUIERE BUSCAR POR TÍTULO,
		//O EL VALOR DEL ISBN ES LITERAL
		}else {
			
			especificacion = "AND L.titulo LIKE '%" + busqueda + "%'";
		}
		
		consulta = "SELECT L.isbn as ISBN, L.titulo as TITULO, CA.nombre as CATEGORIA, ED.nombre as EDITORIAL,\n" + 
					"(SELECT GROUP_CONCAT(\" \", autor.nombre) FROM autor_libro JOIN autor ON autor_libro.cod_autor = autor.cod_autor WHERE autor_libro.isbn = L.isbn) as AUTORES,\n" + 
					"l.precio as PRECIO, l.stock as STOCK\n" + 
					"FROM libreria.libro as L\n" + 
					"JOIN libreria.categoria as CA ON L.cod_categoria = CA.cod_categoria\n" + 
					"JOIN libreria.editorial as ED ON L.cod_editorial = ED.cod_editorial WHERE L.stock > 0\n" + 
				especificacion;
		
		return extraerDatos(conexion, consulta);
	}
	
	public static List<ResultSet> getCartContents(Connection conexion, Hashtable<String, Integer> carrito){
		
		ArrayList<ResultSet> resultado = new ArrayList<ResultSet>();
		Enumeration<String> libros = carrito.keys();
		while(libros.hasMoreElements()) {
			String consulta = "SELECT L.isbn as ISBN, L.titulo as TITULO, CA.nombre as CATEGORIA, ED.nombre as EDITORIAL,\n" + 
					"(SELECT GROUP_CONCAT(\" \", autor.nombre) FROM autor_libro JOIN autor ON autor_libro.cod_autor = autor.cod_autor WHERE autor_libro.isbn = L.isbn) as AUTORES,\n" + 
					"l.precio as PRECIO\n" + 
					"FROM libreria.libro as L\n" + 
					"JOIN libreria.categoria as CA ON L.cod_categoria = CA.cod_categoria\n" + 
					"JOIN libreria.editorial as ED ON L.cod_editorial = ED.cod_editorial\n" + 
					"WHERE L.isbn = " + libros.nextElement() + ";";
			
			resultado.add(extraerDatos(conexion, consulta));
		}
		return resultado;
	}
	
	public static ResultSet extraerDatos(Connection conexion, String sql) {
		Statement statement;
		ResultSet datos = null;
		
		try {
			
			statement = conexion.createStatement();
			datos = statement.executeQuery(sql);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return datos;
	}
}
