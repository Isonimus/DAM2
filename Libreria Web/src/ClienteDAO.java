import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public class ClienteDAO {
	
	public ClienteDAO() {
		
	}
	
	public static String insertarCliente(Connection conexion, String dni, String nombre, String ape1, String ape2, String direccion, String email, String fnac, String uName, String uPass) {
		
		Statement statement;
		int retorno;
		String error = null;
		String consulta = "INSERT INTO `cliente` (`dni`, `nombre`, `apellido1`, `apellido2`, `direccion`, `email`, `fecha_nacimiento`, `usuario`, `contrasenya`) VALUES ('" + dni + "','" 
							+ nombre + "','"
							+ ape1 + "','"
							+ ape2 + "','"
							+ direccion + "','"
							+ email + "','"
							+ fnac + "','"
							+ uName + "','"
							+ uPass + "')";
		
		try {
			statement = conexion.createStatement();
			retorno = statement.executeUpdate(consulta);
		
		}catch (MySQLIntegrityConstraintViolationException e){
			
			retorno = 0;
			error = "Error: Ya existe un usuario con ese Alias.";
			
		} catch (SQLException e) {
			
			retorno = 0;
			error = "Error al crear el usuario.";
		}
		
		return (retorno > 0) ? "Registro completado correctamente." : error;
	}
	
	public static boolean logIn(Connection conexion, String userLogin, String userPass) throws SQLException {
		
		String consulta = "SELECT COUNT(dni) FROM libreria.cliente WHERE usuario = '" + userLogin + "' AND contrasenya = '" +userPass + "'";
		ResultSet rs = extraerDatos(conexion, consulta);
		int found = 0;
		
		while(rs.next()) {
			found = rs.getInt(1);
		}
		
		if(found == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	public static ResultSet getUserFingerprint(Connection conexion, String user, String pass) { //RECIBE CONEXION
		
		String consulta = "SELECT dni, nombre FROM cliente WHERE usuario = '" + user + "' AND contrasenya = '" + pass + "'";
		
		//return getResultado();
		return extraerDatos(conexion, consulta);
	}
	
	public static ResultSet getUserData(Connection conexion, String dni) { //RECIBE CONEXION
		
		String consulta = "SELECT * FROM cliente WHERE dni = '" + dni + "'";
		//return getResultado();
		return extraerDatos(conexion, consulta);
	}
	
	public static ResultSet getUserHistory(Connection conexion, String dni) {
		
		String consulta = "SELECT LC.cod_compra AS REFERENCIA, LC.fecha_compra AS FECHA, (count(L.titulo)) AS 'NUM ARTÍCULOS', (group_concat(\" \", L.titulo)) AS ARTÍCULOS \r\n" + 
				"FROM libreria.compra as LC \r\n" + 
				"JOIN libreria.detalle_compra as DC ON LC.cod_compra = DC.cod_compra \r\n" + 
				"JOIN libreria.libro as L ON L.isbn = DC.isbn_libro \r\n" + 
				"WHERE LC.dni_cliente = '" + dni + "' \r\n" + 
				"group by LC.cod_compra";
		
		return extraerDatos(conexion, consulta);
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
