package operaciones;

import calculadora.Calculadora;
import calculadora.CalculadoraService;
import calculadora.CalculadoraServiceLocator;

public class TestCalculadora {

	public static void main(String[] args) throws Exception{
		
		CalculadoraService calculadoraService = new CalculadoraServiceLocator();
		Calculadora calculadora = calculadoraService.getCalculadora();
		int a = 2;
		int b = 7;
		int s = calculadora.sumar(a, b);
		int r = calculadora.restar(a, b);
		
		System.out.println(s);
		System.out.println(r);
	}
}
