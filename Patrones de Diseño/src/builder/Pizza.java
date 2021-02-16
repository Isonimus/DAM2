package builder;

/**
 * CLASE PRODUCTO
 * @author Iker Laforga
 *
 */
public class Pizza {
	
	private String masa = null;
	private String salsa = null;
	private String relleno = null;
	
	public String toString() {
		return "Pizza con masa " + masa + ", salsa " + salsa + " y relleno de " + relleno + ".";
	}
	
	// SETTERS QUE PERMITIRÁN A LOS CONCRETE BULDERS
	// ASIGNAR VALORES A LA PIZZA
	public void setMasa(String masa) {
		this.masa = masa;
	}

	public void setSalsa(String salsa) {
		this.salsa = salsa;
	}

	public void setRelleno(String relleno) {
		this.relleno = relleno;
	}
}
