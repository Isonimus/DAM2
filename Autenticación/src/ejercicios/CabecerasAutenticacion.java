package ejercicios;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CabecerasAutenticacion")
public class CabecerasAutenticacion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	//CREACIÓN DEL OBJETO CONTENEDOR DEL CONJUNTO DE CREDENCIALES
	//REPRESENTA A UN FICHERO O A UNA TABLA DE LA BDD
	private Properties credenciales = new Properties();
	private boolean solicitarCredenciales = true;
	
    public CabecerasAutenticacion() {
        super();
    }
    
    //INICIALIZACIÓN  DE LAS CREDENCIALES PARA EL ACCESO A LA APP
    public void init() throws ServletException {
    	//
    	credenciales.setProperty("usuario1", "password1");
    	credenciales.setProperty("usuario2", "password2");
    }
    
    @SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if (solicitarCredenciales) {
			//SE ENVÍA 401 Y CABECERA INDICANDO QUE REQUIERE AUTENTICAR
			solicitarCredenciales = false;
			response.setStatus(response.SC_UNAUTHORIZED);
			response.setHeader("WWW-Authenticate", "BASIC realm=\"autenticación requerida para este ámbito.\"");
			
		}else {
			//SE OBTIENEN LAS CREDENCIALES RECIBIDAS EN LA PETICIÓN,
			//Y SE COMPARAN CON LAS ALMACENADAS;
			//PRIERO SE ELIMINAN LOS 6 PRIMEROS CARACTERES ("BASIC ")
			//QUE INDICAN AUTENTICACIÓN
			String cabAutorizacion = request.getHeader("Authorization");
			String autorizacion = cabAutorizacion.substring(6).trim();
			
			//SE DESCODIFICA LA CABECERA
			byte[] autorizacionDecodificada = Base64.getDecoder().decode(autorizacion);
			String datosUsuario = new String(autorizacionDecodificada);
			int separador = datosUsuario.indexOf(':');
			String usuario = datosUsuario.substring(0, separador);
			String contrasena = datosUsuario.substring(separador + 1);
			String contrasenaReal = credenciales.getProperty(usuario);
			
			//
			String informe = "ACCESO DENEGADO";
			//
			if(contrasenaReal != null && contrasenaReal.equals(contrasena)) {
				
				informe = "USUARIO AUTORIZADO";
			}
			
			solicitarCredenciales = true; //CREA BUCLE PARA ESTA DEMO (SALTA EL DIALOG CON CADA ENTER)
			
			//CONSTRUIR CONTENIDO HTML
			out.println("<!DOCTYPE HTML>");
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("<TITLE>Prueba de credenciales </TITLE>");
			out.println("</HEAD>");
			out.println("<BODY>");
			
			out.println("<FIELDSET>Datos recibidos: " + autorizacion);
			out.println("<BR>");
			out.println("Datos decodificados: " + datosUsuario);
			out.println("<BR>");
			out.println("Usuario: " + usuario);
			out.println("<BR>");
			out.println("Contraseña: " + contrasena);
			out.println("<BR>");
			out.println("Informe: " + informe + "</FIELDSET>");
			out.println("</BODY>");
			out.println("</HTML>");
			
			out.close();
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
