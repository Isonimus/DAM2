package vista;

import controlador.Controlador;

public class VistaPrincipal extends Vista {
	
	private VistaAutor vistaAutor;
	private VistaLibro vistaLibro;
	private VistaEditorial vistaEditorial;
	private VistaCategoria vistaCategoria;
	
	public VistaPrincipal(Controlador controlador){
		
		super(controlador);
		this.vistaAutor = new VistaAutor(controlador);
		this.vistaLibro = new VistaLibro(controlador);
		this.vistaEditorial = new VistaEditorial(controlador);
		this.vistaCategoria = new VistaCategoria(controlador);
	}
	
	public void getAccion() {
		
		System.out.println("---------------------LIBRER�A ONLINE------------------");
		
		do {
			
			getMenu();
			pedirOpcion();
			
			switch(getOpcion()){
				
				case 1:
					vistaAutor.getAccion();
					break;
					
				case 2:
					vistaLibro.getAccion();
					break;
					
				case 3: 
					vistaEditorial.getAccion();
					break;
					
				case 4: 
					vistaCategoria.getAccion();
					break;
					
				case 0:
					System.out.println("Has elegido cerrar.");
					getControlador().terminar();
					lanzarMensajeDespedida();
					break;
					
				default:
					System.out.println("Opci�n incorrecta.");
					break;
			
			}
			
		} while(getOpcion() != 0);
	}
	
	public void getMenu() {
		
		System.out.println("---------------------MEN� PRINCIPAL-------------------");
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opci�n:\n");
		System.out.println("1 - Mantenimiento Autores");
		System.out.println("2 - Mantenimiento Libros");
		System.out.println("3 - Mantenimiento Editoriales");
		System.out.println("4 - Mantenimiento Categor�as");
		System.out.println("0 - Salir");
		System.out.println("-------------------ESPERANDO SELECCI�N----------------");
	}
	
	private void lanzarMensajeDespedida() {
		
		System.out.println("Cerrando aplicaci�n.");
		System.out.println("-----------------======== ADI�S =========-------------");
	}
}
