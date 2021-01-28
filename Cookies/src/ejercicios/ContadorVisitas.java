package ejercicios;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilidades.ControladorVisitas;
//URL: http://localhost:8080/Cookies/home
public class ContadorVisitas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContadorVisitas() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		
		String visitasLibros = ControladorVisitas.obtenerVisitas(request, response, "Libros");
		String visitasPeliculas = ControladorVisitas.obtenerVisitas(request, response, "Peliculas");
		
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Contadores de Visitas</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<H1>HOME</H1>");
		out.println("<H3>Bienvenidos a la tienda de libros y películas.</H3>");
		out.println("<BR>");
		out.println("Visitas a la sección de libros: " + visitasLibros + ", y a la sección de Películas: " + visitasPeliculas + ".");
		out.println("<BR><BR>");
		out.println("<form method='POST' action= 'libros'>");
		out.println("<input type = 'submit' name = 'libros' value = 'Ir a libros'>");
		out.println("</form>");
		out.println("<form method='POST' action= 'peliculas'>");
		out.println("<input type = 'submit' name = 'peliculas' value = 'Ir a peliculas'>");
		out.println("</form>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
