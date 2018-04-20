package com.formation.boutique.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.formation.boutique.entities.Categorie;
import com.formation.boutique.services.CategorieService;

@Controller
public class CategorieController {
CategorieService categorieService;


@Autowired
 public CategorieController(CategorieService categorieService) {
	this.categorieService = categorieService;
}


	@GetMapping("/categorie/create")
	public String getCreate(ModelMap model) {
		
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("lstCategorie", categorieService.getAll());
		return "pages/categorie/form";
	}
	
	@PostMapping("/categorie/create")
	public String save(
			@Valid @ModelAttribute(name = "categorie") Categorie categorie, BindingResult categorieResult,
			ModelMap model ){
		model.addAttribute("lstCategorie", categorieService.getAll());
		
		if(categorieResult.hasErrors()){
			return "/pages/categorie/form";
		}
		if(!(categorie.getId() == null)){
			categorie.setCategorie(categorieService.getOne(categorie.getId()));
			categorie.setId(null);
		}
		categorieService.save(categorie);
		return "redirect:/";
		
	}
	
}