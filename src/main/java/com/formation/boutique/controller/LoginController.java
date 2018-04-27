package com.formation.boutique.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.boutique.entities.Civilite;
import com.formation.boutique.entities.Client;
import com.formation.boutique.entities.Droit;
import com.formation.boutique.entities.Login;
import com.formation.boutique.services.ClientService;

@Controller
public class LoginController {

	private final ClientService clientService;

	public LoginController(ClientService clientService) {
		this.clientService = clientService;
	}
	

	@GetMapping("/client/login")
	public String getLogin(ModelMap model) {
		model.addAttribute("action", "/client/login");
		model.addAttribute("login", new Login());
		model.addAttribute("method", "POST");
		model.addAttribute("message","coo");
		return "/pages/client/login";
	}
	
	@GetMapping("/client/logout")
	public String getLogout(ModelMap model,HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/client/login")
	public String postConnexion(@Valid @ModelAttribute(name = "login") Login login, BindingResult resultLogin, ModelMap model, HttpSession httpSession){
	
		Client client = clientService.login(login.getEmail(),login.getPassword());
		if(resultLogin.hasErrors() || client == null){
			model.addAttribute("action", "/client/login");
			model.addAttribute("method", "POST");
			model.addAttribute("message","erreur.login");
			return "/pages/client/login";

		}
		httpSession.setMaxInactiveInterval(60 *60 * 24);
		httpSession.setAttribute("client", client);
		return "redirect:/";
	}
	
}