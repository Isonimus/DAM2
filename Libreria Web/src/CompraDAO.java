import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

public class CompraDAO {
	
	public CompraDAO() {
		
	}
	
	public static String insertarCompra(Connection conexion, HttpSession sesion) {
		
		Statement statement;
		Hashtable<String, Integer> carrito = (Hashtable<String, Integer>) sesion.getAttribute("carrito");
		boolean completado = true;
		
		//EXTRAER SIGUIENTE ID
		String consulta = "SELECT max(cod_compra) FROM compra";
		ResultSet rs = extraerDatos(conexion, consulta);
		int id = 0;
		try {
			while(rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//INICIAR LA TRANSACCIÓN
		try {
			conexion.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//LA CABECERA
		consulta = "INSERT INTO compra (cod_compra, fecha_compra, dni_cliente)"
				+ "VALUES (" + id + ", CURDATE(), '" + sesion.getAttribute("userId") + "')";
		
		try {
			
			statement = conexion.createStatement();
			statement.executeUpdate(consulta);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			completado = false;
		}
		
		//LOS DETALLES
		Enumeration<String> libros = carrito.keys();
		while(libros.hasMoreElements()) {
			String isbn = libros.nextElement();
			consulta = "INSERT INTO detalle_compra (cod_compra, isbn_libro, cantidad) VALUES (" + id + ", " + Integer.parseInt(isbn) + ", " + carrito.get(isbn) + ")";
			try {
				
				statement = conexion.createStatement();
				statement.executeUpdate(consulta);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				completado = false;
			}
		}
		
		//DESCONTAR STOCKS
		Enumeration<String> stocks = carrito.keys();
		while(stocks.hasMoreElements()) {
			String isbn = stocks.nextElement();
			consulta = "UPDATE libro SET stock = stock -" + carrito.get(isbn) + " WHERE isbn = " + isbn;
			try {
				
				statement = conexion.createStatement();
				statement.executeUpdate(consulta);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				completado = false;
			}
		}
		
		//ANTIRECOMPRA
		if(carrito.size() == 0) {
			completado = false;
		}
		
		//SI TODO FUE BIEN
		if(completado) {
			try {
				conexion.commit();
				conexion.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else { // SI NO
			try {
				conexion.rollback();
				conexion.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return (completado) ? "Compra realizada correctamente." : "Error al realizar la compra.";
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
