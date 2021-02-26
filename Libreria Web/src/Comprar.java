import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Comprar")
public class Comprar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public Comprar() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		Connection conexion = (Connection) this.getServletConfig().getServletContext().getAttribute("conexionBDD");
		//ACCION
		Hashtable<String, Integer> carrito = (Hashtable<String, Integer>)sesion.getAttribute("carrito");
		//SI EL CARRITO NO ESTÁ VACÍO
		if(!carrito.isEmpty()) {
			List<ResultSet> infoCarrito = GeneralDAO.getCartContents(conexion, (Hashtable<String, Integer>)sesion.getAttribute("carrito"));
			request.setAttribute("infoCarrito", infoCarrito);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Comprar.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
