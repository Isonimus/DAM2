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

@WebServlet("/ConfirmarCompra")
public class ConfirmarCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmarCompra() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conexion = (Connection) this.getServletConfig().getServletContext().getAttribute("conexionBDD");
		HttpSession sesion = request.getSession();
		Hashtable<String, Integer> carrito = (Hashtable<String, Integer>) sesion.getAttribute("carrito");
		String resultado = CompraDAO.insertarCompra(conexion, sesion);
		String mensaje;
		//SI HA IDO BIEN LA TRANSACCIÓN
		if(resultado.equals("Compra realizada correctamente.")) {
			
			carrito.clear();
			sesion.setAttribute("carrito", carrito);
			mensaje = resultado;
			
		}else {
			
			mensaje = "Error al realizar la compra";
		}
		
		request.setAttribute("mensaje", mensaje);
		
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
