package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Libro {
	
	private int isbn;
	private String titulo;
	private double precio;
	private int stock;
	private int codCategoria;
	private int codEditorial;
	private ArrayList<Autor> autores;
	
	public Libro() {
		
		super();
		autores = new ArrayList<Autor>();
	}
	
	public Libro(int isbn, String titulo, double precio) {
		
		super();
		this.setIsbn(isbn);
		this.setTitulo(titulo);
		this.setPrecio(precio);
	}

	public Libro(ResultSet resultado) {
		
		super();
		
		try {
			
			this.setIsbn(Integer.parseInt(resultado.getString(1)));
			this.setTitulo(resultado.getString(2));
			this.setPrecio(Double.parseDouble(resultado.getString(3)));
			this.setStock(Integer.parseInt(resultado.getString(4)));
			this.setCodCategoria(Integer.parseInt(resultado.getString(5)));
			this.setCodEditorial(Integer.parseInt(resultado.getString(6)));
			
		} catch (NumberFormatException | SQLException e) {
			
			System.out.println("Error al cargar el libro.");
			e.printStackTrace();
		}
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}

	public int getCodEditorial() {
		return codEditorial;
	}

	public void setCodEditorial(int codEditorial) {
		this.codEditorial = codEditorial;
	}
	
	public ArrayList<Autor> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}

	public String toString() {
		return "libro";
	}
}
