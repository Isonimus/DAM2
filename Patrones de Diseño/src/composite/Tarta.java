package composite;

public class Tarta {
	
	public static void main(String[] args) {
		
		Compuesto tarta = new Compuesto("Tarta");
		Compuesto bizcocho = new Compuesto("Bizcocho");
		Compuesto glaseado = new Compuesto("Glaseado");
		Compuesto relleno = new Compuesto("Relleno");
		
		bizcocho.anadir(new Hoja("Huevo", 155));
		bizcocho.anadir(new Hoja("Azúcar", 387));
		bizcocho.anadir(new Hoja("Harina", 364));
		bizcocho.anadir(new Hoja("Mantequilla", 717));
		
		glaseado.anadir(new Hoja("Azúcar glass", 389));
		glaseado.anadir(new Hoja("Mantequilla", 717));
		glaseado.anadir(new Hoja("Clara de huevo", 60));
		
		relleno.anadir(new Hoja("Fresa", 33));
		relleno.anadir(new Hoja("Nata", 196));
		
		tarta.anadir(bizcocho);
		tarta.anadir(glaseado);
		tarta.anadir(relleno);
		//tarta.eliminar(relleno);
		
		System.out.println(tarta.getCaloriasTotales() + " calorías.");
		System.out.println("Te vas a poner gordo :)");
	}
}
