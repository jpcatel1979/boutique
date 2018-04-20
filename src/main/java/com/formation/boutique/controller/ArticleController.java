package com.formation.boutique.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.formation.boutique.entities.Article;
import com.formation.boutique.entities.Categorie;
import com.formation.boutique.entities.Image;
import com.formation.boutique.services.ArticleService;
import com.formation.boutique.services.CategorieService;

@Controller
public class ArticleController {

	private final ArticleService articleService;
	private final CategorieService categorieService;

	@Autowired
	public ArticleController(ArticleService articleService, CategorieService categorieService) {

		this.articleService = articleService;
		this.categorieService = categorieService;
	}
	
	
	@GetMapping("/article/create")
	public String getCreate(ModelMap model){
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("article", new Article());
		model.addAttribute("image", new Image());
		model.addAttribute("lstCategorie", categorieService.getAll() );
		model.addAttribute("action", "/article/create");
		
		return "pages/article/form";
	}

	@PostMapping("/article/create")
	public String save(
			@Valid @ModelAttribute(name = "article") Article article, BindingResult articleResult,
			@Valid @ModelAttribute(name = "categorie") Categorie categorie, BindingResult categorieResult,
			ModelMap model ){
		/*if(categorie.getNom().equals("nouvelle")){
			return "redirect:/categorie/create";
		}*/
		
		System.out.println(model);
		
		
		if(articleResult.hasErrors() || categorieResult.hasErrors()){
			return "/pages/article/form";
		}
		
		articleService.save(article);
		return "redirect:/";
	}
}
