package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Aplicacion") 
public class Aplicacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conexion;
	//private Statement sentencia;
	private ResultSet datos;
	private ResultSetMetaData metadatos;
  
    public Aplicacion() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	try {
    		//OBTENER EL CONTEXTO A TRAVÉS DE JNDI
    		Context contextoInicial = new InitialContext();
    		//OBTENER RECURSO CON SU NOMBRE LÓGICO
    		//"java:comp/env" ES EL NODO EN EL ARBOL JNDI DONDE BUSCAR EL RECURSO
    		//PARA EL ACTUAL COMPONENTE JavaEE (THIS WEBAPP)
    		DataSource conexiones = (DataSource) contextoInicial.lookup("java:comp/env/poolConexiones");
    		//CONEXIÓN CON LA BDD A TRAVÉS DEL DATASOURCE
    		conexion = conexiones.getConnection();
    		
    	}catch (NamingException e) {
    		System.out.println("Problemas en la obtención del Contexto inicial.");
    		e.printStackTrace();
    		
    	} catch (SQLException e) {
			System.out.println("Problemas en la obtención de la conexión con la BDD.");
			e.printStackTrace();
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//OBTENCIÓN DE LOS DATOS
		try {
			Statement sentencia = conexion.createStatement();
			datos = sentencia.executeQuery("SELECT cod_editorial AS 'CODIGO', nombre AS 'EDITORIAL' FROM editorial");
			metadatos = datos.getMetaData();
			
		}catch(SQLException e) {
			System.out.println("Se ha producido un problema el crear el objeto 'Statement'.");
		}
		
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Tomcat to MySQL Connection (VEclipse)</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<H1>Listado de Editoriales</H1>");
		out.println("<TABLE width='50%' border='1'>");
		out.println("<tr>");
		try {
			
			for(int i = 1; i <= metadatos.getColumnCount(); i++) {
				out.println("<th>" + metadatos.getColumnLabel(i) + "</th>"); //HEADERS DE LA TABLA
			}
			out.println("</tr>");
			
			while(datos.next()) {
				
				out.println("<tr>"); //FILAS DE DATOS
				
				for(int i = 1; i <= metadatos.getColumnCount(); i++) {
					
					if(i == 1) {
						out.println("<td>" + datos.getInt(i) + "</td>");
					}else {
						out.println("<td>" + datos.getString(i) + "</td>");
					}
					
				}
				out.println("</tr>");
			}
			
		}catch(SQLException e) {
			System.out.println("Se ha producido un problema preparando la respuesta de datos.");
		}
		
		out.println("</TABLE>");
		out.println("<a href = '/TTMConnectionVE'>HOME</a>");
		out.println("</BODY>");
		out.println("</HTML>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
