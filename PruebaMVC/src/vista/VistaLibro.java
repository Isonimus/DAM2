package vista;

import java.sql.SQLException;
import java.util.Vector;

import controlador.Controlador;
import modelo.Libro;

public class VistaLibro extends Vista implements Consultable{
	
	
	private Vector<Libro> libros;
	
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
					System.out.println("Nuevo Libro");
					break;
					
				case 2:
					System.out.println("Actualizar Libro");
					break;
					
				case 3: 
					System.out.println("Eliminar Libro");
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
		
		try {
			
			libros = getControlador().obtenerLibros();
			
		} catch (SQLException e) {
			
			mostrarFeedback("Error al obtener los libros.");
			e.printStackTrace();
		}
		
			
		System.out.println("---------------------LISTA DE LIBROS------------------");
		System.out.println("---------------------===============------------------");
		
		for(Libro libro : libros) {
			
			String isbn = libro.getIsbn();
			String nombreLibro = libro.getNombreLibro();
			System.out.println("Código Libro: " + isbn + " - Nombre libro: " + nombreLibro + ".");
		}
		
		System.out.println("--------------------================------------------");
	}

	@Override
	public void insertar() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
}
