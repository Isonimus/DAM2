package sax;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class PruebaSAX1 {

	public static void main(String[] args) {

		String pathFichero = "C:\\Users\\multi\\Desktop\\DAM 2\\Procesos y Servicios\\EjemploSAX1\\employees.xml";
		System.out.println("Analizando: " + pathFichero + "\n");
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			MyHandler manejador = new MyHandler();
			saxParser.parse(pathFichero, manejador);
			// Get Employees list
			List<Employee> employeesList = manejador.getEmpList();
			// print employee information
			for (Employee employee : employeesList) {
				System.out.println(employee);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}