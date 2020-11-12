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
		System.out.println("Elige una opci�n:\n");
		System.out.println("1 - Nueva categor�a");
		System.out.println("2 - Actualizar categor�a");
		System.out.println("3 - Eliminar categor�a");
		System.out.println("4 - Listar categor�as");
		System.out.println("0 - Men� principal");
		System.out.println("-------------------ESPERANDO SELECCI�N----------------");
	}

	public void getAccion() {
	
		System.out.println("---------------MANTENIMIENTO DE CATEGOR�AS------------");
		
		do {
			
			getMenu();
			pedirOpcion();
			
			switch(getOpcion()){
				
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
					listar();
					break;
					
				case 0:
					//CERRAR EL  SCANNER�?
					break;
					
				default:
					System.out.println("Opci�n incorrecta.");
					break;
			}
			
		} while(getOpcion() != 0);
	}
	
	public void listar() {
		
		try {
			
			categorias = getControlador().obtenerCategorias();
			
		} catch (SQLException e) {
			
			mostrarFeedback("Error al obtener las categor�as.");
			e.printStackTrace();
		}
		
			
		System.out.println("-------------------LISTA DE CATEGOR�AS----------------");
		System.out.println("-------------------===================----------------");
		
		for(Categoria categoria : categorias) {
			
			int codCategoria = categoria.getIdCategoria();
			String nombreCategoria = categoria.getNombreCategoria();
			System.out.println("C�digo Categor�a: " + codCategoria + " - Nombre categor�a: " + nombreCategoria + ".");
		}
		
		System.out.println("--------------------================------------------");
	}
	
}
