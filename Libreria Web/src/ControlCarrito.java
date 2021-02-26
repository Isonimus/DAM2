import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Enumeration;
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

@WebServlet("/ControlCarrito")
public class ControlCarrito extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ControlCarrito() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conexion = (Connection) this.getServletConfig().getServletContext().getAttribute("conexionBDD");
		HttpSession sesion = request.getSession();
		Hashtable<String, Integer> carrito = (Hashtable<String, Integer>) sesion.getAttribute("carrito");
		ResultSet resultado = GeneralDAO.getAllBooks(conexion);
		request.setAttribute("catalogo", resultado);
		/*
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Catalogo.jsp");
		requestDispatcher.forward(request, response);*/
		Enumeration<String> parametros = request.getParameterNames();
		String target = null;
		
		while(parametros.hasMoreElements()) {
			//COMPROBAR SI YA EXISTE EN LA SESIÓN
			String isbn = parametros.nextElement();
			String orden = request.getParameter(isbn);
			String check = String.valueOf(carrito.get(isbn));
			
			if(orden.equals("add")) { 
				//SI NO EXISTE, AÑADIR
				if(check == "null") {
					//SE CREA LA ENTRADA DEL PRODUCTO EN LA SESIÓN
					//sesion.setAttribute(isbn, 1);
					carrito.put(isbn, 1);
					
				}else { //SI YA EXISTE, INCREMENTAR CANTIDAD Y AÑADIR
					
					int cantidad = Integer.parseInt(check) + 1;
					//sesion.setAttribute(isbn, cantidad);
					carrito.put(isbn, cantidad);
				}
				target = "/Catalogo.jsp";
				
			}else if(orden.equals("remove")) {
				//SI HAY MÁS DE 1 UNIDAD
				if(Integer.parseInt(check) > 1) {
					//SE DECREMENTA LA CANTIDAD
					carrito.put(isbn, Integer.parseInt(check) - 1);
					
				}else { //SI SÓLO QUEDA UNO, ELIMINAR DEL CARRITO
					
					carrito.remove(isbn);
				}
				
				//SI EL CARRITO NO ESTÁ VACÍO
				if(!carrito.isEmpty()) {
					List<ResultSet> infoCarrito = GeneralDAO.getCartContents(conexion, (Hashtable<String, Integer>)sesion.getAttribute("carrito"));
					request.setAttribute("infoCarrito", infoCarrito);
				}
				
				target = "/Carrito.jsp";
			}
		}
		
		sesion.setAttribute("carrito", carrito);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(target);
		requestDispatcher.forward(request, response);
		
		//DEBUG DE LA SESIÓN
		/*
		PrintWriter pw = response.getWriter();
		Enumeration<String> lista = sesion.getAttributeNames();
		while(lista.hasMoreElements()) {
			String elemento = lista.nextElement();
			pw.println(elemento + ": " + sesion.getAttribute(elemento));
		}
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
