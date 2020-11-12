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
	
	protected int pedirOpcion() {
		
		try {
			
			setOpcion(getScanner().nextInt());
			
		}catch(InputMismatchException e) {
			
			setOpcion(-1);
			getScanner().next();
		}
		
		return getOpcion();
	}
	
	protected int recogerInt(){
		
		int integer = getScanner().nextInt();
		return integer;
	}
	
	protected String recogerString(){
		
		String string = getScanner().next();
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
