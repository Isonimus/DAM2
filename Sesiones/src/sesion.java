

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sesion")
public class sesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public sesion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		// ANTES DE PEDIR EL WRITER
		HttpSession sesion = request.getSession();
		PrintWriter out = response.getWriter();
		//
		if(sesion.isNew()) {
			//
			out.println("<!DOCTYPE HTML>");
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>Ejercicio básico de sesiones web.</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY>");
			out.println("Primera visita a esta página (" + sesion.getId() + ")");
			out.println("<BR>");
			out.println("</BODY>");
			out.println("</HTML>");
			//
			sesion.setAttribute("contador", Integer.valueOf(1));
			//
		}else {
			//
			int contador = ((Integer) (sesion.getAttribute("contador"))).intValue();
			contador++;
			//
			out.println("<!DOCTYPE HTML>");
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>Ejercicio básico de sesiones web.</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY>");
			out.println("Visita número <b>" + contador + "</b> a la página en esta sesión: " + sesion.getId() + ".");
			out.println("<BR>");
			out.println("</BODY>");
			out.println("</HTML>");
			//
			sesion.setAttribute("contador", Integer.valueOf(contador));
		}
	}
}
