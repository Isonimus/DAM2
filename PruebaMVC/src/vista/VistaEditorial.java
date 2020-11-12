package vista;

import java.sql.SQLException;
import java.util.Vector;

import controlador.Controlador;
import modelo.Editorial;

public class VistaEditorial extends Vista implements Consultable{
	
	private Vector<Editorial> editoriales;
	
	public VistaEditorial(Controlador controlador) {
		
		super(controlador);
	}
	
	public void getAccion() {
		
		System.out.println("--------------MANTENIMIENTO DE EDITORIALES------------");
		
		do {
			
			getMenu();
			pedirOpcion();
			
			switch(getOpcion()){
				
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
	
	public void getMenu() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opción:\n");
		System.out.println("1 - Nueva editorial");
		System.out.println("2 - Actualizar editorial");
		System.out.println("3 - Eliminar editorial");
		System.out.println("4 - Listar editoriales");
		System.out.println("0 - Menú principal");
		System.out.println("-------------------ESPERANDO SELECCIÓN----------------");
	}
	
	public void listar() {
		
		try {
			
			editoriales = getControlador().obtenerEditoriales();
			
		} catch (SQLException e) {
			
			mostrarFeedback("Error al obtener las editoriales.");
			e.printStackTrace();
		}
		
			
		System.out.println("------------------LISTA DE EDITORIALES----------------");
		System.out.println("------------------====================----------------");
		
		for(Editorial editorial : editoriales) {
			
			int codEditorial = editorial.getIdEditorial();
			String nombreEditorial = editorial.getNombreEditorial();
			System.out.println("Código Editorial: " + codEditorial + " - Nombre editorial: " + nombreEditorial + ".");
		}
		
		System.out.println("--------------------================------------------");
	}
	
}
