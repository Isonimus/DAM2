package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Editorial {
	
	private int codEditorial;
	private String nombre;
	
	public Editorial() {
		super();
	}
	
	public Editorial(int codEditorial, String nombre) {
		
		super();
		this.setCodEditorial(codEditorial);
		this.setNombre(nombre);
	}

	public Editorial(ResultSet resultado) {

		try {
			
			this.setCodEditorial(Integer.parseInt(resultado.getString(1)));
			this.setNombre(resultado.getString(2));
			
		} catch (NumberFormatException | SQLException e) {
			
			System.out.println("Error al cargar la editorial.");
			e.printStackTrace();
		} 
	}

	public int getCodEditorial() {
		return codEditorial;
	}

	public void setCodEditorial(int codEditorial) {
		this.codEditorial = codEditorial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String toString() {
		return "editorial";
	}
}
