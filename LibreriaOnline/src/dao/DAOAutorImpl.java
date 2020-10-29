package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import modelo.Autor;
import modelo.Motor;

/**
 * Clase de Acceso a Datos AUTOR.
 * @author Iker Laforga
 *
 */
public class DAOAutorImpl extends ConexionMySQL implements Dao<Autor>{
	
	public DAOAutorImpl(){
		
		super();
		cargarDriver();
	}
	
	@Override
	public void registrar(Autor t) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("INSERT INTO autor (cod_autor, nombre) VALUES (?, ?)");
			query.setInt(1, t.getCodAutor());
			query.setString(2, t.getNombre());
			query.executeUpdate();
			Motor.log("Autor añadido correctamente.");
			JOptionPane.showMessageDialog(null, "Autor añadido correctamente.", "Servidor", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido añadirse el autor.");
			JOptionPane.showMessageDialog(null, "No ha podido añadirse el autor.", "Servidor", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}

	@Override
	public void actualizar(Autor t) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("UPDATE autor SET nombre = ? WHERE cod_autor = ?");
			query.setString(1, t.getNombre());
			query.setInt(2, t.getCodAutor());
			query.executeUpdate();
			Motor.log("Autor actualizado correctamente.");
			JOptionPane.showMessageDialog(null, "Autor actualizado correctamente.", "Servidor", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido actualizarse el autor.");
			JOptionPane.showMessageDialog(null, "No ha podido actualizarse el autor.", "Servidor", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}

	@Override
	public void eliminar(Autor t) {
		
		String mensaje = null;
		int tipoMensaje = 0;
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("DELETE FROM autor WHERE cod_autor = ?");
			query.setInt(1, t.getCodAutor());
			query.executeUpdate();
			
			tipoMensaje = JOptionPane.INFORMATION_MESSAGE;
			mensaje = "Autor eliminado correctamente.";
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			
			tipoMensaje = JOptionPane.ERROR_MESSAGE;
			mensaje = "No es posible eliminar un autor con libros asignados.";
			
		} catch (SQLException e) {
			
			tipoMensaje = JOptionPane.ERROR_MESSAGE;
			mensaje = "No ha podido eliminarse el autor.";
			e.printStackTrace();
			
		}finally {
			
			Motor.log(mensaje);
			JOptionPane.showMessageDialog(null, mensaje, "Servidor", tipoMensaje);
			cerrarConexion();
		}
	}

	@Override
	public ArrayList<Autor> listar() {

		conectar();
		ArrayList<Autor> lista = null;
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT * FROM autor");
			lista = new ArrayList<Autor>();
			ResultSet resultado = query.executeQuery();
			Motor.log("Listando autores...");
			
			while(resultado.next()) {
				
				Autor autor = new Autor(resultado);
				//Motor.log(resultado.getString(2));
				lista.add(autor);
			}
			
			resultado.close();
			query.close();
			
		} catch (SQLException e) {
			
			Motor.log("No han podido recuperarse los autores.");
			JOptionPane.showMessageDialog(null, "No han podido recuperarse los autores.", "Servidor", JOptionPane.ERROR_MESSAGE);
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
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT max(`cod_autor`) FROM autor");
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
	
	public void dropObras(Autor t) {
		
		String mensaje = null;
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("DELETE FROM autor_libro WHERE cod_autor = ?");
			query.setInt(1, t.getCodAutor());
			query.executeUpdate();
			mensaje = "Obras de " + t.getNombre() + " eliminadas correctamente.";
			
		} catch (SQLException e) {
			
			mensaje = "No han podido eliminarse las obras de " + t.getNombre();
			e.printStackTrace();
			
		}finally {
			
			Motor.log(mensaje);
			cerrarConexion();
		}
	}
}
