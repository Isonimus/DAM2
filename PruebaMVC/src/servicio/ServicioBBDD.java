package servicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServicioBBDD {
	
	private static ServicioBBDD servicio;
	private Connection conexion;
	
	private ServicioBBDD(){ //SINGLETON
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/libreria";
			String user = "root";
			String pass = "admin";
			
			conexion = DriverManager.getConnection(url, user, pass);
			
			System.out.println("Conexión establecida con la BDD.");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("ERROR: No se ha podido cargar el Driver.");
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: No se ha podido conectar a la BDD.");
			e.printStackTrace();
		}
	}
	
	public static synchronized ServicioBBDD obtenerServicio() {
		
		if(servicio == null) {
			
			servicio = new ServicioBBDD();
		}
		
		return servicio;
	}
	
	public Connection obtenerConexion() {
		
		return conexion;
	}
}
