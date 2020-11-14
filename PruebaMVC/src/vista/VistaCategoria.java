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
					//CERRAR EL  SCANNER¿?
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
	
	@Override
	public void insertar() {
		
		System.out.println("Introduce el nombre de la nueva categoría a insertar:");
		String nombre = recogerString();
		mostrarFeedback(getControlador().insertarCategoria(nombre));
	}
	
	@Override
	public void actualizar() {
		
		listar();
		System.out.println("Introduce la ID de la categoría a actualizar:");
		int id = recogerInt();
		System.out.println("Introduce el nuevo nombre de la categoría " + id + ":");
		String nombre = recogerString();
		mostrarFeedback(getControlador().actualizarCategoria(id, nombre));
	}
	
	@Override
	public void eliminar() {
		
		listar();
		System.out.println("Introduce la ID de la categoría a eliminar:");
		int id = recogerInt();
		mostrarFeedback(getControlador().eliminarCategoria(id));
	}
	
}
