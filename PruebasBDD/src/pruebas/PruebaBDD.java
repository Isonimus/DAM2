package pruebas;

import java.sql.*;

public class PruebaBDD {

	public static void main(String[] args) {

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Cargando driver...");
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreria", //URL
															  "root", //USER
															  "admin"); //PASS
			System.out.println("Conexión creada con la librería.");
			
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM autor"); //QUERY DE PRUEBA
			
			while(rs.next()) {
				
				int codAutor = rs.getInt(1);
				String nombreAutor = rs.getString(2);
				
				System.out.println("Código Autor: " + codAutor + ". Nombre autor: " + nombreAutor + ".");
			}
			
			rs.close();
			System.out.println("Cerrando ResultSet...");
			stmt.close();
			System.out.println("Cerrando Statement...");
			conexion.close();
			System.out.println("Cerrando Conexión con BDD...");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("No se ha encontrado el Driver.");
			e.printStackTrace();
			
		} catch(SQLException e) {
			
			System.out.println("Error SQL:");
			e.printStackTrace();
		}

	}

}
