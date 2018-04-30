package com.formation.boutique.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.formation.boutique.entities.Article;
import com.formation.boutique.entities.Client;
import com.formation.boutique.entities.Commande;
import com.formation.boutique.services.CommandeService;

@Controller
public class CommandeController {
	private CommandeService commandeService;

	@Autowired
	public CommandeController(CommandeService commandeService) {
		this.commandeService = commandeService;
	}

	@GetMapping("/commande")
	public String passerCommande(HttpSession httpSession, ModelMap model) {
		Client client = (Client) httpSession.getAttribute("client");
		List <Article> lstArticle = (List<Article>) httpSession.getAttribute("panier");
		if(client.getEmail() !=null || lstArticle.size() > 0){
			Commande commande = new Commande();
			commande.setClient(client);
			commande.setArticle(lstArticle);
			commande.setDate(new Date(0));
			commandeService.save(commande);
			List<Article> lstArticlePanier = (List<Article>) httpSession.getAttribute("panier");
			lstArticlePanier = new ArrayList <> ();
			httpSession.setAttribute("panier", lstArticlePanier );
			httpSession.setAttribute("totalCommande", 0);
		}
		
		
		return "/pages/home";
	}
}