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
import org.springframework.web.bind.annotation.PutMapping;

import com.formation.boutique.entities.Civilite;
import com.formation.boutique.entities.Client;
import com.formation.boutique.entities.Droit;
import com.formation.boutique.services.ClientService;

@Controller
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	

	@GetMapping("/client/inscription")
	public String getInscription(ModelMap model) {

		model.addAttribute("client", new Client());
		model.addAttribute("lstCivilite", Civilite.values());
		model.addAttribute("password2", "");
		model.addAttribute("action", "/client/inscription");
		model.addAttribute("title","client.inscription.entete.inscription");
		model.addAttribute("boutton","client.inscription.submit");
		model.addAttribute("method", "POST");
		return "/pages/client/inscription";
	}
	
	@GetMapping("/client/create")
	public String getInscriptionAdmin(ModelMap model) {

		model.addAttribute("client", new Client());
		model.addAttribute("lstCivilite", Civilite.values());
		model.addAttribute("lstDroit", Droit.values());
		model.addAttribute("password2", "");
		model.addAttribute("action", "/client/create");
		model.addAttribute("title","client.inscription.entete.update");
		model.addAttribute("method", "POST");
		return "/pages/client/create";
	}
	
	@GetMapping("/client/update/{email}")
	public String getUpdateClientByAdmin(@PathVariable String email,ModelMap model) {

		Client clientBDD = clientService.getOne(email);
		model.addAttribute("client", clientBDD);
		model.addAttribute("lstCivilite", Civilite.values());
		model.addAttribute("lstDroit", Droit.values());
		model.addAttribute("password2", clientBDD.getPassword());
		model.addAttribute("action", "/client/update/"+email);
		model.addAttribute("title","client.inscription.entete.update");
		model.addAttribute("method", "PUT");
		return "/pages/client/create";
	}
	
	@GetMapping("/client/modification")
	public String getUpdateClientByUser(ModelMap model, HttpSession httpSession) {
		String email = ((Client) httpSession.getAttribute("client")).getEmail();
		model.addAttribute("client", clientService.getOne(email));
		model.addAttribute("lstCivilite", Civilite.values());
		model.addAttribute("password2", clientService.getOne(email).getPassword());
		model.addAttribute("action", "/client/modification");
		model.addAttribute("title","client.inscription.entete.update");
		model.addAttribute("boutton","client.inscription.update");

		model.addAttribute("method", "PUT");
		return "/pages/client/inscription";
	}

	
	@GetMapping("/client/list")
	public String listCompte(ModelMap model) {
		model.addAttribute("lstCompte", clientService.getAll());
		model.addAttribute("action", "/client/connexion");
		model.addAttribute("method", "POST");
		return "/pages/client/list";
	}
	
	
	@GetMapping("/client/delete/{email}")
	public String supprimerClient(@PathVariable String email, ModelMap model){
		clientService.deleteByEmail(email);
		return "/pages/client/list";

	}
	
	@PostMapping("/client/inscription")
	public String postInscription(@Valid @ModelAttribute(name = "client") Client client, BindingResult resultClient,
			@Valid @ModelAttribute(name = "password2") String password2, ModelMap model) {
		model.addAttribute("lstCivilite", Civilite.values());
		model.addAttribute("password2", password2);
		model.addAttribute("action", "/client/inscription");
		model.addAttribute("title","client.inscription.entete.create");
		model.addAttribute("boutton","client.inscription.submit");
		model.addAttribute("method", "POST");
		client.setDroit(Droit.RULE_USER);
		password2 = Client.get_SHA_512_SecurePassword(password2);

		if(!passwordValid(client.getPassword(), password2)){
			return "/pages/client/inscription";
		}
		
		if (resultClient.hasErrors()) {
			return "/pages/client/inscription";
		}
		clientService.save(client);
		return "/pages/home";
	}
	
	@PostMapping("/client/create")
	public String postCreate(@Valid @ModelAttribute(name = "client") Client client, BindingResult resultClient,
			@Valid @ModelAttribute(name = "password2") String password2, ModelMap model) {
		model.addAttribute("lstCivilite", Civilite.values());
		model.addAttribute("lstDroit", Droit.values());
		model.addAttribute("password2", password2);
		model.addAttribute("action", "/client/create");
		model.addAttribute("method", "POST");
		model.addAttribute("title","client.inscription.entete.create");

		password2 = Client.get_SHA_512_SecurePassword(password2);
		if(!passwordValid(client.getPassword(), password2)){
			return "/pages/client/create";
		}
		
		if (resultClient.hasErrors()) {
			return "/pages/client/create";
		}
		clientService.save(client);
		return "/pages/home";
	}
	
	@PutMapping("/client/update/{email}")
	public String postUpdate(
			@PathVariable String email,
			@Valid @ModelAttribute(name = "client") Client client, BindingResult resultClient,
			@Valid @ModelAttribute(name = "password2") String password2, ModelMap model) {
		
		model.addAttribute("lstCivilite", Civilite.values());
		model.addAttribute("lstDroit", Droit.values());
		model.addAttribute("password2", password2);
		model.addAttribute("action", "/client/update/"+email);
		model.addAttribute("method", "PUT");
		model.addAttribute("title","client.inscription.entete.update");
		String passwordBDD = clientService.getOne(email).getPassword();

		//l'utilisateur à modifier le mots de passe
		if(!passwordBDD.equals(client.getPassword())){
			password2 = Client.get_SHA_512_SecurePassword(password2);
		}
		//nouveau mot de passe 
		if(!passwordValid(client.getPassword(), password2)){
			
			return "/pages/client/create";
		}
		
		if (resultClient.hasErrors()) {
			return "/pages/client/create";
		}
		clientService.save(client);
		return "/pages/home";
	}
	
	@PutMapping("/client/modification")
	public String modification(@Valid @ModelAttribute(name = "client") Client client, BindingResult resultClient,
		@Valid @ModelAttribute(name = "password2") String password2, ModelMap model){
		model.addAttribute("lstCivilite", Civilite.values());
		model.addAttribute("password2", password2);
		model.addAttribute("action", "/client/modification");
		model.addAttribute("method", "PUT");
		model.addAttribute("title","client.inscription.entete.update");
		model.addAttribute("boutton","client.inscription.update");

		String passwordBDD = clientService.getOne(client.getEmail()).getPassword();
		client.setDroit(Droit.RULE_USER);
		//l'utilisateur à modifier le mots de passe
		if(!passwordBDD.equals(client.getPassword())){
			password2 = Client.get_SHA_512_SecurePassword(password2);
		}
		//nouveau mot de passe 
		if(!passwordValid(client.getPassword(), password2)){	
			return "/pages/client/modification";
		}
		
		if (resultClient.hasErrors()) {
			return "/pages/client/modification";
		}
		clientService.save(client);
		return "/pages/home";
	}
	
	public boolean passwordValid(String password, String confirmPassword) {
			return password.equals(confirmPassword);

	}
}