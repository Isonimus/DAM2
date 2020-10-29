package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import modelo.Autor;
import modelo.Libro;
import modelo.Motor;


public class DAOLibroImpl extends ConexionMySQL implements Dao<Libro>{
	
	public DAOLibroImpl(){
		
		super();
		cargarDriver();
	}

	@Override
	public void registrar(Libro t) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("INSERT INTO libro (isbn, titulo, precio, stock, cod_categoria, cod_editorial) VALUES (?, ?, ?, ?, ?, ?)");
			query.setInt(1, t.getIsbn());
			query.setString(2, t.getTitulo());
			query.setDouble(3, t.getPrecio());
			query.setInt(4, t.getStock());
			query.setInt(5, t.getCodCategoria());
			query.setInt(6, t.getCodEditorial());
			query.executeUpdate();
			Motor.log("Libro añadido correctamente.");
			JOptionPane.showMessageDialog(null, "Libro añadido correctamente.");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido añadirse el libro.");
			JOptionPane.showMessageDialog(null, "No ha podido añadirse el libro.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}

	@Override
	public void actualizar(Libro t) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("UPDATE libro SET isbn = ?, titulo = ?, precio = ?, stock = ?, cod_categoria = ?, cod_editorial = ? WHERE isbn = ?");
			query.setInt(1, t.getIsbn());
			query.setString(2, t.getTitulo());
			query.setDouble(3, t.getPrecio());
			query.setInt(4, t.getStock());
			query.setInt(5, t.getCodCategoria());
			query.setInt(6, t.getCodEditorial());
			query.setInt(7, t.getIsbn());
			query.executeUpdate();
			Motor.log("Libro actualizado correctamente.");
			JOptionPane.showMessageDialog(null, "Libro actualizado correctamente.", "Servidor", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido actualizarse el libro.");
			JOptionPane.showMessageDialog(null, "No ha podido actualizarse el libro.", "Servidor", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}

	@Override
	public void eliminar(Libro t) {
		
		String mensaje = null;
		int tipoMensaje = 0;
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("DELETE FROM libro WHERE isbn = ?");
			query.setInt(1, t.getIsbn());
			query.executeUpdate();
			tipoMensaje = JOptionPane.INFORMATION_MESSAGE;
			mensaje = "Libro eliminado correctamente.";
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			
			tipoMensaje = JOptionPane.ERROR_MESSAGE;
			mensaje = "No es posible eliminar un libro con autores asignados.";
			
		} catch (SQLException e) {
			
			tipoMensaje = JOptionPane.ERROR_MESSAGE;
			mensaje = "No ha podido eliminarse el libro.";
			e.printStackTrace();
			
		}finally {
			
			Motor.log(mensaje);
			JOptionPane.showMessageDialog(null, mensaje, "Servidor", tipoMensaje);
			cerrarConexion();
		}
	}
	
	@Override
	public ArrayList<Libro> listar() {
		
		conectar();
		ArrayList<Libro> lista = null;
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT * FROM libro");
			lista = new ArrayList<Libro>();
			ResultSet resultado = query.executeQuery();
			Motor.log("Listando libros...");
			
			while(resultado.next()) {
				
				Libro libro = new Libro(resultado);
				//Motor.log(resultado.getString(2));
				lista.add(libro);
			}
			
			resultado.close();
			query.close();
			
		} catch (SQLException e) {
			
			Motor.log("No han podido recuperarse los libros.");
			JOptionPane.showMessageDialog(null, "No han podido recuperarse los libros.", "Servidor", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
		
		return lista;
	}
	
	/**
	 * Retorna un ArrayList de objetos Autor con los autores  
	 * de un libro.
	 * @param t
	 * @return ArrayList de Autores
	 */
	public ArrayList<Autor> listarAutores(Libro t) {
		
		conectar();
		ArrayList<Autor> lista = null;
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT autor.cod_autor, autor.nombre FROM libro JOIN autor_libro ON libro.isbn = autor_libro.isbn JOIN autor ON autor_libro.cod_autor = autor.cod_autor WHERE libro.isbn = ?");
			query.setInt(1, t.getIsbn());
			lista = new ArrayList<Autor>();
			ResultSet resultado = query.executeQuery();
			Motor.log("Listando autores de la obra...");
			
			while(resultado.next()) {
				
				Autor autor = new Autor(resultado);
				Motor.log(resultado.getString(2));
				lista.add(autor);
			}
			
			resultado.close();
			query.close();
			
		} catch (SQLException e) {
			
			Motor.log("No han podido recuperarse los Autores de la obra.");
			JOptionPane.showMessageDialog(null, "No han podido recuperarse los autores.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
		
		return lista;
	}
	
	/**
	 * Retorna un String con el nombre de la editorial de un libro.
	 * @param t
	 * @return (String) El nombre de la editorial 
	 */
	public String getNombreEditorial(Libro t) {
		
		conectar();
		
		String nombre = null;
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("SELECT nombre FROM editorial WHERE cod_editorial = ?");
			query.setInt(1, t.getCodEditorial());
			ResultSet resultado = query.executeQuery();
			
			while(resultado.next()) {
				
				nombre = resultado.getString(1);
			}
			
			Motor.log("Recuperando nombre de la editorial...");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido recuperarse el nombre de la editorial...");
			e.printStackTrace();
			
		} finally {
				
				cerrarConexion();
		}
		
		return nombre;
	}
	
	/**
	 * Actualiza los autores de un libro en la tabla pivote.
	 * @param t
	 */
	public void actualizarAutores(Libro t) {
		
		ArrayList<Autor> autoresBd = listarAutores(t);
		ArrayList<Autor> autoresLibro = t.getAutores();
		
		for(Autor autor : autoresBd) {
			
			if(!autoresLibro.contains(autor)) {
				
				dropAutor(t, autor);
			}
		}
		
		for(Autor autor : autoresLibro) {
			
			if(!autoresBd.contains(autor)) {
				
				addAutor(t, autor);
			}
		}
	}
	
	/**
	 * Añade un autor como autor de un libro en la tabla pivote.
	 * @param libro
	 * @param autor
	 */
	public void addAutor(Libro libro, Autor autor) {
		
		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("INSERT INTO autor_libro (isbn, cod_autor) VALUES (?, ?)");
			query.setInt(1, libro.getIsbn());
			query.setInt(2, autor.getCodAutor());
			query.executeUpdate();
			Motor.log("Autor añadido correctamente a '" + libro.getTitulo() + "'.");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido añadirse el autor al libro.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}
	
	/**
	 * Elimina un autor como autor de un libro en la tabla pivote.
	 * @param libro
	 * @param autor
	 */
	public void dropAutor(Libro libro, Autor autor) {

		conectar();
		
		try {
			
			PreparedStatement query = (PreparedStatement) conn.prepareStatement("DELETE FROM autor_libro WHERE isbn = ? AND cod_autor = ?");
			query.setInt(1, libro.getIsbn());
			query.setInt(2, autor.getCodAutor());
			query.executeUpdate();
			Motor.log("Autor eliminado correctamente de '" + libro.getTitulo() + "'.");
			
		} catch (SQLException e) {
			
			Motor.log("No ha podido eliminarse el autor del libro.");
			e.printStackTrace();
			
		}finally {
			
			cerrarConexion();
		}
	}
}
