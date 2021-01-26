package ejercicios;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilidades.ControladorVisitas;

public class ContadorPeliculas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContadorPeliculas() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		
		//COMPUTAR LA VISITA A ESTA SECCIÓN
		ControladorVisitas.computarVisita(request, response, "Peliculas");
		
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Contadores de visitas</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<H1>Sección de Películas</H1>");
		out.println("<H3>Más de 1000 títulos.</H3");
		out.println("<BR>");
		out.println("<a href='/Cookies/home'>HOME</a>");
		out.println(" - ");
		out.println("<a href='/Cookies/libros'>Ir a Libros</a>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
