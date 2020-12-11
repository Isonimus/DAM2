package metodos;

public class Calculadora {

	public int operacionRestoEnteros(int x, int y) {
		return x % y;
	}
	
	public int operacionRestoEnteros(int x, int y, int z) {
		return x % y;
	}

	public static int operacionMultiplicacion(int x, int y) {
		return x * y;
	}

	public static void main(String[] args) {
		
		Calculadora cal = new Calculadora();
		InterfaceOperacion operacion = cal::operacionRestoEnteros;
		System.out.println("Resultado : " + operacion.operacion(5, 2));
		InterfaceOperacion operacion2 = Calculadora::operacionMultiplicacion;
		System.out.println("Resultado2 : " + operacion2.operacion(5, 2));
	}
}
