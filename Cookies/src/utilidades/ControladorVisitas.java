package utilidades;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControladorVisitas {

	public static String obtenerVisitas(HttpServletRequest request, HttpServletResponse response, String seccion) {
		//
		Cookie[] cookies = request.getCookies();
		// BUSCAR LA COOKIE O CREAR SI NO EXISTE
		Cookie cookieContador = buscarCookie("contador" + seccion, cookies);
		String visitas;
		if (cookieContador == null) {
			// CREAR
			Cookie cookie = new Cookie("contador" + seccion, "0");
			cookie.setMaxAge(180); // EN SEGUNDOS
			response.addCookie(cookie);
			visitas = "0";
		} else {
			// OBTENER EL NÚMERO ACTUAL DE VISITAS A LA SECCIÓN
			visitas = cookieContador.getValue();

		}

		return visitas;
	}

	public static void computarVisita(HttpServletRequest request, HttpServletResponse response, String seccion) {
		//
		Cookie[] cookies = request.getCookies();
		//
		Cookie cookieContador = buscarCookie("contador" + seccion, cookies);
		if (cookieContador == null) {
			// CREAR
			Cookie cookie = new Cookie("contador" + seccion, "1");
			cookie.setMaxAge(180);
			response.addCookie(cookie);

		} else {
			// OBTENER EL NÚMERO ACTUAL DE VISITAS A LA SECCIÓN
			int visitas;
			visitas = Integer.parseInt(cookieContador.getValue());
			visitas++;
			// SE ACTUALIZA LA COOKIE
			Cookie cookie = new Cookie("contador" + seccion, "" + visitas);
			cookie.setMaxAge(180);
			response.addCookie(cookie);
		}
	}

	public static Cookie buscarCookie(String nombreCookie, Cookie[] cookies) {
		// SI NO HAY COOKIES
		if (cookies == null) {
			return null;
		}

		// SI ENCUENTRA LA COOKIE, LA DEVUELVE
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals(nombreCookie)) {
				return cookies[i];
			}
		}

		// SI NO ENCUENTRA LA COOKIE
		return null;
	}
}
