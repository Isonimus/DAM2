import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Authentication")
public class Authentication extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public Authentication() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conexion = (Connection) this.getServletConfig().getServletContext().getAttribute("conexionBDD");
		
		String uName = null;
		String uPass = null;
		
		if(request.getParameter("user") != null) {
			uName = request.getParameter("user");
		}
		
		if(request.getParameter("pass") != null) {
			uPass = request.getParameter("pass");
		}
		
		try {
			
			if(ClienteDAO.logIn(conexion, uName, uPass)) {
				
				HttpSession sesion = request.getSession();
				sesion.setAttribute("logged", true);
				Hashtable<String, Integer> carrito = (Hashtable<String, Integer>)sesion.getAttribute("carrito");
				ResultSet rs = ClienteDAO.getUserFingerprint(conexion, uName, uPass);
				
				while(rs.next()) {
					sesion.setAttribute("userId", rs.getString(1));
					sesion.setAttribute("nombre", rs.getString(2));
				}
				
				//SI EL CARRITO NO ESTÁ VACÍO
				if(!carrito.isEmpty()) {
					List<ResultSet> infoCarrito = GeneralDAO.getCartContents(conexion, (Hashtable<String, Integer>)sesion.getAttribute("carrito"));
					request.setAttribute("infoCarrito", infoCarrito);
				}
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Carrito.jsp");
				requestDispatcher.forward(request, response);
				
			}else {
				
				request.setAttribute("mensaje", "Datos de acceso incorretos.");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");
				requestDispatcher.forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
