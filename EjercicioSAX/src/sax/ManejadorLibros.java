package sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorLibros extends DefaultHandler {

	private List<Libro> libros;
	private Libro libro;
	private StringBuilder data = null;
	
	boolean bTitulo = false;
	boolean bAutor = false;
	boolean bAnyo = false;
	boolean bEditorial = false;

	public ManejadorLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("libro")) {
			// create a new Employee and put it in Map
			String isbn = attributes.getValue("isbn");
			// initialize Employee object and set id attribute
			libro = new Libro();
			libro.setIsbn(isbn);
			// initialize list
			if (libros == null)
				libros = new ArrayList<>();
		} else if (qName.equalsIgnoreCase("titulo")) {
			// set boolean values for fields, will be used in setting Employee variables
			bTitulo = true;
		} else if (qName.equalsIgnoreCase("autor")) {
			bAutor = true;
		} else if (qName.equalsIgnoreCase("anyo")) {
			bAnyo = true;
		} else if (qName.equalsIgnoreCase("editorial")) {
			bEditorial = true;
		}
		// create the data container
		data = new StringBuilder();
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// Guardamos el texto en la variable temporal
		data.append(new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// Según la etiqueta guardamos el valor leido
		// en una propiedad del objeto libro
		if (qName.equals("titulo")) {
			libro.setTitulo(data.toString());
			bTitulo = false;
		} else if (qName.equals("autor")) {
			libro.setAutor(data.toString());
			bAutor = false;
		} else if (qName.equals("anyo")) {
			libro.setAnyo(data.toString());
			bAnyo = false;
		} else if (qName.equals("editorial")) {
			libro.setEditorial(data.toString());
			bEditorial = false;
		}
		
		if (qName.equalsIgnoreCase("Libro")) {
			// add Employee object to list
			libros.add(libro);
		}
	}
}
