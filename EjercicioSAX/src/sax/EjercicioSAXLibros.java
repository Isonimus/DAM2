package sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class EjercicioSAXLibros {

	public static void main(String[] args) {

		String pathFichero = "C:\\Users\\multi\\eclipseEE-workspace\\EjercicioSAX\\libros.xml";
		System.out.println("Analizando: " + pathFichero + "\n");
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			// Creamos nuestra lista de libros vacia
			List<Libro> libros = new ArrayList<Libro>();
			ManejadorLibros manejadorLibro = new ManejadorLibros(libros);
			saxParser.parse(pathFichero, manejadorLibro);
			for(Libro libro : libros) {
				System.out.println(libro.toString());
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}