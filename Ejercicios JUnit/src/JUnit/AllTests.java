package JUnit;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * CLASE QUE AGRUPA TODOS LOS TESTS
 * PARA NOTENER QUE CORRERLOS A MANO
 * UNO A UNO (EN CASO DE QUE HUBIESE MÁS
 * DE UNO)
 * 
 * RUN AS > JUnit Test
 * @author Iker Laforga
 *
 */

public class AllTests {
   public static Test suite() {
      TestSuite suite = new TestSuite("Tests para la app Calculadora");
      suite.addTestSuite(TestCalculadora.class);
      return suite;
   }
} 
