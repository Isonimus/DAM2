package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import modelo.Cliente;
import modelo.Motor;


/**
 * Clase de Acceso a Datos Cliente.
 * @author Iker Laforga
 *
 */
public class DAOClienteImpl extends ConexionMySQL implements Dao<Cliente>{
	
	public DAOClienteImpl(){
		
		super();
		cargarDriver();
	}

	@Override
	public void registrar(Cliente t) {

		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("INSERT INTO cliente (dni, nombre, apellido1, apellido2, direccion, email, fecha_nacimiento, usuario, contrasenya) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			query.setString(1, t.getDni());
			query.setString(2, t.getNombre());
			query.setString(3, t.getApellido1());
			query.setString(4, t.getApellido2());
			query.setString(5, t.getDireccion());
			query.setString(6, t.getEmail());
			query.setDate(7, t.getFechaNacimiento());
			query.setString(8, t.getUsuario());
			query.setString(9, t.getContrasenya());
			query.executeUpdate();
			Motor.log("Cliente añadido correctamente.");
			JOptionPane.showMessageDialog(null, "Cliente añadido correctamente.");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido añadirse el cliente.");
			JOptionPane.showMessageDialog(null, "No ha podido añadirse el cliente.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
		
	}

	@Override
	public void actualizar(Cliente t) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("UPDATE cliente SET nombre = ?, apellido1 = ?, apellido2 = ?, direccion = ?, email = ?, fecha_nacimiento = ?, usuario = ?, contrasenya = ? WHERE dni = ?");
			query.setString(1, t.getNombre());
			query.setString(2, t.getApellido1());
			query.setString(3, t.getApellido2());
			query.setString(4, t.getDireccion());
			query.setString(5, t.getEmail());
			query.setDate(6, t.getFechaNacimiento());
			query.setString(7, t.getUsuario());
			query.setString(8, t.getContrasenya());
			query.setString(9, t.getDni());
			query.executeUpdate();
			Motor.log("Cliente actualizado correctamente.");
			JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido actualizarse el cliente.");
			JOptionPane.showMessageDialog(null, "No ha podido actualizarse el cliente.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}

	@Override
	public void eliminar(Cliente t) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("DELETE FROM cliente WHERE dni = ?");
			query.setString(1, t.getDni());
			query.executeUpdate();
			Motor.log("Cliente eliminado correctamente.");
			JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido eliminarse el cliente.");
			JOptionPane.showMessageDialog(null, "No ha podido eliminarse el cliente.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}

	@Override
	public ArrayList<Cliente> listar() {
		
		conectar();
		ArrayList<Cliente> lista = null;
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT * FROM cliente");
			lista = new ArrayList<Cliente>();
			ResultSet resultado = query.executeQuery();
			Motor.log("Listando clientes...");
			
			while(resultado.next()) {
				
				Cliente cliente = new Cliente(resultado);
				//Motor.log(resultado.getString(2));
				lista.add(cliente);
			}
			
			resultado.close();
			query.close();
			
		} catch (SQLException e) {
			
			Motor.log("No han podido recuperarse los clientes.");
			JOptionPane.showMessageDialog(null, "No han podido recuperarse los clientes.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
		
		return lista;
	}
}