import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GeneralDAO {
	
	private static Statement sentencia;
	private static ResultSet resultado;
	
	public GeneralDAO() {
		
	}
	
	public static void setConexionBDD(Statement sentencia, ResultSet resultado) {
		
		setResultado(resultado);
		setSentencia(sentencia);
	}
	
	public static ResultSet getTop10Books() {
		
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
		
		try {
			
			setResultado(getSentencia().executeQuery(consulta));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return getResultado();
	}

	public static ResultSet getResultado() {return GeneralDAO.resultado;}

	public static void setResultado(ResultSet resultado) {GeneralDAO.resultado = resultado;}

	public static Statement getSentencia() {return GeneralDAO.sentencia;}

	public static void setSentencia(Statement sentencia) {GeneralDAO.sentencia = sentencia;}
}
