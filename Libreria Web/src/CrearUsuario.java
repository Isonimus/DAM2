import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CrearUsuario")
public class CrearUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public CrearUsuario() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conexion = (Connection) this.getServletConfig().getServletContext().getAttribute("conexionBDD");
		String nombre = request.getParameter("nombre");
		String ape1 = request.getParameter("ape1");
		String dni = request.getParameter("dni");
		String direccion = request.getParameter("direccion");
		String email = request.getParameter("email");
		String fnac = request.getParameter("fnac");
		String uname = request.getParameter("uname");
		String upass1 = request.getParameter("upass1");
		
		String resultado = ClienteDAO.insertarCliente(conexion, dni, nombre, ape1, ape1, direccion, email, fnac, uname, upass1);
		if(resultado.equals("Registro completado correctamente.")) {
			
			request.setAttribute("redireccion", "LOG-IN");
			
		}else {
			
			request.setAttribute("redireccion", "REGISTRO");
		}
		
		request.setAttribute("mensaje", resultado);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Redirection.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
