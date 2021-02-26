package dom;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class PruebaDOM3 {

	public static void main(String[] args) {

		String pathFichero = "C:\\Users\\multi\\Desktop\\DAM 2\\Procesos y Servicios\\EjemplosDOM\\libros.xml";
		System.out.println("Analizando: " + pathFichero + "\n");
		try {
			// Construimos nuestro DocumentBuilder
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// Procesamos el fichero XML y obtenemos nuestro objeto Document
			Document doc = documentBuilder.parse(new InputSource(new FileInputStream(pathFichero)));
			// Buscamos una etiqueta mediante XPath.  
			// Implementación de XPath por defecto en Java  
			Node etiquetaHija = (Node)(XPathFactory.newInstance().newXPath().evaluate("/libros/prestamo", doc, XPathConstants.NODE));  
			if (etiquetaHija!=null){  
			   System.out.println(etiquetaHija.getTextContent());  
			}  
			//
			// Añadimos una nueva etiqueta al documento  
			// Primero creamos la etiqueta (element)  
			Element nuevaEtiqueta = doc.createElement("nuevaEtiqueta");  
			// Añadimos atributos  
			nuevaEtiqueta.setAttribute("atributoNuevo", "Es un nuevo atributo");  
			// Añadimos contenido  
			nuevaEtiqueta.setTextContent("Contenido dentro de la nueva etiqueta");  
			// después se la añadimos como hija a una etiqueta ya existente  
			etiquetaHija.appendChild(nuevaEtiqueta);  
			//
			System.out.println(etiquetaHija.getTextContent());


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}