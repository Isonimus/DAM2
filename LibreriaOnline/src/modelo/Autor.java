package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Autor implements Comparable<Autor>{

	private int codAutor;
	private String nombre;
	
	public Autor() {
		
		super();
	}
	
	public Autor(int codAutor, String nombre) {
		
		super();
		this.setCodAutor(codAutor);
		this.setNombre(nombre);
	}

	public Autor(ResultSet resultado) {
		
		super();
		
		try {
			
			this.setCodAutor(Integer.parseInt(resultado.getString(1)));
			this.setNombre(resultado.getString(2));
			
		} catch (NumberFormatException | SQLException e) {
			
			System.out.println("Error al cargar el autor.");
			e.printStackTrace();
		}
	}

	public int getCodAutor() {
		return codAutor;
	}

	public void setCodAutor(int codAutor) {
		this.codAutor = codAutor;
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

	@Override
	public int compareTo(Autor o) {
		
		if(this.getCodAutor() == o.getCodAutor()){
			
			return 0;
			
		}else if(this.getCodAutor() < o.getCodAutor()) {
			
			return -1;
		}else {
			
			return 1;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codAutor;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (codAutor != other.codAutor)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
