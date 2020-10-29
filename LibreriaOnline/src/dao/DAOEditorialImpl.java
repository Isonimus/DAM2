package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import modelo.Editorial;
import modelo.Motor;


/**
 * Clase de Acceso a Datos de tipo Editorial.
 * @author Iker Laforga
 *
 */
public class DAOEditorialImpl extends ConexionMySQL implements Dao<Editorial>{
	
	public DAOEditorialImpl(){
		
		super();
		cargarDriver();
	}

	@Override
	public void registrar(Editorial t) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("INSERT INTO editorial (cod_editorial, nombre) VALUES (?, ?)");
			query.setInt(1, t.getCodEditorial());
			query.setString(2, t.getNombre());
			query.executeUpdate();
			Motor.log("Editorial añadida correctamente.");
			JOptionPane.showMessageDialog(null, "Editorial añadida correctamente.");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido añadirse la editorial.");
			JOptionPane.showMessageDialog(null, "No ha podido añadirse la editorial.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}

	@Override
	public void actualizar(Editorial t) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("UPDATE editorial SET nombre = ? WHERE cod_editorial = ?");
			query.setString(1, t.getNombre());
			query.setInt(2, t.getCodEditorial());
			query.executeUpdate();
			Motor.log("Editorial actualizada correctamente.");
			JOptionPane.showMessageDialog(null, "Editorial actualizada correctamente.");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido actualizarse la editorial.");
			JOptionPane.showMessageDialog(null, "No ha podido actualizarse la editorial.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}

	@Override
	public void eliminar(Editorial t) {
		
		String mensaje = null;
		int tipoMensaje = 0;
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("DELETE FROM editorial WHERE cod_editorial = ?");
			query.setInt(1, t.getCodEditorial());
			query.executeUpdate();
			tipoMensaje = JOptionPane.INFORMATION_MESSAGE;
			mensaje = "Editorial eliminada correctamente.";
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			
			tipoMensaje = JOptionPane.ERROR_MESSAGE;
			mensaje = "No es posible eliminar una editorial con libros asignados.";
			
		} catch (SQLException e) {
			
			tipoMensaje = JOptionPane.ERROR_MESSAGE;
			mensaje = "No ha podido eliminarse la editorial.";
			e.printStackTrace();
			
		}finally {
			
			Motor.log(mensaje);
			JOptionPane.showMessageDialog(null, mensaje, "Servidor", tipoMensaje);
			cerrarConexion();
		}
	}

	@Override
	public ArrayList<Editorial> listar() {

		conectar();
		ArrayList<Editorial> lista = null;
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT * FROM editorial");
			lista = new ArrayList<Editorial>();
			ResultSet resultado = query.executeQuery();
			Motor.log("Listando editoriales...");
			
			while(resultado.next()) {
				
				Editorial editorial = new Editorial(resultado);
				//Motor.log(resultado.getString(2));
				lista.add(editorial);
			}
			
			resultado.close();
			query.close();
			
		} catch (SQLException e) {
			
			Motor.log("No han podido recuperarse las editoriales.");
			JOptionPane.showMessageDialog(null, "No han podido recuperarse las editoriales.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
		
		return lista;
	}
	
	public int getNextCode() {
		
		conectar();
		
		int currentId = 0;
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT max(`cod_editorial`) FROM editorial");
			ResultSet resultado = query.executeQuery();
			
			while(resultado.next()) {
				
				currentId = resultado.getInt(1);
			}
			
		} catch (SQLException e) {

			Motor.log("No ha podido recuperarse la siguiente ID.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
		
		return currentId + 1;
	}
}
