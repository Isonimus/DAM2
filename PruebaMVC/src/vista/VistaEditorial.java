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
	
	@Override
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
	
	@Override
	public void insertar() {
		
		System.out.println("Introduce el nombre de la nueva editorial a insertar:");
		String nombre = recogerString();
		mostrarFeedback(getControlador().insertarEditorial(nombre));
	}
	
	@Override
	public void actualizar() {
		
		listar();
		System.out.println("Introduce la ID de la editorial a actualizar:");
		int id = recogerInt();
		System.out.println("Introduce el nuevo nombre de la editorial " + id + ":");
		String nombre = recogerString();
		mostrarFeedback(getControlador().actualizarEditorial(id, nombre));
	}
	
	@Override
	public void eliminar() {
		
		listar();
		System.out.println("Introduce la ID de la editorial a eliminar:");
		int id = recogerInt();
		mostrarFeedback(getControlador().eliminarEditorial(id));
	}
}
