package com.formation.boutique.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.boutique.entities.Civilite;
import com.formation.boutique.entities.Client;
import com.formation.boutique.entities.Droit;

@Controller
public class ClientController {

	@GetMapping("/client/inscription")
	public String getInscription(ModelMap model) {

		model.addAttribute("client", new Client());
		model.addAttribute("lstCivilite", Civilite.values());
		model.addAttribute("action", "/client/inscription");
		model.addAttribute("method", "POST");
		return "/pages/client/inscription";
	}

	@GetMapping("/client/connexion")
	public String getConnexion(ModelMap model) {
		model.addAttribute("action", "/client/connexion");
		model.addAttribute("method", "POST");
		return "/pages/client/connexion";
	}

	@PostMapping("/client/inscription")
	public String postInscription(@Valid @ModelAttribute Client client,
			@ModelAttribute("lstCivilite") @Valid Civilite civilite, BindingResult resultClient,BindingResult resultCivilite, ModelMap model) {
		model.addAttribute("action", "/client/inscription");
		model.addAttribute("method", "POST");
		client.setDroit(Droit.ROLE_USER);
		System.out.println(client);
		if (resultClient.hasErrors()) {

			return "/pages/client/inscription";
		}

		return "/pages/home";
	}

	@PostMapping("/client/connexion")
	public String postConnexion(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, ModelMap model) {
		model.addAttribute("email", email);
		model.addAttribute("password", password);
		model.addAttribute("action", "/client/connexion");
		model.addAttribute("method", "POST");

		// to do verifier que le login et mot de passe sont les bons sinon
		// retour sur inscription ou mdp oublié

		return "/pages/client/connexion";
	}

}