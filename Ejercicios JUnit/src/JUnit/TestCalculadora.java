package JUnit;
import junit.framework.TestCase;
/**
 * CLASE DE TESTS SOBRE LA CALCULADORA
 * EXTIENDE LA CLASE TESTCASE (NECESARIO IMPRTAR
 * LA LIBRERÍA DE JUNIT)
 * 
 * RUN AS > JUnit Test
 * 
 * @author Iker Laforga
 *
 */
public class TestCalculadora extends TestCase{
	
	private Calculadora calculadora;
	
	public void escenario() { // INICIALIZA 'EL ENTORNO DE PRUEBAS'
		
		calculadora = new Calculadora();
	}
	
	/**
	 * SE CREAN LOS TEST ESPECÍFICOS PARA CADA MÉTODO
	 * DE LA CLASE QUE SE QUIERE TESTEAR. DEBE LLAMARSE
	 * A ESCENARIO() EN CADA TEST, PARA QUE LA CALCULADORA 
	 * ESTÉ 'MINT'
	 */
	
	public void testSumar() {
		// ASSERT TRUE
		// ASEGURAR QUE EL MÉTODO TESTADO ARROJA 
		// EL RESULTADO ESPERADO
		escenario();//INICIALIZA LAS CONDICIONES DE TEST
		assertTrue(calculadora.sumar(2, 2) == (2 + 2));
	}
	
	public void testRestar() {
		// ASSERT EQUALS
		// ASEGURAR QUE EL VALOR QUE ESPERAMOS
		// ES EL QUE ARROJA EL MÉTODO A TESTEAR
		escenario(); 
		assertEquals(5, calculadora.restar(7, 2));
	}
	
	public void testMultiplicar() {
		// ASSERT TRUE PARA COMPROBAR QUE UN MÉTODO
		// HA SUPERADO UN BUG (POR EJEMPLO, DE REDONDEO)
		escenario();
		assertTrue(calculadora.multiplicar(2, 2) != (5)); //COMPROBAMOS QUE EL OUTPUT														  
	}													  //DEL MÉTODO NO ES EL INCORRECTO
	
	public void testDividir() {
		escenario();
		assertEquals(1, calculadora.dividir(2, 2));
	}
	
	public void testTieneResto() {
		escenario();
		assertTrue(calculadora.tieneResto(2, 2) == false); //CONTRA UNA BOOLEANA
	}
}
