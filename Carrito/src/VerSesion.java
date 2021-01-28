import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/verSesion")
public class VerSesion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public VerSesion() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession miSesion = (HttpSession) request.getSession();
		PrintWriter pw = response.getWriter();
		
		//RECOGER LOS NOMBRES DE LAS CLAVES QUE HAY EN LA SESIÓN
		Enumeration<String> productos = miSesion.getAttributeNames();
		Producto producto;
		
		//SALIDA DEL CONTENIDO DE LA SESIÓN
		pw.println("<HTML><HEAD><TITLE>Listado del contenido de una sesión</TITLE></HEAD><BODY>");
		//
		while(productos.hasMoreElements()) {
			producto = (Producto) miSesion.getAttribute(productos.nextElement());
			pw.println(producto.getId() + " - " + producto.getDescripcion() + ", " + producto.getImporte() + "<BR>");
		}
		//
		pw.println("</BODY></HTML>");
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
}
