

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@WebServlet("/listener")
public class listener extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public listener() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		
		HttpSession sesion = request.getSession();
		PrintWriter out = response.getWriter();
		
		if(sesion.isNew()) {
			out.println("<HTML><HEAD><TITLE>Listener de Sesión</TITLE></HEAD><BODY>" + "Mensaje de inicio" + "</BODY></HTML>");
			sesion.setAttribute("contador", new ObjetoSesion(1));
			
		}else {
			
			//MOSTRAR EL VALOR ACTUAL DEL CONTADOR
			int contador = ((ObjetoSesion) (sesion.getAttribute("contador"))).getValor();
			String mensaje = ((ObjetoSesion) (sesion.getAttribute("contador"))).getEnlazado();
			
			out.println("<HTML><BODY>");
			out.println("Valor: " + contador + "<BR>Enlazado: " + mensaje + " (" + sesion.getMaxInactiveInterval() + ")");
			out.println("</BODY></HTML>");
			
			sesion.setAttribute("contador", new ObjetoSesion(contador + 1));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	class ObjetoSesion implements HttpSessionBindingListener{
		
		int valor;
		String enlazado = "NO";
		
		public ObjetoSesion(int valor) {
			this.valor = valor;
		}
		
		public void valueBound(HttpSessionBindingEvent e) {
			enlazado = "Objeto enlazado a la sesión " + valor + " veces";
		}
		
		public void valueUnbound(HttpSessionBindingEvent e) {
			
		}
		
		public String getEnlazado() {
			return enlazado;
		}
		
		public int getValor() {
			return valor;
		}
	}
}
