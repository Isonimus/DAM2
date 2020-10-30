package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Motor;

/**
 * Gestiona la conexión y operaciones en una 
 * base de datos MySQL.
 * 
 * @author Iker Laforga
 *
 */
public class ConexionMySQL { //MODIFICAR 
	
	public static final String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DEFAULT_DB_URL = "jdbc:mysql://localhost/libreria";
	public static final String DEFAULT_USERNAME = "root";
	public static final String DEFAULT_PASS = "admin";
	private String driver;
	private String url;
	private String user;
	private String pass;
	protected Connection conn;
	private Statement sentencia;
	protected ResultSet resultado;
	
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
	 * CONSTRUCTOR SIMPLE:
	 * Recibe sólo el Nombre de Usuario y la Clave. Carga el driver y los datos de MySQL por defecto.
	 * @param user String: El Nombre de Usuario de MySQL
	 * @param pass String: La Clave de Usuario de MySQL
	 */
	public ConexionMySQL(String user, String pass){
		
		this.setUser(user);
		this.setPass(pass);
		this.setDriver(DEFAULT_DRIVER);
		this.setUrl(DEFAULT_DB_URL);
	}
	
	/**
	 * CONSTRUCTOR AVANZADO:
	 * Recibe todos los parámetros por separado
	 * @param driver String: El driver JDBC
	 * @param url String: La url con los parámetros de MySQL ("jdbc:mysql://localhost/[nombreBDD]")
	 * @param user String: El nombre de Usuario de MySQL
	 * @param pass String: La clave de Usuario de MySQL
	 */
	public ConexionMySQL(String driver, String url, String user, String pass){
		
		this.setDriver(driver);
		this.setUrl(url);
		this.setUser(user);
		this.setPass(pass);
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
			Motor.log("DRIVER '" + getDriver() + "' cargado correctamente.");
			
		} catch (ClassNotFoundException e) {
			
			Motor.log("ERROR: No se ha podido cargar el driver.");
			return;
		}
	}
	
	/**
	 * Crea la conexión.
	 */
	public void conectar() {
		
		try {
			
			conn = DriverManager.getConnection(getUrl(), getUser(), getPass());
			sentencia = conn.createStatement();
			Motor.log( "Conexión establecida con la BDD." );
			
		} catch (SQLException e) {
			
			Motor.log("ERROR: No ha podido establecerse la conexión.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Termina la conexión con la BDD. (Throws)
	 * @throws SQLException La posible excepción a manejar.
	 */
	public void cerrarConexion(){
		
		if(conn != null) {
			
			try {
				
				if(!conn.isClosed()) {
					
					conn.close();
					Motor.log( "Conexión con la BDD cerrada correctamente." );
				}
				
			} catch (SQLException e) {
				
				Motor.log( "No ha podido cerrarse la conexión con la BDD." );
				e.printStackTrace();
			}
		}
	}
}
