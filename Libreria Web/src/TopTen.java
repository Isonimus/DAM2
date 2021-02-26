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

@WebServlet("/TopTen")
public class TopTen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TopTen() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conexion = (Connection) this.getServletConfig().getServletContext().getAttribute("conexionBDD");
		ResultSet resultado = GeneralDAO.getTop10Books(conexion);
		request.setAttribute("lista", resultado);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Home.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
