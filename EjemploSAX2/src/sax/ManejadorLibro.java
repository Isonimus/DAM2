package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorLibro extends DefaultHandler {

	private String valor = null;

	private Libro libro;

	public ManejadorLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		// Limpiamos la variable temporal.
		valor = null;

		// Si la etiqueta es libro leemos el atributo isbn
		if (qName.equals("libro")) {
			String isbn = attributes.getValue("isbn");
			// Lo guardamos en el objeto libro
			libro.setIsbn(isbn);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// Guardamos el texto en la variable temporal
		valor = new String(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// Según la etiqueta guardamos el valor leido
		// en una propiedad del objeto libro
		if (qName.equals("titulo")) {
			libro.setTitulo(valor);
		} else if (qName.equals("autor")) {
			libro.setAutor(valor);
		} else if (qName.equals("anyo")) {
			libro.setAnyo(valor);
		} else if (qName.equals("editorial")) {
			libro.setEditorial(valor);
		}

	}

}
