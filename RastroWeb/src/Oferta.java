import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Oferta")
public class Oferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//EN ESTA HASHTABLE SE ESTABLECE LA RELACI�N DE OBJETOS "PRODUCTO" Y SU CLAVE
	//DE TIPO STRING
	//TIENE LA MISMA ESTRUCTURA DE DATOS QUE HTTPSESSION,
	//PERO NO ES UNA SESI�N
	Hashtable<String, Producto> productosOfertados;
	
    public Oferta() {
        super();
    }
    
    public void init() throws ServletException{
    	//AL INICIAR EL SERVLET SE ESTABLECE LA COLECCI�N DE OBJETOS
    	//PRODUCTO CON LOS QUE SE TRABAJAR� EN EST APLICACI�N
    	productosOfertados = new Hashtable<String, Producto>();
    	productosOfertados.put("OF01", new Producto(1123, "Television Plasma", 459.99));
    	productosOfertados.put("OF02", new Producto(2020, "Ordenador Port�til", 812.00));
    	productosOfertados.put("OF03", new Producto(9001, "C�mara Reflex y accesorios", 1999.99));
    	productosOfertados.put("OF04", new Producto(4444, "Lavavajillas express", 658.00));
    	productosOfertados.put("OF05", new Producto(4321, "Taladradora con bater�a", 100.00));
    	
    	//UNA VEZ CREADA LA COLECCI�N SE COMPARTE CON EL RESTO DE LA APLICACI�N
    	//SE PUBLICA EN EL CONTEXTO DE LA APLICACI�N 
    	//(EL CONTEXTO ES OTRA ESTRUCTURA CLAVE => VALOR)
    	this.getServletConfig().getServletContext().setAttribute("productosOfertados", productosOfertados);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<!DOCTYPE HTML>");
		pw.println("<HTML>");
		pw.println("<HEAD>");
		pw.println("<meta charset=\"ISO-8859-1\">");
		pw.println("<TITLE>E-Commerce - RastroWeb</TITLE>");
		pw.println("</HEAD>");
		pw.println("<BODY>");
		pw.println("<H3>OFERTAS (RastroWeb)</H3>");
		pw.println("<form action = \"Seleccion\" method =\"POST\">");
		pw.println("<table border=\"1\">");
		pw.println("<TR>");
		pw.println("<TH align = \"center\"><b>Referencia</b></TD>");
		pw.println("<TH align = \"center\"><b>Descripci�n</b></TD>");
		pw.println("<TH align = \"center\"><b>Precio/u</b></TD>");
		pw.println("<TH align = \"center\"><b>A�adir</b></TD>");
		pw.println("</TR>");
		
		//DE LA COLECCI�N DE PRODUCTOS SE EXTRAE EL CONJUNTO DE CLAVES
		//QUE SE UTILIZA EN EL BUCLE PARA MONTAR LAS FILAS DE LA TABLA
		Enumeration<String> ofertas = productosOfertados.keys();
		String oferta;
		Producto producto;
		while(ofertas.hasMoreElements()) {
			//
			//EN CADA ITERACI�N SE EXTRAE UN PAR OFERTA-PRODUCTO
			//Y SE MUESTRA
			oferta = ofertas.nextElement();
			producto = productosOfertados.get(oferta);
			//SE MONTA LA FILA DE LA TABLA CON CADA PRODUCTO
			pw.println("<TR>");
			pw.println("<TD>" + producto.getId() + "</TD>");
			pw.println("<TD>" + producto.getDescripcion() + "</TD>");
			pw.println("<TD>" + producto.getImporte() + "</TD>");
			pw.println("<TD><input type=\"submit\" name = \"" + oferta + "\" value = \"+\" style = \"width: 100%\"></TD>");
			pw.println("</TR>");
		}
		//
		pw.println("</TABLE>");
		pw.println("</FORM>");
		pw.println("</BODY>");
		pw.println("</HTML>");
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
}
