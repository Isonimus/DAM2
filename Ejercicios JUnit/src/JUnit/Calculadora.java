package JUnit;

public class Calculadora {
	
	public int sumar(int a, int b) {
		return a+b;
	}
	
	public int restar(int a, int b) {
		return a-b;
	}
	
	public int multiplicar(int a, int b) {
		return a*b;
	}
	
	public int dividir(int a, int b) {
		return a/b;
	}
	
	public boolean tieneResto(int a, int b) {
		//SI LA DIVISIÓN ES ENTERA
		//DEVUELVE 'FALSE'
		return a % b == 0 ? false : true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
