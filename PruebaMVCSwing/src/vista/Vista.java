package vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import controlador.Controlador;

public abstract class Vista implements Selectable{
	
	private Controlador controlador;
	private int opcion;
	private Scanner scanner;
	
	public Vista(Controlador controlador) {
		
		super();
		this.setControlador(controlador);
		this.setScanner(new Scanner(System.in));
	}
	
	protected int pedirOpcion(String mensaje) {
		
		try {
			
			setOpcion(recogerInt(mensaje));
			
		}catch(InputMismatchException e) {
			
			setOpcion(-1);
			getScanner().next();
		}
		
		return getOpcion();
	}
	
	protected int recogerInt(String mensaje){
		
		int integer = Integer.MIN_VALUE;
		boolean correcto = false;
		
		while(!correcto) {
			
			System.out.println(mensaje);
			
			try {
				
				integer = getScanner().nextInt();
				getScanner().nextLine(); //LIMPIAR EL BUFFER
				
				if(integer >= 0) {
					correcto = true;
				}else {
					mostrarFeedback("Debes introducir un valor mayor que cero.");
				}
				
			}catch(InputMismatchException e) {
				
				mostrarFeedback("Debes introducir un valor numérico.");
				getScanner().next();
			}
		}
		
		return integer;
	}
	
	protected double recogerDouble(String mensaje){ 
		
		double doble = Double.MIN_VALUE;
		boolean correcto = false;
		
		while(!correcto) {
			
			System.out.println(mensaje);
			
			try {
				
				doble = getScanner().nextDouble();
				getScanner().nextLine(); //LIMPIAR EL BUFFER
				
				if(doble >= 0) {
					correcto = true;
				}else {
					mostrarFeedback("Debes introducir un valor mayor que cero.");
				}
				
			}catch(InputMismatchException e) {
				
				mostrarFeedback("Debes introducir un valor numérico.");
				getScanner().next();
			}
		}
		
		return doble;
	}
	
	protected String recogerString(){ //SANITIZAR TODO
		
		String string = getScanner().nextLine();
		return string;
	}
	
	protected void mostrarFeedback(String feedback) {
		
		System.out.println(feedback);
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public int getOpcion() {
		return opcion;
	}

	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}
