import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Homepage")
public class Homepage extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Connection conexion;
	private Statement sentencia;
	private ResultSet resultado;
    
    public Homepage() {
        super();
    }
    
    public void init() {
    	
    	this.conexion = ServicioBBDD.obtenerServicio(ServicioBBDD.MYSQL).obtenerConexion();
		
		try {
			
			sentencia = conexion.createStatement();
			GeneralDAO.setConexionBDD(sentencia, resultado);
			
		} catch (SQLException e) {
			
			System.out.println("Error: Fallo al crear recursos de BDD.");
			e.printStackTrace();
		}
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		resultado = GeneralDAO.getTop10Books();
		request.setAttribute("lista", resultado);
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Home.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
