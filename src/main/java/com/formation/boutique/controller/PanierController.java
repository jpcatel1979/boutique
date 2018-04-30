package com.formation.boutique.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.formation.boutique.entities.Article;
import com.formation.boutique.services.ArticleService;

@Controller
public class PanierController {
	private ArticleService articleService;

	public PanierController(ArticleService articleService) {
		this.articleService = articleService;
	}
 
	@GetMapping("/panier/ajouter/{codeArticle}")
	public String AjouterArticle(ModelMap model, @PathVariable Long codeArticle, HttpSession httpSession) {
		List<Article> lstArticlePanier = (List<Article>) httpSession.getAttribute("panier");
		lstArticlePanier.add(articleService.getOne(codeArticle));
		httpSession.setAttribute("panier", lstArticlePanier);
		return "redirect:/article/list";
	}

	@GetMapping("/client/panier")
	public String ListArticle(ModelMap model, HttpSession httpSession) {
		List<Article> lstArticlePanier = (List<Article>) httpSession.getAttribute("panier");
		Float totalCommande = (float) 0;

		for (Article articlePanier : lstArticlePanier) {
			totalCommande += articlePanier.getPrix();
		}
		httpSession.setAttribute("totalCommande", totalCommande);
		return "/pages/panier/panier";
	}

	@GetMapping("/panier/supprimer/{codeArticle}")
	public String supprimerArticlePanier(ModelMap model, @PathVariable Long codeArticle, HttpSession httpSession,
			HttpServletRequest request) {
		List<Article> lstArticlePanier = (List<Article>) httpSession.getAttribute("panier");
		for (Article articlePanier : lstArticlePanier) {
			if (articlePanier.getCode().equals(codeArticle)) {
				lstArticlePanier.remove(articlePanier);
				break;
			}
		}
		httpSession.setAttribute("panier", lstArticlePanier);
		// return "redirect:" + request.getHeader(name: "Referer");
		return "redirect:/client/panier";
	}

}