package ejemplo;

public class Test {

	public static void main(String[] args) {
		double x = 9;
		double y = 5;
		OperacionInterface operacionSuma = (double a, double b) -> {
			return a + b;
		};
		double z = Calculadora.realizaOperacion(x, y, operacionSuma);
		System.out.println("z: " + z);

	}

}
