package com.formation.boutique.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.formation.boutique.entities.Categorie;
import com.formation.boutique.services.CategorieService;

import ch.qos.logback.core.net.SyslogOutputStream;


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
	public String geUpdate(ModelMap model) {
		
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("title","Mise à jour Categorie");
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
	
	@DeleteMapping("/categorie/delete")
	public String delete(ModelMap model, Categorie categorie ){
		model.addAttribute("action","/categorie/delete" );
		model.addAttribute("title","Supprimer Categorie");
		model.addAttribute("method","DELETE");
		categorieService.delete(categorie.getId());
		return "redirect:/";
	}
	
	
	
}