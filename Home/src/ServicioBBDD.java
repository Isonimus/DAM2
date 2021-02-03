import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServicioBBDD {
	
	private static ServicioBBDD servicio;
	private Connection conexion;
	
	public static final String MYSQL = "mysql";
	public static final String ORACLE = "oracle";
	public static final String POSTGRESQL = "postgresql";
	
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
	
	public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/libreria";
	public static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String POSTGRESQL_URL = "jdbc:postgresql://localhost:5432/libreria";
	
	private final String USER = "root";
	private final String PASS = "";
	
	private ServicioBBDD(String driver, String url){
		
		try {
			
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, USER, PASS);
			
			System.out.println("Conexión establecida con la BDD.");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("ERROR: No se ha podido cargar el Driver.");
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: No se ha podido conectar a la BDD.");
			e.printStackTrace();
		}
	}
	
	public static synchronized ServicioBBDD obtenerServicio(String servicioBBDD) { //SINGLETON + FACTORY
		
		if(servicio == null) {
			
			switch(servicioBBDD) {
				
				case ServicioBBDD.MYSQL:
					
					servicio = new ServicioBBDD(ServicioBBDD.MYSQL_DRIVER, ServicioBBDD.MYSQL_URL);
					break;
					
				case ServicioBBDD.ORACLE:
					
					servicio = new ServicioBBDD(ServicioBBDD.ORACLE_DRIVER, ServicioBBDD.ORACLE_URL);
					break;
					
				case ServicioBBDD.POSTGRESQL:
					
					servicio = new ServicioBBDD(ServicioBBDD.POSTGRESQL_DRIVER, ServicioBBDD.POSTGRESQL_URL);
					break;
			}
		}
		
		return servicio;
	}
	
	public Connection obtenerConexion() {
		
		return conexion;
	}
}
