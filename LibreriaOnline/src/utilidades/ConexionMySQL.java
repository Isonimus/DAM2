package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Controla la conexión con una base de datos MySQL.
 * @author Iker Laforga 
 *
 */
public class ConexionMySQL {
	
	public static final String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DEFAULT_DB_URL = "jdbc:mysql://localhost/libreria"; //"jdbc:sgbd://localhost/libreria"
	public static final String DEFAULT_USERNAME = "root";
	public static final String DEFAULT_PASS = "admin";
	private String driver;
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	private Statement sentencia;
	private ResultSet resultado;
	
	/**
	 * CONSTRUCTOR POR DEFECTO:
	 * Aplica todos los parámetros definidos
	 * en los valores por defecto.
	 */
	public ConexionMySQL(){
		
		this.setUser(DEFAULT_USERNAME);
		this.setPass(DEFAULT_PASS);
		this.setDriver(DEFAULT_DRIVER);
		this.setUrl(DEFAULT_DB_URL);
	}
	
	/**
	 * CONSTRUCTOR AVANZADO:
	 * Recibe todos los parámetros por separado
	 * @param driver El driver JDBC
	 * @param url La url con los parámetros de MySQL ("jdbc:mysql://localhost/[nombreBDD]")
	 * @param user El nombre de Usuario de MySQL
	 * @param pass La clave de Usuario de MySQL
	 */
	public ConexionMySQL(String driver, String url, String user, String pass){
		
		this.setDriver(driver);
		this.setUrl(url);
		this.setUser(user);
		this.setPass(pass);
	}
	
	/**
	 * CONSTRUCTOR SIMPLE:
	 * Recibe sólo el Nombre de Usuario y la Clave. Carga el driver y los datos de MySQL por defecto.
	 * @param user El Nombre de Usuario
	 * @param pass La Clave de Usuario
	 */
	public ConexionMySQL(String user, String pass){
		
		this.setUser(user);
		this.setPass(pass);
		this.setDriver(DEFAULT_DRIVER);
		this.setUrl(DEFAULT_DB_URL);
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getSentencia() {
		return sentencia;
	}

	public void setSentencia(Statement sentencia) {
		this.sentencia = sentencia;
	}

	public ResultSet getResultado() {
		return resultado;
	}

	public void setResultado(ResultSet resultado) {
		this.resultado = resultado;
	}
	
	/**
	 * Carga el driver especificado en el constructor.
	 */
	public void cargarDriver() {
		
		try {
			
			Class.forName(getDriver());
			System.out.println("DRIVER '" + getDriver() + "' cargado correctamente.");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("ERROR: No se ha podido cargar el driver.");
			return;
		}
	}
	
	/**
	 * Crea la conexión con manejo propio de excepciones.
	 */
	public void conectar1() {
		
		try {
			
			conn = DriverManager.getConnection(getUrl(), getUser(), getPass());
			sentencia = conn.createStatement();
			System.out.println( "Conexión establecida con la BDD." );
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: No ha podido establecerse la conexión.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Crea la conexión con Thows en las excepciones.
	 * @throws SQLException La posible excepción a manejar.
	 */
	void conectar2() throws SQLException {
		
		conn = DriverManager.getConnection(getUrl(), getUser(), getPass());
		sentencia = conn.createStatement();
		System.out.println( "Conexión establecida con la BDD." );
	}
	
	/**
	 * Termina la conexión con la BDD. (Throws)
	 * @throws SQLException La posible excepción a manejar.
	 */
	public void cerrarConexion() throws SQLException {
		
		conn.close();
		System.out.println( "Conexión con la BDD cerrada correctamente." );
	}
	
	/**
	 * Realiza una consulta a la BDD.
	 * @param consulta La consulta a realizar.
	 * @return El resultado de la consulta.
	 */
	public ResultSet consulta(String consulta) {
		
		try {
			
			resultado = sentencia.executeQuery(consulta);
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: No ha podido realizarse la consulta.");
			e.printStackTrace();
		}
		return resultado;
	}
	
	/**
	 * Realiza una actualización en la BDD.
	 * @param consulta La consulta.
	 * @return El número de filas afectadas.
	 */
	public int actualizar(String consulta) {
		
		int numFilas = 0;
		
		try {
			
			numFilas = sentencia.executeUpdate(consulta);
			System.out.println(numFilas + " filas actualizadas.");
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: No ha podido realizarse la actualización.");
			e.printStackTrace();
		}
		
		return numFilas;
	}
	
	/**
	 * Elimina registros de la BDD.
	 * @param consulta La consulta.
	 * @throws SQLException La posible excepción.
	 */
	public void borrar(String consulta) throws SQLException{
	
		int numFilas = sentencia.executeUpdate(consulta);
		System.out.println(numFilas + " filas eliminadas.");
	}
	
	/**
	 * Inserta registros en la BDD.
	 * @param consulta La consulta.
	 * @throws SQLException La posible excepción.
	 */
	public void insertar(String consulta) throws SQLException{
		
		int numFilas = sentencia.executeUpdate(consulta);
		System.out.println(numFilas + " elemento insertado.");
	}
	
	/**
	 * Crea tablas en la BDD
	 * @param consulta La consulta.
	 * @throws SQLException La posible excepción.
	 */
	public void crear(String consulta) throws SQLException{
		
		resultado = sentencia.executeQuery(consulta);
		System.out.println("Tabla o vista creada con éxito.");
	}
}
