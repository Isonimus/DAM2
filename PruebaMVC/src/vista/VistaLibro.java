package vista;

import java.sql.SQLException;
import java.util.Vector;

import controlador.Controlador;
import modelo.Categoria;
import modelo.Editorial;
import modelo.Libro;

public class VistaLibro extends Vista implements Consultable{
	
	private Vector<Libro> libros;
	private VistaCategoria vistaCat;
	private VistaEditorial vistaEdi;
	
	//TIENE ACCESO A LA VISTA DE CATEGORÍAS, A LA DE AUTORES
	//Y A LA DE EDITORIALES TODO
	
	public VistaLibro(Controlador controlador) {
		
		super(controlador);
	}
	
	public void getMenu() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opción:\n");
		System.out.println("1 - Nuevo libro");
		System.out.println("2 - Actualizar libro");
		System.out.println("3 - Eliminar libro");
		System.out.println("4 - Listar libros");
		System.out.println("0 - Menú principal");
		System.out.println("-------------------ESPERANDO SELECCIÓN----------------");
	}
	
	public void getAccion() {
		
		System.out.println("-----------------MANTENIMIENTO DE LIBROS--------------");
		
		do {
			
			getMenu();
			pedirOpcion();
			
			switch(getOpcion()){
				
				case 1: 
					insertar();
					break;
					
				case 2:
					System.out.println("Actualizar Libro");
					break;
					
				case 3: 
					eliminar();
					break;
					
				case 4: 
					listar();
					break;
					
				case 0:
					//CERRAR EL SCANNER¿?
					break;
					
				default:
					System.out.println("Opción incorrecta.");
					break;
			}
			
		} while(getOpcion() != 0);
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
		System.out.println("-ISBN-----TÍTULO-----CATEGORÍA-----EDITORIAL----------");
		
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
			System.out.println(isbn + " - " + nombreLibro + " - " + categoria + " - " + editorial);
		}
		
		System.out.println("--------------------================------------------");
	}

	@Override
	public void insertar() {
		
		Libro libro;
		vistaCat = new VistaCategoria(getControlador());
		
		System.out.println("Introduce el título del nuevo libro:");
		libro = new Libro(recogerString());
		
		System.out.println("Introduce el ISBN del nuevo libro:");
		libro.setIsbn(recogerInt());
		
		System.out.println("Introduce el precio del nuevo libro:");
		libro.setPrecio(recogerDouble());
		
		System.out.println("Introduce el stock actual del nuevo libro:");
		libro.setStock(recogerInt());
		
		System.out.println("Selecciona la categoría del nuevo libro:");
		vistaCat.listar();
		libro.setCategoria(recogerInt());
		
		System.out.println("Selecciona la editorial del nuevo libro:");
		vistaEdi.listar();
		libro.setEditorial(recogerInt());
		
		//libro.insertar(""); //TODO
	}
	
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar() {
		
		listar();
		System.out.println("Introduce el ISBN del libro a eliminar:");
		String isbn = recogerString();
		mostrarFeedback(getControlador().eliminarLibro(isbn));
	}
}
