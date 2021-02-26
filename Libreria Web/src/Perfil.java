import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Perfil")
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Perfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conexion = (Connection) this.getServletConfig().getServletContext().getAttribute("conexionBDD");
		HttpSession sesion = request.getSession();
		ResultSet userData = ClienteDAO.getUserData(conexion, (String) request.getSession().getAttribute("userId"));
		request.setAttribute("userData", userData);
		ResultSet userHistory = ClienteDAO.getUserHistory(conexion, (String) sesion.getAttribute("userId"));
		request.setAttribute("userHistory", userHistory);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Perfil.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
