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
import com.formation.boutique.services.ImageService;

@Controller
public class ArticleController {

	private final ArticleService articleService;
	private final CategorieService categorieService;
	private final ImageService imageService;


	@Autowired
	public ArticleController(ArticleService articleService, CategorieService categorieService, ImageService imageService) {

		this.articleService = articleService;
		this.categorieService = categorieService;
		this.imageService = imageService;
	}
	
	
	@GetMapping("/article/create")
	public String getCreate(ModelMap model){
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("article", new Article());
		model.addAttribute("image", new Image());
		model.addAttribute("lstCategorie", categorieService.getAll() );
		model.addAttribute("action", "/article/create");
		model.addAttribute("title","Ajout Article");
		
		return "pages/article/form";
	}
	
	@GetMapping("article/list")
	public String getAll(ModelMap model){
		model.addAttribute("lstArticle", articleService.getAll());
		model.addAttribute("title","Liste des Articles");
		return "pages/article/list";
	}
	
	
	@PostMapping("/article/create")
	public String save(
			@Valid @ModelAttribute(name = "article") Article article, BindingResult articleResult,
			@Valid @ModelAttribute(name = "image") Image image, BindingResult imageResult,
			ModelMap model ){
		model.addAttribute("lstCategorie", categorieService.getAll() );
		model.addAttribute("action", "/article/create");
		model.addAttribute("title","Ajout Article");
		
		if(articleResult.hasErrors()) {
			return "/pages/article/form";
		}
		
		articleService.save(article);
		image.setArticle(article);
		imageService.save(image);
		
		return "redirect:/";
	}
}
