package prueba.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prueba.beans.Persona;

/**
 * Servlet implementation class SrvPrueba1
 */
public class SrvPrueba1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Persona persona = new Persona();
		persona.setNombre("Marta");
		persona.setApellido("Lopez");
		request.setAttribute("persona", persona);
		ServletContext ct = getServletContext();
		RequestDispatcher rd = ct.getRequestDispatcher("/prueba1.jsp");
		rd.forward(request, response);

	}

}
