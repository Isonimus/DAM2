import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//TODO: A�ADIR BOTONES QUITAR ELEMENTO Y CONTEO DE ITEMS
@WebServlet("/Seleccion")
public class Seleccion extends HttpServlet {
	//
	private static final long serialVersionUID = 1L;
    //
	//REFERENCIA A LA COLECCI�N DE PRODUCTOS
	//CON VISIBILIDAD EN TODA LA APLICACI�N
    Hashtable<String, Producto> productosOfertados;
    
    public Seleccion() {
        super();
    }
    
    @SuppressWarnings("unchecked")
    public void init() throws ServletException{
    	//SE RECUPERA DEL CONTEXTO LA COLECCI�N DE PRODUCTOS
    	//productosOfertados = new Hashtable<String, Producto>();
    	productosOfertados = (Hashtable<String, Producto>) this.getServletConfig().getServletContext().getAttribute("productosOfertados");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		//CON LA SELECCI�N DEL USUARIO SE GENERA UNA L�NEA DETALLE
		//DEL PEDIDO, QUE SE GUARDA EN LA SESI�N DE USUARIO "PEDIDO"
		HttpSession pedido = request.getSession();
		PrintWriter pw = response.getWriter();
		
		//SE OBTIENEN LAS CLAVES DE LA COLECCI�N DE PRODUCTOS
		Enumeration<String> ofertas = productosOfertados.keys();
		String oferta = null;
		String seleccion = null;
		
		//SE BUSCA CU�L HA SIDO LA SELECCI�N DEL USUARIO
		while(seleccion == null) {
			oferta = ofertas.nextElement();
			seleccion = request.getParameter(oferta);
		}
		
		//SE CREA LA L�NEA DETALLE DEL PEDIDO
		pedido.setAttribute(oferta, productosOfertados.get(oferta));
		
		Enumeration<String> productosPedido = pedido.getAttributeNames();
		Producto producto;
		
		//SE LISTA EL CONTENIDO DE LA SESI�N/PEDIDO
		pw.println("<!DOCTYPE HTML>");
		pw.println("<HTML>");
		pw.println("<HEAD>");
		pw.println("<meta charset = \"ISO-8859-1\">");
		pw.println("<TITLE>E-Commerce - RastroWeb</TITLE>");
		pw.println("</HEAD>");
		pw.println("<BODY>");
		pw.println("<H3> SELECCI�N (RastroWeb)</H3>");
		pw.println("<TABLE border = \"1\">");
		pw.println("<TR>");
		pw.println("<TH align = \"center\"><b>Referencia</b></TD>");
		pw.println("<TH align = \"center\"><b>Descripci�n</b></TD>");
		pw.println("<TH align = \"center\"><b>Precio/u</b></TD>");
		pw.println("<TH align = \"center\"><b>Unidades</b></TD>");
		pw.println("</TR>");
		
		//SE CREA UNA FILA POR CADA PRODUCTO EN EL PEDIDO
		while(productosPedido.hasMoreElements()) {
			producto = (Producto) pedido.getAttribute(productosPedido.nextElement());
			pw.println("<TR>");
			pw.println("<TD>" + producto.getId() + "</TD>");
			pw.println("<TD>" + producto.getDescripcion() + "</TD>");
			pw.println("<TD>" + producto.getImporte() + "</TD>");
			pw.println();
			pw.println("</TR>");
		}
		
		pw.println("</TABLE>");
		pw.println("<form action = \"Oferta\">");
		pw.println("<input type = \"submit\" value = \"Ir a ofertas\">");
		pw.println("</FORM>");
		pw.println("</BODY>");
		pw.println("</HTML>");
		pw.close();
	}
}
