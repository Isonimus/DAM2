package com.tongosa.tombola;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tongosa.tombola.di.AccesoDatos;
import com.tongosa.tombola.di.impl.AccesoDatos2;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	// esta escuchando en la url de localhost y devuelve el codigo de abajo
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		// Ejemplo DI
		ApplicationContext appContext = new FileSystemXmlApplicationContext(new String[] {
				"C:\\Users\\multi\\Desktop\\DAM 2\\Procesos y Servicios\\EjemploSpring\\EjemploSpring\\WebContent\\WEB-INF\\spring\\root-context.xml" });
		AccesoDatos accesoDatos = (AccesoDatos) appContext.getBean("accesoDatos");
		accesoDatos.conectar();
		accesoDatos.desconectar();
		// Ejemplo AOP
		System.out.println("///////////////////////////////////");
		AccesoDatos2 accesoDatos2 = (AccesoDatos2) 
				appContext.getBean("accesoDatos2Proxy");
		accesoDatos2.conectar();
		accesoDatos2.desconectar();
		//

		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

}
