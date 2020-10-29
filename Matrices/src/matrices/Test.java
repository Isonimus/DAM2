package matrices;

public class Test {

	
	public static void main(String[] args) {
		
		int m1Filas, m1Columnas, m2Filas, m2Columnas;
		
		m1Filas = Utilidades.recogerEnteroTeclado("Introduce el nº de filas de la primera matriz:");
		m1Columnas = Utilidades.recogerEnteroTeclado("Introduce el nº de columnas de la primra matriz:");
		m2Filas = Utilidades.recogerEnteroTeclado("Introduce el nº de filas de la segunda matriz:");
		m2Columnas = Utilidades.recogerEnteroTeclado("Introduce el nº de columnas de la segunda matriz:");
		System.out.println("--------------------");
		
		if(m1Columnas != m2Filas) {
			
			System.out.println("Esas matrices no pueden multiplicarse.");
			
		}else {
			
			System.out.println("Matriz A:");
			Matriz m1 = new Matriz(m1Filas, m1Columnas);
			m1.poblarMatriz();
			System.out.println(m1.toString());
			
			System.out.println("--------------------");
			
			System.out.println("Matriz B:");
			Matriz m2 = new Matriz(m2Filas, m2Columnas);
			m2.poblarMatriz();
			System.out.println(m2.toString());
			
			System.out.println("--------------------");
			
			System.out.println("Producto C (C = A * B):");
			System.out.println(Matriz.multiplicarMatrices(m1, m2).toString());
		}
	}
}
