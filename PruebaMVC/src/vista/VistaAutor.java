package vista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import controlador.Controlador;
import modelo.Autor;

public class VistaAutor {
	
	private Controlador controlador;
	private int opcion;
	private Vector<Autor> autores;
	private Scanner scanner;
	
	public VistaAutor(Controlador controlador){
		
		super();
		this.controlador = controlador;
		this.scanner = new Scanner(System.in);
	}
	
	public void getAccion() {
		
		System.out.println("---------------------LIBRER�A ONLINE------------------");
		
		do {
			
			getMenuPrincipal();
			getOpcion();
			
			switch(opcion){
				
				case 1:
					mantenerAutores();
					break;
					
				case 2:
					mantenerLibros();
					break;
					
				case 3: 
					mantenerEditoriales();
					break;
					
				case 4: 
					mantenerCategorias();
					break;
					
				case 0:
					System.out.println("Has elegido cerrar.");
					controlador.terminar();
					lanzarMensajeDespedida();
					break;
					
				default:
					System.out.println("Opci�n incorrecta.");
					break;
			
			}
			
		} while(opcion != 0);
	}
	
	private void getMenuPrincipal() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opci�n:\n");
		System.out.println("1 - Mantenimiento Autores");
		System.out.println("2 - Mantenimiento Libros");
		System.out.println("3 - Mantenimiento Editoriales");
		System.out.println("4 - Mantenimiento Categor�as");
		System.out.println("0 - Salir");
		System.out.println("-------------------ESPERANDO SELECCI�N----------------");
	}
	
	private void getMenuAutor() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opci�n:\n");
		System.out.println("1 - Nuevo autor");
		System.out.println("2 - Actualizar autor");
		System.out.println("3 - Eliminar autor");
		System.out.println("4 - Listar autores");
		System.out.println("0 - Men� principal");
		System.out.println("-------------------ESPERANDO SELECCI�N----------------");
	}
	
	private void getMenuLibro() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opci�n:\n");
		System.out.println("1 - Nuevo libro");
		System.out.println("2 - Actualizar libro");
		System.out.println("3 - Eliminar libro");
		System.out.println("4 - Listar libros");
		System.out.println("0 - Men� principal");
		System.out.println("-------------------ESPERANDO SELECCI�N----------------");
	}
	
	private void getMenuEditorial() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opci�n:\n");
		System.out.println("1 - Nueva editorial");
		System.out.println("2 - Actualizar editorial");
		System.out.println("3 - Eliminar editorial");
		System.out.println("4 - Listar editoriales");
		System.out.println("0 - Men� principal");
		System.out.println("-------------------ESPERANDO SELECCI�N----------------");
	}
	
	private void getMenuCategoria() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opci�n:\n");
		System.out.println("1 - Nueva categor�a");
		System.out.println("2 - Actualizar categor�a");
		System.out.println("3 - Eliminar categor�a");
		System.out.println("4 - Listar categor�as");
		System.out.println("0 - Men� principal");
		System.out.println("-------------------ESPERANDO SELECCI�N----------------");
	}

	private void mantenerAutores() {
		
		System.out.println("-----------------MANTENIMIENTO DE AUTORES-------------");
		
		do {
			
			getMenuAutor();
			getOpcion();
			
			switch(opcion){
				
				case 1: 
					System.out.println("Nuevo Autor");
					break;
					
				case 2:
					System.out.println("Actualizar Autor");
					break;
					
				case 3: 
					System.out.println("Eliminar Autor");
					break;
					
				case 4: 
					listarAutores(); 
					break;
					
				case 0:
					getAccion();
					break;
					
				default:
					System.out.println("Opci�n incorrecta.");
					break;
			}
			
		} while(opcion != 0);
		
	}
	
	private void mantenerLibros() {
		
		System.out.println("-----------------MANTENIMIENTO DE LIBROS--------------");
		
		do {
			
			getMenuLibro();
			getOpcion();
			
			switch(opcion){
				
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
					System.out.println("Listar Libros");
					break;
					
				case 0:
					getAccion();
					break;
					
				default:
					System.out.println("Opci�n incorrecta.");
					break;
			}
			
		} while(opcion != 0);
	}

	private void mantenerEditoriales() {
	
		System.out.println("--------------MANTENIMIENTO DE EDITORIALES------------");
		
		do {
			
			getMenuEditorial();
			getOpcion();
			
			switch(opcion){
				
				case 1: 
					System.out.println("Nueva Editorial");
					break;
					
				case 2:
					System.out.println("Actualizar Editorial");
					break;
					
				case 3: 
					System.out.println("Eliminar Editorial");
					break;
					
				case 4: 
					System.out.println("Listar Editoriales");
					break;
					
				case 0:
					getAccion();
					break;
					
				default:
					System.out.println("Opci�n incorrecta.");
					break;
			}
			
		} while(opcion != 0);
	}

	private void mantenerCategorias() {
	
		System.out.println("---------------MANTENIMIENTO DE CATEGOR�AS------------");
		
		do {
			
			getMenuCategoria();
			getOpcion();
			
			switch(opcion){
				
				case 1: 
					System.out.println("Nueva Categor�a");
					break;
					
				case 2:
					System.out.println("Actualizar Categor�a");
					break;
					
				case 3: 
					System.out.println("Eliminar Categor�a");
					break;
					
				case 4: 
					System.out.println("Listar Categor�as");
					break;
					
				case 0:
					getAccion();
					break;
					
				default:
					System.out.println("Opci�n incorrecta.");
					break;
			}
			
		} while(opcion != 0);
	}
	
	private int getOpcion() {
		
		try {
			
			opcion = scanner.nextInt();
			
		}catch(InputMismatchException e) {
			
			opcion = -1;
			scanner.next();
		}
		
		return opcion;
	}
	
	private void mostrarFeedback(String feedback) {
		
		System.out.println(feedback);
	}
	
	private void listarAutores() {
		
		try {
			
			autores = controlador.obtenerAutores();
			
		} catch (SQLException e) {
			
			mostrarFeedback("Error al obtener los autores.");
			e.printStackTrace();
		}
		
			
		System.out.println("--------------------LISTA DE AUTORES------------------");
		System.out.println("--------------------================------------------");
		
		for(Autor autor : autores) {
			
			int codAutor = autor.getIdAutor();
			String nombreAutor = autor.getNombreAutor();
			System.out.println("C�digo Autor: " + codAutor + " - Nombre autor: " + nombreAutor + ".");
		}
		
		System.out.println("--------------------================------------------");
			
	}
	
	private void lanzarMensajeDespedida() {
		
		System.out.println("Cerrando aplicaci�n.");
		System.out.println("-----------------======== ADI�S =========-------------");
	}

	public Controlador getControlador() {
		
		return controlador;  
	}

	public void setControlador(Controlador controlador) {
		
		this.controlador = controlador;
	}
}
