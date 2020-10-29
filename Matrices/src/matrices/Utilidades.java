package matrices;

import java.util.Scanner;

public class Utilidades {
	
	public static int recogerEnteroTeclado(String mensaje) {
		
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		System.out.print(mensaje);
		int numero = Integer.parseInt(teclado.nextLine());
		return numero;
	}
}
