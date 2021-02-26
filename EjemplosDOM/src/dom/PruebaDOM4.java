package dom;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PruebaDOM4 {

	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost/empresa";
	private static String USER = "root";
	private static String PASSWORD = "admin";

	public static List<Empleado> listarTodosEmpleados() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		String sql = "SELECT codEmpleado, nombre, apellido FROM empleado";
		try {
			Class.forName(DRIVER);
			Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conexion.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Empleado empleado = new Empleado();
				empleado.setCodEmpleado(rs.getInt("codEmpleado"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setApellido(rs.getString("apellido"));
				empleados.add(empleado);
			}
			rs.close();
			stmt.close();
			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return empleados;
	}

	public static void main(String[] args) {
		String pathFichero = "C:\\Users\\multi\\Desktop\\DAM 2\\Procesos y Servicios\\EjemplosDOM\\empleados.xml";

		try {
			List<Empleado> listEmpleados = listarTodosEmpleados();
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element elementoEmpleados = document.createElement("empleados");
			document.appendChild(elementoEmpleados);

			Empleado empleado = null;

			for (int i = 0; i < listEmpleados.size(); i++) {
				// employee element
				empleado = listEmpleados.get(i);
				//
				Element elementoEmpleado = document.createElement("empleado");
				elementoEmpleados.appendChild(elementoEmpleado);
				// firstname element
				Element elementoCodEmpleado = document.createElement("codEmpleado");
				elementoCodEmpleado.appendChild(document.createTextNode(Integer.toString(empleado.getCodEmpleado())));
				elementoEmpleado.appendChild(elementoCodEmpleado);

				// lastname element
				Element elementoNombre = document.createElement("nombre");
				elementoNombre.appendChild(document.createTextNode(empleado.getNombre()));
				elementoEmpleado.appendChild(elementoNombre);

				// email element
				Element elementoApellido = document.createElement("apellido");
				elementoApellido.appendChild(document.createTextNode(empleado.getApellido()));
				elementoEmpleado.appendChild(elementoApellido);
			}

			// create the xml file
			// transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			//
			String nombreFicheroXml = pathFichero.substring(0, pathFichero.length() - 4);
			long horaActual = System.currentTimeMillis();
			Date fecha = new Date(horaActual);
			SimpleDateFormat formateadorFechas = new SimpleDateFormat("yyyyMMdd_HHmmss");
			nombreFicheroXml = nombreFicheroXml + "_" + formateadorFechas.format(fecha) + ".xml";
			//
			// StreamResult streamResult = new StreamResult(new File(PATH_XML_EMPLEADOS));
			StreamResult streamResult = new StreamResult(new File(nombreFicheroXml));

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging

			transformer.transform(domSource, streamResult);

			// System.out.println("Done creating XML File");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}