package JUnit;
import junit.framework.TestCase;
/**
 * CLASE DE TESTS SOBRE LA CALCULADORA
 * EXTIENDE LA CLASE TESTCASE (NECESARIO IMPRTAR
 * LA LIBRER�A DE JUNIT)
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
	 * SE CREAN LOS TEST ESPEC�FICOS PARA CADA M�TODO
	 * DE LA CLASE QUE SE QUIERE TESTEAR. DEBE LLAMARSE
	 * A ESCENARIO() EN CADA TEST, PARA QUE LA CALCULADORA 
	 * EST� 'MINT'
	 */
	
	public void testSumar() {
		// ASSERT TRUE
		// ASEGURAR QUE EL M�TODO TESTADO ARROJA 
		// EL RESULTADO ESPERADO
		escenario();//INICIALIZA LAS CONDICIONES DE TEST
		assertTrue(calculadora.sumar(2, 2) == (2 + 2));
	}
	
	public void testRestar() {
		// ASSERT EQUALS
		// ASEGURAR QUE EL VALOR QUE ESPERAMOS
		// ES EL QUE ARROJA EL M�TODO A TESTEAR
		escenario(); 
		assertEquals(5, calculadora.restar(7, 2));
	}
	
	public void testMultiplicar() {
		// ASSERT TRUE PARA COMPROBAR QUE UN M�TODO
		// HA SUPERADO UN BUG (POR EJEMPLO, DE REDONDEO)
		escenario();
		assertTrue(calculadora.multiplicar(2, 2) != (5)); //COMPROBAMOS QUE EL OUTPUT														  
	}													  //DEL M�TODO NO ES EL INCORRECTO
	
	public void testDividir() {
		escenario();
		assertEquals(1, calculadora.dividir(2, 2));
	}
	
	public void testTieneResto() {
		escenario();
		assertTrue(calculadora.tieneResto(2, 2) == false); //CONTRA UNA BOOLEANA
	}
}
