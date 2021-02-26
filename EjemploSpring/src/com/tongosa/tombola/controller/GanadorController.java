package com.tongosa.tombola.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tongosa.tombola.domain.Premio;

//con el controler se convierte en un controlador
@Controller
public class GanadorController {

	//esta escuchando en la url de localhost y devuelve el codigo de abajo
	//	@RequestMapping(value = "/ganador") //al mapping se le pone el nombre del .jsp al que queremos ir
	//	public String mostrarGanador(Model modelo){
		
	//		ArrayList<String> nombreganador = new ArrayList<String>();
	//		nombreganador.add("Asier");
	//		nombreganador.add("Gari");
	//		nombreganador.add("Mikel");
	//		nombreganador.add("Pedro");
	//		nombreganador.add("Jon");
			
	//		modelo.addAttribute("nombreganador",nombreganador );
					
		
	//	return "ganador";
			
	
			 			  
		@RequestMapping(value = "/ganador") //al mapping se le pone el nombre del .jsp al que queremos ir
		public String mostrarGanador(Model modelo){
		
		String vista = "ganador"; //nombre de la JSP
		//Recuperar el modelo de bbdd
			ArrayList<String> nomganador = new ArrayList<String>();
			
			nomganador.add("Manolo Cabezabolo");
			nomganador.add("Marianito el corto");
			nomganador.add("Manolo el del bombo");
			
			modelo.addAttribute("nombreganador",nomganador ); //nombre, objetos		

			//listado de premios
			ArrayList<Premio> aPremios = new ArrayList<Premio>();
			aPremios.add(new Premio("cuaderno",2.35f));
			aPremios.add(new Premio("Boligrafo",1.15f));
			aPremios.add(new Premio("cafe + pintxo",2.75f));
			aPremios.add(new Premio("Lata anchoas",0.35f));

			//Pasar el atributo a la vista de JSP
				
			modelo.addAttribute("premios",aPremios);
					
			return vista;
			  
		}
}
