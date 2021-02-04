import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Homepage")
public class Homepage extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Connection conexion;
	private Statement sentencia;
	private ResultSet resultado;
    
    public Homepage() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	try {
    		//OBTENER EL CONTEXTO A TRAV�S DE JNDI
    		Context contextoInicial = new InitialContext();
    		//OBTENER RECURSO CON SU NOMBRE L�GICO
    		//"java:comp/env" ES EL NODO EN EL ARBOL JNDI DONDE BUSCAR EL RECURSO
    		//PARA EL ACTUAL COMPONENTE JavaEE (THIS WEBAPP)
    		DataSource conexiones = (DataSource) contextoInicial.lookup("java:comp/env/poolConexiones");
    		//CONEXI�N CON LA BDD A TRAV�S DEL DATASOURCE
    		conexion = conexiones.getConnection();
    		sentencia = conexion.createStatement();
			GeneralDAO.setConexionBDD(sentencia, resultado);
    		
    	}catch (NamingException e) {
    		System.out.println("Problemas en la obtenci�n del Contexto inicial.");
    		e.printStackTrace();
    		
    	} catch (SQLException e) {
			System.out.println("Problemas en la obtenci�n de la conexi�n con la BDD.");
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
