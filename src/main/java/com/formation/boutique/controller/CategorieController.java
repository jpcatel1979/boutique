package com.formation.boutique.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.formation.boutique.entities.Categorie;
import com.formation.boutique.services.ArticleService;
import com.formation.boutique.services.CategorieService;


@Controller
public class CategorieController {
CategorieService categorieService;
ArticleService articleService;


@Autowired
 public CategorieController(CategorieService categorieService, ArticleService articleService) {
	this.categorieService = categorieService;
	this.articleService = articleService;
}


	@GetMapping("/categorie/create")
	public String getCreate(ModelMap model) {
		
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("title","Ajouter Categorie");
		model.addAttribute("action","/categorie/create" );
		model.addAttribute("method","POST");
		return "pages/categorie/form";
	}
	
	@GetMapping("/categorie/delete")
	public String getDelete(ModelMap model) {
		
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("title","Supprimer Categorie");
		model.addAttribute("action","/categorie/delete");
		model.addAttribute("method","DELETE");
		return "pages/categorie/form";
	}
	
	@GetMapping("/categorie/update")
	public String getUpdate(ModelMap model) {
		
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("title","Mise a jour Categorie");
		model.addAttribute("action","/categorie/update");
		model.addAttribute("method","PUT");
		return "pages/categorie/form";
	}
	
	
	@GetMapping("/categorie/list")
	public String getAll(ModelMap model){
		model.addAttribute("lstCategorie",categorieService.getAll());
		model.addAttribute("title","Lister Categorie");

		return"pages/categorie/list";
	}
	
	@PostMapping("/categorie/create")
	public String save(
			@Valid @ModelAttribute(name = "categorie") Categorie categorie, BindingResult categorieResult,
			ModelMap model ){
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("action","/categorie/create" );
		model.addAttribute("title","Ajouter Categorie");
		model.addAttribute("method","POST");


		
		if(categorieResult.hasErrors()){
			return "/pages/categorie/form";
		}
		if(!(categorie.getId() == null)){
			categorie.setParent(categorieService.getOne(categorie.getId()));
			categorie.setId(null);
		}
		categorieService.save(categorie);
		return "redirect:/";
		
	}
	
	@PutMapping("categorie/update")
	public String update( Categorie categorie, BindingResult categorieResult, ModelMap model){
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("title","Mise à jour Categorie");
		model.addAttribute("action","/categorie/update");
		model.addAttribute("method","PUT");
		if(categorie.getNom().equals("")){
			return "/pages/categorie/form";
		}
		categorie.setParent(categorieService.getOne(categorie.getId()).getParent());
		categorieService.save(categorie);
		return "redirect:/";
	}
	
	@DeleteMapping("/categorie/delete")
	public String delete(ModelMap model, Categorie categorie ){
		model.addAttribute("action","/categorie/delete" );
		model.addAttribute("title","Supprimer Categorie");
		model.addAttribute("method","DELETE");
		System.out.println(articleService.countByCategorie(categorie.getId()));
		Categorie cat = new Categorie();
		cat = categorieService.getOne(categorie.getId());
		//categorieService.delete(categorie.getId());
		return "redirect:/";
	}
	
	
	
}