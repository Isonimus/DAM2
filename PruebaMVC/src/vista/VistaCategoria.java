package vista;

import java.sql.SQLException;
import java.util.Vector;

import controlador.Controlador;
import modelo.Categoria;

public class VistaCategoria extends Vista implements Consultable{
	
	private Vector<Categoria> categorias;
	
	public VistaCategoria(Controlador controlador) {
		
		super(controlador);
	}
	
	public void getMenu() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opción:\n");
		System.out.println("1 - Nueva categoría");
		System.out.println("2 - Actualizar categoría");
		System.out.println("3 - Eliminar categoría");
		System.out.println("4 - Listar categorías");
		System.out.println("0 - Menú principal");
		System.out.println("-------------------ESPERANDO SELECCIÓN----------------");
	}

	public void getAccion() {
	
		System.out.println("---------------MANTENIMIENTO DE CATEGORÍAS------------");
		
		do {
			
			getMenu();
			pedirOpcion();
			
			switch(getOpcion()){
				
				case 1: 
					System.out.println("Nueva Categoría");
					break;
					
				case 2:
					System.out.println("Actualizar Categoría");
					break;
					
				case 3: 
					System.out.println("Eliminar Categoría");
					break;
					
				case 4: 
					listar();
					break;
					
				case 0:
					//CERRAR EL  SCANNER¿?
					break;
					
				default:
					System.out.println("Opción incorrecta.");
					break;
			}
			
		} while(getOpcion() != 0);
	}
	
	public void listar() {
		
		try {
			
			categorias = getControlador().obtenerCategorias();
			
		} catch (SQLException e) {
			
			mostrarFeedback("Error al obtener las categorías.");
			e.printStackTrace();
		}
		
			
		System.out.println("-------------------LISTA DE CATEGORÍAS----------------");
		System.out.println("-------------------===================----------------");
		
		for(Categoria categoria : categorias) {
			
			int codCategoria = categoria.getIdCategoria();
			String nombreCategoria = categoria.getNombreCategoria();
			System.out.println("Código Categoría: " + codCategoria + " - Nombre categoría: " + nombreCategoria + ".");
		}
		
		System.out.println("--------------------================------------------");
	}
	
}
