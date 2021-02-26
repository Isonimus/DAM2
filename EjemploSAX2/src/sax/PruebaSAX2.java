package sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class PruebaSAX2 {

	public static void main(String[] args) {

		String pathFichero = "C:\\Eclipse_JavaSE\\eclipse\\workspace\\EjemploSAX2\\libro.xml";
		System.out.println("Analizando: " + pathFichero + "\n");
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			// Creamos nuestro objeto libro vacío
			Libro libro = new Libro();
			ManejadorLibro manejadorLibro = new ManejadorLibro(libro);
			saxParser.parse(pathFichero, manejadorLibro);
			System.out.println(libro.toString());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}