package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import modelo.Categoria;
import modelo.Motor;


/**
 * Clase de Acceso a Datos de tipo Editorial.
 * @author Iker Laforga
 *
 */
public class DAOCategoriaImpl extends ConexionMySQL implements Dao<Categoria>{
	
	public DAOCategoriaImpl(){
		
		super();
		cargarDriver();
	}

	@Override
	public void registrar(Categoria t) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("INSERT INTO categoria (cod_categoria, nombre) VALUES (?, ?)");
			query.setInt(1, t.getCodCategoria());
			query.setString(2, t.getNombre());
			query.executeUpdate();
			Motor.log("Categor�a a�adida correctamente.");
			JOptionPane.showMessageDialog(null, "Categor�a a�adida correctamente.");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido a�adirse la categor�a.");
			JOptionPane.showMessageDialog(null, "No ha podido a�adirse la categor�a.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}

	@Override
	public void actualizar(Categoria t) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("UPDATE categoria SET nombre = ? WHERE cod_categoria = ?");
			query.setString(1, t.getNombre());
			query.setInt(2, t.getCodCategoria());
			query.executeUpdate();
			Motor.log("Categor�a actualizada correctamente.");
			JOptionPane.showMessageDialog(null, "Categor�a actualizada correctamente.");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido actualizarse la categor�a.");
			JOptionPane.showMessageDialog(null, "No ha podido actualizarse la categor�a.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}

	@Override
	public void eliminar(Categoria t) {
		
		String mensaje = null;
		int tipoMensaje = 0;
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("DELETE FROM categoria WHERE cod_categoria = ?");
			query.setInt(1, t.getCodCategoria());
			query.executeUpdate();
			tipoMensaje = JOptionPane.INFORMATION_MESSAGE;
			mensaje = "Categor�a eliminada correctamente.";
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			
			tipoMensaje = JOptionPane.ERROR_MESSAGE;
			mensaje = "No es posible eliminar una categor�a con libros asignados.";
			
		} catch (SQLException e) {
			
			tipoMensaje = JOptionPane.ERROR_MESSAGE;
			mensaje = "No ha podido eliminarse la categor�a.";
			e.printStackTrace();
			
		}finally {
			
			Motor.log(mensaje);
			JOptionPane.showMessageDialog(null, mensaje, "Servidor", tipoMensaje);
			cerrarConexion();
		}
	}

	@Override
	public ArrayList<Categoria> listar() {

		conectar();
		ArrayList<Categoria> lista = null;
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT * FROM categoria");
			lista = new ArrayList<Categoria>();
			ResultSet resultado = query.executeQuery();
			Motor.log("Listando categor�as...");
			
			while(resultado.next()) {
				
				Categoria categoria = new Categoria(resultado);
				//Motor.log(resultado.getString(2));
				lista.add(categoria);
			}
			
			resultado.close();
			query.close();
			
		} catch (SQLException e) {
			
			Motor.log("No han podido recuperarse las categor�as.");
			JOptionPane.showMessageDialog(null, "No han podido recuperarse las categor�as.");
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
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT max(`cod_categoria`) FROM categoria");
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
