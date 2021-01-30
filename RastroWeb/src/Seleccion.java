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
//TODO: AÑADIR BOTONES QUITAR ELEMENTO Y CONTEO DE ITEMS
@WebServlet("/Seleccion")
public class Seleccion extends HttpServlet {
	//
	private static final long serialVersionUID = 1L;
    //
	//REFERENCIA A LA COLECCIÓN DE PRODUCTOS
	//CON VISIBILIDAD EN TODA LA APLICACIÓN
    Hashtable<String, Producto> productosOfertados;
    
    public Seleccion() {
        super();
    }
    
    @SuppressWarnings("unchecked")
    public void init() throws ServletException{
    	//SE RECUPERA DEL CONTEXTO LA COLECCIÓN DE PRODUCTOS
    	productosOfertados = (Hashtable<String, Producto>) this.getServletConfig().getServletContext().getAttribute("productosOfertados");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		//CON LA SELECCIÓN DEL USUARIO SE GENERA UNA LÍNEA DETALLE
		//DEL PEDIDO, QUE SE GUARDA EN LA SESIÓN DE USUARIO "PEDIDO"
		HttpSession pedido = request.getSession();
		PrintWriter pw = response.getWriter();
		
		//SE OBTIENEN LAS CLAVES DE LA COLECCIÓN DE PRODUCTOS
		Enumeration<String> ofertas = productosOfertados.keys();
		String oferta = null;
		String seleccion = null;
		
		//SE BUSCA CUÁL HA SIDO LA SELECCIÓN DEL USUARIO
		while(seleccion == null) {
			
			oferta = ofertas.nextElement();
			seleccion = request.getParameter(oferta);
		}
		
		//UPDATE
		//COMPROBAR SI YA EXISTE EN LA SESIÓN
		Producto p = (Producto) pedido.getAttribute(oferta);
		//SI NO EXISTE, AÑADIR
		if(p == null) {
			
			//SE CREA LA ENTRADA DEL PRODUCTO EN LA SESIÓN
			pedido.setAttribute(oferta, productosOfertados.get(oferta));
			
		}else { //SI YA EXISTE, INCREMENTAR CANTIDAD Y AÑADIR
			
			p.setCantidad(p.getCantidad() + 1);
			pedido.setAttribute(oferta, p);
		}
		
		System.out.println(productosOfertados.get(oferta).getDescripcion());

		Enumeration<String> productosPedido = pedido.getAttributeNames();
		Producto producto;
		
		//SE LISTA EL CONTENIDO DE LA SESIÓN/PEDIDO
		pw.println("<!DOCTYPE HTML>");
		pw.println("<HTML>");
		pw.println("<HEAD>");
		pw.println("<meta charset = \"ISO-8859-1\">");
		pw.println("<TITLE>E-Commerce - RastroWeb</TITLE>");
		pw.println("</HEAD>");
		pw.println("<BODY>");
		pw.println("<H3> SELECCIÓN (RastroWeb)</H3>");
		pw.println("<form action = \"Deseleccion\" method =\"POST\">");
		pw.println("<TABLE border = \"1\">");
		pw.println("<TR>");
		pw.println("<TH align = \"center\"><b>Referencia</b></TD>");
		pw.println("<TH align = \"center\"><b>Descripción</b></TD>");
		pw.println("<TH align = \"center\"><b>Precio/u</b></TD>");
		pw.println("<TH align = \"center\"><b>Unidades</b></TD>");
		pw.println("<TH align = \"center\"><b>Quitar</b></TD>");
		pw.println("</TR>");
		
		//SE CREA UNA FILA POR CADA PRODUCTO EN EL PEDIDO
		while(productosPedido.hasMoreElements()) {
			String codeOferta = productosPedido.nextElement();
			producto = (Producto) pedido.getAttribute(codeOferta);
			pw.println("<TR>");
			pw.println("<TD align = \"center\">" + producto.getId() + "</TD>");
			pw.println("<TD align = \"center\">" + producto.getDescripcion() + "</TD>");
			pw.println("<TD align = \"center\">" + producto.getImporte() + "</TD>");
			pw.println("<TD align = \"center\">" + producto.getCantidad() + "</TD>");
			pw.println("<TD><input type=\"submit\" name = \"" + codeOferta + "\" value = \"-\" style = \"width: 100%\"></TD>");
			pw.println();
			pw.println("</TR>");
		}
		
		pw.println("</TABLE>");
		pw.println("</FORM>");
		pw.println("<form action = \"Oferta\">");
		pw.println("<input type = \"submit\" value = \"Ir a ofertas\">");
		pw.println("</FORM>");
		pw.println("</BODY>");
		pw.println("</HTML>");
		pw.close();
	}
}
