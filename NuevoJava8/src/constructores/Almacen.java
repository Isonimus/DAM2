package constructores;

public class Almacen {

	private String[] articulos;

	public Almacen(String[] articulos) {
		this.articulos = articulos;
	}

	public void imprimirPrimerArticulo() {
		if (articulos.length > 0) {
			System.out.println(articulos[0]);
		}
	}
}
