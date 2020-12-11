package streams;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;
import conduccion.Dni;
import conduccion.Persona;

public class DemoStreams {

	public static void main(String[] args) {
		
		List<Persona> listPersonas = new ArrayList<>();
		
		//CARGAR LA LISTA DE PERSONAS A LO BURRO
		Long cantidad = (long) (Math.pow(10, 7));
		String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int a = 0;
		int z = 25;
		
		Instant start = Instant.now();
		
		for(int i = 0; i < cantidad; i++) {
			
			char letra = alfabeto.charAt((int) ((Math.random() * (z - a)) + a));
			String numero = String.valueOf((int) ((Math.random() * (99999999 - 1)) + 1));
			
			Persona persona = new Persona(i, "Persona", "Apellido1", new Dni(numero, letra));
			listPersonas.add(persona);
		}
		
		/*
		Persona persona1 = new Persona(1, "Juan", "Lopez", new Dni("223344556", 'A'));
		listPersonas.add(persona1);
		Persona persona2 = new Persona(2, "Olatz", "Bilbao", new Dni("223344557", 'B'));
		listPersonas.add(persona2);
		Persona persona3 = new Persona(3, "Marta", "Igartua", new Dni("223344558", 'C'));
		listPersonas.add(persona3);
		Persona persona4 = new Persona(4, "Xabi", "Martínez", new Dni("223344559", 'D'));
		listPersonas.add(persona4);
		*/
		
		// Crear un Stream a partir de una colección (de 2 maneras)
		// También se puede crear de otras formas, por ejemplo desde un Array
		Stream streamPersonas = Stream.of(listPersonas); //FORMA 1
		streamPersonas = listPersonas.stream();		     //FORMA 2
		
		System.out.println("--------");
		System.out.println("Listado 1");
		System.out.println("--------");
		// Recorrer e imprimir las personas. Recorrer usa Consumer
		Consumer<Persona> consumer = persona -> System.out.println(persona);
		streamPersonas.forEach(consumer);
		
		System.out.println("--------");
		System.out.println("Listado 2");
		System.out.println("--------");
		// Filtrar y recorrer. Filtrar usa Predicate
		Predicate<Persona> predicate = (Persona persona) -> {return persona.getDni().getLetra() > 'A'; };
		listPersonas.stream().filter(predicate).forEach(persona -> System.out.println(persona)); //CONSUMER
		
		System.out.println("--------");
		System.out.println("Listado 3");
		System.out.println("--------");
		// Ordenar. Usa Comparator
		Comparator<Persona> comparator = (p1, p2) -> ((Persona) p1).getNombre().compareTo(((Persona) p2).getNombre());
		listPersonas.stream().filter(persona -> persona.getDni().getLetra() > 'A').sorted(comparator).forEach(persona -> System.out.println(persona));
		
		System.out.println("--------");
		System.out.println("Listado 4");
		System.out.println("--------");
		// Hacer un mapeo. Usa Function
		ToIntFunction<Persona> function = p -> Integer.parseInt(((Persona) p).getDni().getNumero());
		listPersonas.stream().mapToInt(function).forEach(numero -> System.out.println(numero));
		
		System.out.println("--------");
		System.out.println("Listado 4");
		System.out.println("--------");
		// Hacer una operación, por ejemplo el minimo. Usa Function
		int minimo = listPersonas.stream().mapToInt(p -> Integer.parseInt(p.getDni().getNumero())).min().getAsInt();
		System.out.println(minimo);
		
		//CÁLCULO DEL TIEMPO DE EJECUCIÓN
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		//float toSec = timeElapsed/1000;
        System.out.println("\nTiempo de ejecución: " + timeElapsed + " ms.");
	}
}
