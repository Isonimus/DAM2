package ejemplo;

public class Calculadora {
	public static double realizaOperacion(double x, double y, OperacionInterface op) {
		return op.operacion(x, y);
	}

}
