

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CabecerasPeticion")
public class CabecerasPeticion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CabecerasPeticion() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML>");
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<CENTER><H1>Cabeceras de la petición</H1></CENTER>");
		out.println("<Fieldset>");
		
		//OBTENER LOS NOMBRES DE LAS CABECERAS
		Enumeration<String> cabeceras = request.getHeaderNames();
		
		//IMPRIMIR
		while(cabeceras.hasMoreElements()) {
			String nombre = cabeceras.nextElement();
			out.println(nombre + " :: " + request.getHeader(nombre));
			out.println("<BR><BR>");
		}
		out.println("</Fieldset>");
		out.println("</BODY>");
		out.println("</HTML>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
