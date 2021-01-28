import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sesionUsuario")
public class SesionUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public SesionUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession miSesion = request.getSession(true);
		Producto p1, p2, p3, p4;
		
		p1 = new Producto(1, "teléfono", 300);
		p2 = new Producto(2, "televisión", 600);
		p3 = new Producto(3, "coche", 12000);
		p4 = new Producto(4, "ordenador", 700);
		miSesion.setAttribute("1", p1);
		miSesion.setAttribute("2", p2);
		miSesion.setAttribute("3", p3);
		miSesion.setAttribute("4", p4);
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<HTML><BODY>Productos en la sesión</BODY></HTML>");
		
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
