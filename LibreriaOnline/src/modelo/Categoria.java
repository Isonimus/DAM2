package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Categoria{
	
	private int codCategoria;
	private String nombre;
	
	public Categoria() {
		
		super();
	}
	
	public Categoria(int codCategoria, String nombre) {
		
		super();
		this.setCodCategoria(codCategoria);
		this.setNombre(nombre);
	}

	public Categoria(ResultSet resultado) {
		
		super();
		
		try {
			
			this.setCodCategoria(Integer.parseInt(resultado.getString(1)));
			this.setNombre(resultado.getString(2));
			
		} catch (NumberFormatException | SQLException e) {
			
			System.out.println("Error al cargar la categoría.");
			e.printStackTrace();
		}
	}

	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		
		return nombre;
	}
}
