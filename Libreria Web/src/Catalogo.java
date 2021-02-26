import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Catalogo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conexion = (Connection) this.getServletConfig().getServletContext().getAttribute("conexionBDD");
		//SI VIENE DE LA BÚSQUEDA
		String busqueda;
		String tipoBusqueda;
		ResultSet resultado;
		
		if(request.getParameter("busqueda") != null && request.getParameter("tipoBusqueda") != null) {
			
			busqueda = (String) request.getParameter("busqueda");
			tipoBusqueda = (String) request.getParameter("tipoBusqueda");
			resultado = GeneralDAO.searchBooks(conexion, busqueda, tipoBusqueda);
			request.setAttribute("encabezado", "Resultados para la búsqueda '" + busqueda + "':");
			
		}else {
			
			resultado = GeneralDAO.getAllBooks(conexion);
		}
		request.setAttribute("catalogo", resultado);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Catalogo.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
