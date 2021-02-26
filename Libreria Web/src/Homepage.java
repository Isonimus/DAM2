import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/Homepage")
public class Homepage extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public Homepage() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    	
    	try {
    		//OBTENER EL CONTEXTO A TRAVÉS DE JNDI
    		Context contextoInicial = new InitialContext();
    		//OBTENER RECURSO CON SU NOMBRE LÓGICO
    		//"java:comp/env" ES EL NODO EN EL ARBOL JNDI DONDE BUSCAR EL RECURSO
    		//PARA EL ACTUAL COMPONENTE JavaEE (THIS WEBAPP)
    		DataSource conexiones = (DataSource) contextoInicial.lookup("java:comp/env/poolConexiones");
    		//CONEXIÓN CON LA BDD A TRAVÉS DEL DATASOURCE
    		Connection conexion = conexiones.getConnection();
    		//AÑADIR CONEXIÓN AL CONTEXTO DEL SERVLET
    		//PARA QUE ESTÉ ACCESIBLE DESDE TODA LA APP
    		this.getServletConfig().getServletContext().setAttribute("conexionBDD", conexion);
    		
    	}catch (NamingException e) {
    		System.out.println("Problemas en la obtención del Contexto inicial.");
    		e.printStackTrace();
    		
    	} catch (SQLException e) {
			System.out.println("Problemas en la obtención de la conexión con la BDD.");
			e.printStackTrace();
		}
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		//PREPARACIÓN DE LA SESIÓN
		if(sesion.getAttribute("carrito") == null) {
			sesion.setAttribute("carrito", new Hashtable<String, Integer>());
		}
		
		if(sesion.getAttribute("logged") == null) {
			sesion.setAttribute("logged", false);
		}
		
		if(sesion.getAttribute("nombre") == null) {
			sesion.setAttribute("nombre", "invitado");
		}
		
		if(sesion.getAttribute("userId") == null) {
			sesion.setAttribute("userId", "00000000X");
		}
		//sesion.invalidate();
		
		String[] opciones = {"HOME", "CATALOGO", "CARRITO", "COMPRAR", "LOG-IN", "LOGOUT", "REGISTRO", "PERFIL"};
		String opcion = null;
		String seleccion = null;
		
		//SE BUSCA CUÁL HA SIDO LA SELECCIÓN DEL USUARIO
		int i = 0;
		while(seleccion == null && i < 8) {
			
			opcion = opciones[i];
			seleccion = request.getParameter(opcion);
			i++;
			if(i == 8 && seleccion == null) {
				seleccion = "";
				opcion = "HOME";
			}
		}
		
		String target;
		
		switch(opcion) {
			case "HOME":
				target = "/TopTen";
				break;
			case "CATALOGO":
				target = "/Catalogo";
				break;
			case "CARRITO":
				target = "/Carrito";
				break;
			case "COMPRAR":
				if((boolean)sesion.getAttribute("logged")) {
					target = "/Comprar";
				}else {
					target = "/Login";
				}
				break;
			case "LOG-IN":
				target = "/Login";
				break;
			case "LOGOUT":
				target = "/Logout";
				break;
			case "REGISTRO":
				target = "/Registro";
				break;
			case "PERFIL":
				target = "/Perfil";
				break;
			default:
				target = "/TopTen";
				break;
		}
		
		//RequestDispatcher requestDispatcher = request.getRequestDispatcher(target);//TOP 10
		RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher(target);
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
