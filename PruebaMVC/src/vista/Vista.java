package vista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import controlador.Controlador;

public class Vista {
	
	private Controlador controlador;
	private int opcion;
	private ResultSet resultado;
	private Scanner scanner;
	
	public Vista(Controlador controlador){
		
		super();
		this.controlador = controlador;
		this.scanner = new Scanner(System.in);
	}
	
	public void getAccion() {
		
		System.out.println("---------------------LIBRERÍA ONLINE------------------");
		
		do {
			
			getMenu();
			getOpcion();
			
			switch(opcion){
				
				case 1:
					listarAutores(); //mantenerAutores();
					break;
					
				case 0:
					System.out.println("Has elegido cerrar.");
					controlador.terminar();
					break;
					
				default:
					System.out.println("Opción incorrecta.");
					break;
			
			}
			
		} while(opcion != 0);
		//SWITCH CON EL RESULTADO DE INPUT
		//ACTUAR
		System.out.println("Cerrando aplicación.");
	}
	
	private void getMenu() {
		
		System.out.println("------------------------OPCIONES----------------------");
		System.out.println("Elige una opción:");
		System.out.println("1 - Listar Autores");
		System.out.println("0 - Salir");
		System.out.println("-------------------ESPERANDO SELECCIÓN----------------");
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
		
		resultado = controlador.obtenerAutores();
		
		try {
			
			System.out.println("-------------------LISTA DE AUTORES-------------------");
			
			while(resultado.next()) {
				
				int codAutor = resultado.getInt(1);
				String nombreAutor = resultado.getString(2);
				System.out.println("Código Autor: " + codAutor + ". Nombre autor: " + nombreAutor + ".");
			}
			
			System.out.println("--------------------================------------------");
			
		} catch (SQLException e) {
			
			System.out.println("ERROR: no han podido visualizarse los autores.");
			e.printStackTrace();
		}
	}

	public Controlador getControlador() {
		
		return controlador;  
	}

	public void setControlador(Controlador controlador) {
		
		this.controlador = controlador;
	}
}
