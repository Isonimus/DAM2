package constructores;

public class PruebaFactoria {

	public static void main(String[] args) {
		FactoriaAlmacen factoria = Almacen::new;
		Almacen almacen1 = factoria.getAlmacen((new String[] { "Sofa", "Sillon", "Armario" }));
		almacen1.imprimirPrimerArticulo();
		Almacen almacen2 = factoria.getAlmacen((new String[] { "Libro", "Juguete", "Balón" }));
		almacen2.imprimirPrimerArticulo();
	}
}
