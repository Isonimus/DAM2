package vista;

import java.sql.SQLException;
import java.util.Vector;

import controlador.Controlador;
import modelo.Autor;

public class VistaAutor extends Vista implements Consultable{
	
	private Vector<Autor> autores;
	
	public VistaAutor(Controlador controlador) {
		
		super(controlador);
	}
	
	public void getAccion() {
		
		System.out.println("-----------------MANTENIMIENTO DE AUTORES-------------");
		
		do {
			
			getMenu();
			pedirOpcion();
			
			switch(getOpcion()){
				
				case 1: 
					insertar();
					break;
					
				case 2:
					System.out.println("Actualizar Autor");
					break;
					
				case 3: 
					eliminar();
					break;
					
				case 4: 
					listar(); 
					break;
					
				case 0:
					//getAccion();
					break;
					
				default:
					System.out.println("Opción incorrecta.");
					break;
			}
			
		} while(getOpcion() != 0);
		
	}
	
	public void getMenu() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opción:\n");
		System.out.println("1 - Nuevo autor");
		System.out.println("2 - Actualizar autor");
		System.out.println("3 - Eliminar autor");
		System.out.println("4 - Listar autores");
		System.out.println("0 - Menú principal");
		System.out.println("-------------------ESPERANDO SELECCIÓN----------------");
	}
	
	public void listar() {
		
		try {
			
			autores = getControlador().obtenerAutores();
			
		} catch (SQLException e) {
			
			mostrarFeedback("Error al obtener los autores.");
			e.printStackTrace();
		}
		
		System.out.println("--------------------LISTA DE AUTORES------------------");
		System.out.println("--------------------================------------------");
		
		for(Autor autor : autores) {
			
			int codAutor = autor.getIdAutor();
			String nombreAutor = autor.getNombreAutor();
			System.out.println("Código Autor: " + codAutor + " - Nombre autor: " + nombreAutor + ".");
		}
		
		System.out.println("--------------------================------------------");
	}
	
	public void insertar() {
		
		System.out.println("Introduce el nombre del nuevo autor a insertar:");
		String nombre = recogerString();
		mostrarFeedback(getControlador().insertarAutor(nombre));
	}
	
	public void eliminar() {
		
		listar();
		System.out.println("Introduce la ID del autor a eliminar:");
		int id = recogerInt();
		mostrarFeedback(getControlador().eliminarAutor(id));
	}
	
}
