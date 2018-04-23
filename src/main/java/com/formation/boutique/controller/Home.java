package com.formation.boutique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class Home {

	
	
	
	@GetMapping("/")
	public String home (ModelMap model){
		
		return "/pages/home";
	}
}
