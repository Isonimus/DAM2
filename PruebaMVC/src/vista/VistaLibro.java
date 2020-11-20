package vista;

import java.sql.SQLException;
import java.util.Vector;

import controlador.Controlador;
import modelo.Autor;
import modelo.Categoria;
import modelo.Editorial;
import modelo.Libro;

public class VistaLibro extends Vista implements Consultable{
	
	private Vector<Libro> libros;
	private VistaCategoria vistaCat;
	private VistaEditorial vistaEdi;
	private VistaAutor vistaAut;
	private boolean editando = false;
	
	//TIENE ACCESO A LA VISTA DE CATEGOR�AS, A LA DE AUTORES
	//Y A LA DE EDITORIALES TODO
	
	public VistaLibro(Controlador controlador) {
		
		super(controlador);
		vistaCat = new VistaCategoria(getControlador());
		vistaEdi = new VistaEditorial(getControlador());
		vistaAut = new VistaAutor(getControlador());
	}
	
	public void getMenu() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opci�n:\n");
		System.out.println("1 - Nuevo libro");
		System.out.println("2 - Actualizar libro");
		System.out.println("3 - Eliminar libro");
		System.out.println("4 - Listar libros");
		System.out.println("0 - Men� principal");
	}
	
	public void getMenuActualizar() {
		
		System.out.println("--------------------ACTUALIZAR LIBRO------------------");
		System.out.println("Elige una opci�n:\n");
		System.out.println("1 - Actualizar ISBN");
		System.out.println("2 - Actualizar t�tulo");
		System.out.println("3 - Actualizar precio");
		System.out.println("4 - Actualizar stock");
		System.out.println("5 - Actualizar categor�a");
		System.out.println("6 - Actualizar editorial");
		System.out.println("7 - Actualizar autores");
		System.out.println("0 - volver");
	}
	
	public void getMenuAutores() {
		
		System.out.println("-------------------ACTUALIZAR AUTORES-----------------");
		System.out.println("Elige una opci�n:\n");
		System.out.println("1 - A�adir autor");
		System.out.println("2 - Eliminar Autor");
		System.out.println("0 - Volver");
	}
	
	public void getAccion() {
		
		System.out.println("-----------------MANTENIMIENTO DE LIBROS--------------");
		
		do {
			
			getMenu();
			pedirOpcion("-------------------ESPERANDO SELECCI�N----------------");
			
			switch(getOpcion()){
				
				case 1: 
					insertar();
					break;
					
				case 2:
					actualizar();
					break;
					
				case 3: 
					eliminar();
					break;
					
				case 4: 
					listar();
					break;
					
				case 0:
					//CERRAR EL SCANNER�?
					break;
					
				default:
					System.out.println("Opci�n incorrecta.");
					break;
			}
			
		} while(getOpcion() != 0);
	}
	
	public String ajustarString(String string, int longitud) {
		
		for(int i = string.length(); i < longitud; i++) {
			
			string = string + " ";
		}
		
		return string;
	}
	
	@Override
	public void listar() {
		
		Vector<Categoria> categorias = null;
		Vector<Editorial> editoriales = null;
		//Vector<Autor> autores;
		
		try {
			
			libros = getControlador().obtenerLibros();
			categorias = getControlador().obtenerCategorias();
			editoriales = getControlador().obtenerEditoriales(); 
			
		} catch (SQLException e) {
			
			mostrarFeedback("Error al obtener los libros.");
			e.printStackTrace();
		}
		
		System.out.println("---------------------LISTA DE LIBROS------------------");
		System.out.println("---------------------===============------------------");
		System.out.println("-ISBN-----T�TULO-----CATEGOR�A-----EDITORIAL----------");
		
		int maxLength = Integer.MIN_VALUE;
		
		for(Libro libro : libros) {
			
			if(libro.getNombreLibro().length() > maxLength) {
				
				maxLength = libro.getNombreLibro().length();
			}
		}
		
		for(Libro libro : libros) {
			
			String categoria = null;
			String editorial = null;
			
			for(Categoria categ : categorias) {
				
				if(categ.getIdCategoria() == libro.getCategoria()) {
					
					categoria = categ.getNombreCategoria();
				}
			}
			
			for(Editorial edit : editoriales) {
				
				if(edit.getIdEditorial() == libro.getEditorial()) {
					
					editorial = edit.getNombreEditorial();
				}
			}
			
			int isbn = libro.getIsbn();
			String nombreLibro = libro.getNombreLibro();
			System.out.println(isbn + " - " + ajustarString(nombreLibro, maxLength) + " - " + categoria + " - " + editorial);
		}
		
		System.out.println("--------------------================------------------");
	}
	
	public void listarAutores(int isbn) {
		
		Vector<Autor> autores;
		System.out.println("--------------------AUTORES ASIGNADOS-----------------");
		
		try {
			
			autores = getControlador().obtenerAutoresLibro(isbn);
			
			if(autores.size() > 0) {
				
				for(Autor autor : autores) {
					
					System.out.println(autor.getIdAutor() + " - " + autor.getNombreAutor());
				}
				
			}else {
				
				System.out.println("El libro " + isbn + " no tiene autores asignados.");
			}
			
			System.out.println("--------------------================------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR: no se han podido obtener los autores del libro.");
		}
	}

	@Override
	public void insertar() {
		
		Libro libro;
		
		System.out.println("-----------------------NUEVO LIBRO--------------------");
		System.out.println("Introduce el t�tulo del nuevo libro:");
		libro = new Libro(recogerString());
		
		libro.setIsbn(recogerInt("Introduce el ISBN del nuevo libro:")); //COMPROBAR QUE NO EXISTA
		
		libro.setPrecio(recogerDouble("Introduce el precio del nuevo libro:"));
		
		libro.setStock(recogerInt("Introduce el stock actual del nuevo libro:"));
		
		vistaCat.listar();
		libro.setCategoria(recogerInt("Selecciona la categor�a del nuevo libro:"));
		
		vistaEdi.listar();
		libro.setEditorial(recogerInt("Selecciona la editorial del nuevo libro:"));
		
		mostrarFeedback(getControlador().insertarLibro(libro));
	}
	
	@Override
	public void actualizar() {
		
		editando = true;
		listar();
		//System.out.println("--------------------ACTUALIZAR LIBRO------------------");
		int isbn = recogerInt("Introduce el ISBN del libro a actualizar:");
		
		do {
			
			getMenuActualizar();
			
			switch(recogerInt("-------------------ESPERANDO SELECCI�N----------------")){
			
				case 1: 
					System.out.println("ISBN");
					break;
					
				case 2:
					System.out.println("Introduce el nuevo t�tulo del libro:");
					String titulo = recogerString();
					System.out.println(getControlador().actualizarLibro(isbn, "titulo", titulo));
					break;
					
				case 3: 
					int precio = recogerInt("Introduce el nuevo precio del libro:");
					System.out.println(getControlador().actualizarLibro(isbn, "precio", Integer.toString(precio)));
					break;
					
				case 4: 
					int stock = recogerInt("Introduce el nuevo stock del libro:");
					System.out.println(getControlador().actualizarLibro(isbn, "stock", Integer.toString(stock)));
					break;
					
				case 5: 
					vistaCat.listar();
					int categoria = recogerInt("Introduce la nueva categor�a del libro:");
					System.out.println(getControlador().actualizarLibro(isbn, "cod_categoria", Integer.toString(categoria)));
					break;
					
				case 6: 
					vistaEdi.listar();
					int editorial = recogerInt("Introduce la nueva editorial del libro:");
					System.out.println(getControlador().actualizarLibro(isbn, "cod_editorial", Integer.toString(editorial)));
					break;
					
				case 7: 
					actualizarAutores(isbn);
					break;
					
				case 0:
					editando = false;
					break;
					
				default:
					System.out.println("Opci�n incorrecta.");
					break;
			}
			
		}while(editando);
	}
	
	public void actualizarAutores(int isbn) {
		
		int idAutor;
		
		do {
			
			getMenuAutores();
			
			switch(recogerInt("-------------------ESPERANDO SELECCI�N----------------")){
			
				case 1:
					vistaAut.listar();
					idAutor = recogerInt("Introduce el c�digo del autor a a�adir:");
					System.out.println(getControlador().addAutorLibro(idAutor, isbn));
					break;
					
				case 2:
					listarAutores(isbn);
					idAutor = recogerInt("Introduce el c�digo del autor a eliminar:");
					System.out.println(getControlador().dropAutorLibro(idAutor, isbn));
					break;
					
				case 0:
					editando = false;
					break;
			
				default:
					System.out.println("Opci�n incorrecta.");
					break;
			}
			
		}while(editando);
		editando = true;
	}

	@Override
	public void eliminar() {
		
		listar();
		System.out.println("---------------------ELIMINAR LIBRO-------------------");
		int isbn = recogerInt("Introduce el ISBN del libro a eliminar:");
		mostrarFeedback(getControlador().eliminarLibro(isbn));
	}
}
