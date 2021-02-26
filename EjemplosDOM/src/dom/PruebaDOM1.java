package dom;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class PruebaDOM1 {

	public static void main(String[] args) {

		String pathFichero = "C:\\Users\\multi\\Desktop\\DAM 2\\Procesos y Servicios\\EjemplosDOM\\libros.xml";
		System.out.println("Analizando: " + pathFichero + "\n");
		try {
			// Construimos nuestro DocumentBuilder
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// Procesamos el fichero XML y obtenemos nuestro objeto Document
			Document doc = documentBuilder.parse(new InputSource(new FileInputStream(pathFichero)));
			// Obtenemos la etiqueta raiz
			Element elementRaiz = doc.getDocumentElement();
			System.out.println(elementRaiz.getNodeName());
			// Iteramos sobre sus hijos
			NodeList hijos = elementRaiz.getChildNodes();
			for (int i = 0; i < hijos.getLength(); i++) {
				Node nodo = hijos.item(i);
				if (nodo instanceof Element) {
					System.out.println(nodo.getNodeName());
				}
			}
			// Buscamos una etiqueta dentro del XML
			NodeList listaNodos = doc.getElementsByTagName("libro");
			for (int i = 0; i < listaNodos.getLength(); i++) {
				Node nodo = listaNodos.item(i);
				if (nodo instanceof Element) {
					System.out.println(nodo.getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}