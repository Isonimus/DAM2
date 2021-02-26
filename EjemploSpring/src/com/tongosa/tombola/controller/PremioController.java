package com.tongosa.tombola.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//con el controler se convierte en un controlador
@Controller
public class PremioController {

	//esta escuchando en la url de localhost y devuelve el codigo de abajo
	@RequestMapping(value = "/premio")
	public String mostrarPremio(Model model){
	
		ArrayList<String> nombrepremio = new ArrayList<String>();
		nombrepremio.add("Chope");
		nombrepremio.add("Yogur");
		nombrepremio.add("Naranjas");
		nombrepremio.add("Melon");
		nombrepremio.add("Aceitunas");
		
		model.addAttribute("nombrepremio",nombrepremio );
	
		return "premio";
	}
	
}
