package com.tongosa.tombola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


//con el controler se convierte en un controlador
@Controller
public class SorteoController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//esta escuchando en la url de localhost y devuelve el codigo de abajo
		@RequestMapping(value = "/sorteo")
		public String mostrarVista(Model model){
			
		model.addAttribute("nombre","Manolo cabezabolo");	
		model.addAttribute("nombrepremio","Anchoas del cantabrico con extra de oliva");	
			
			return "sorteo";
		}
}
