package dom;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class PruebaDOM2 {

	public static void main(String[] args) {

		String pathFichero = "C:\\Users\\multi\\Desktop\\DAM 2\\Procesos y Servicios\\EjemplosDOM\\libros.xml";
		System.out.println("Analizando: " + pathFichero + "\n");
		try {
			// Construimos nuestro DocumentBuilder
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// Procesamos el fichero XML y obtenemos nuestro objeto Document
			Document doc = documentBuilder.parse(new InputSource(new FileInputStream(pathFichero)));
			// Buscamos una etiqueta mediante XPath.  
			// Implementaci�n de XPath por defecto en Java  
			Node etiquetaHija = (Node)(XPathFactory.newInstance().newXPath().evaluate("/libros/prestamo", doc, XPathConstants.NODE));  
			if (etiquetaHija!=null){  
			   System.out.println(etiquetaHija.getTextContent());  
			}  

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}