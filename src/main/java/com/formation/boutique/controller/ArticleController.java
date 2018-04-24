package com.formation.boutique.controller;

import java.util.Collection;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ArticleController(ArticleService articleService, CategorieService categorieService,
			ImageService imageService) {

		this.articleService = articleService;
		this.categorieService = categorieService;
		this.imageService = imageService;
	}

	@GetMapping("/article/create")
	public String getCreate(ModelMap model) {
		model.addAttribute("categorie", new Categorie());
		model.addAttribute("article", new Article());
		model.addAttribute("image", new Image());
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("action", "/article/create");
		model.addAttribute("method", "POST");
		model.addAttribute("title", "Ajout Article");

		return "pages/article/form";
	}

	@GetMapping("article/update/{codeArticle}")
	public String getUpdate(@PathVariable Long codeArticle, ModelMap model) {
		
		Article article = articleService.getOne(codeArticle);
		if (article.equals(null)) {
			return "pages/";
		}

		Image image = new Image();
		Collection<Image> lstImage = article.getImage();

		for (Image img : lstImage) {
			image.setLien(img.getLien());
		}

		model.addAttribute("article", article);
		model.addAttribute("image", image);
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("categorie", article.getCategorie());
		model.addAttribute("article", articleService.getOne(codeArticle));
		model.addAttribute("title", "Mise à jour Article");
		model.addAttribute("method", "PUT");

		return "pages/article/form";

	}

	@GetMapping("article/delete/{codeArticle}")
	public String getDelete(@PathVariable Long codeArticle, ModelMap model) {

		Article article = articleService.getOne(codeArticle);
		if (article.equals(null)) {
			return "pages/";
		}

		Image image = new Image();
		Collection<Image> lstImage = article.getImage();

		for (Image img : lstImage) {
			image.setLien(img.getLien());
			image.setId(img.getId());
			image.setArticle(article);
		}

		model.addAttribute("article", article);
		model.addAttribute("image", image);
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("categorie", article.getCategorie());
		model.addAttribute("article", articleService.getOne(codeArticle));
		model.addAttribute("title", "Suppresion Article");
		model.addAttribute("method", "DELETE");

		return "pages/article/form";

	}

	@GetMapping("article/list")
	public String getAll(ModelMap model) {
		model.addAttribute("lstArticle", articleService.getAll());
		model.addAttribute("title", "Liste des Articles");
		return "pages/article/list";
	}

	@PostMapping("/article/create")
	public String save(@Valid @ModelAttribute(name = "article") Article article, BindingResult articleResult,
			@Valid @ModelAttribute(name = "image") Image image, BindingResult imageResult, ModelMap model) {
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("action", "/article/create");
		model.addAttribute("method", "POST");
		model.addAttribute("title", "Ajout Article");
		System.out.println(article);
		if (articleResult.hasErrors()) {
			return "/pages/article/form";
		}

		articleService.save(article);
		image.setArticle(article);
		imageService.save(image);

		return "redirect:/";
	}

	@PutMapping("article/update/{codeArticle}")
	public String update(@Valid @ModelAttribute(name = "article") Article article, BindingResult articleResult,
			@Valid @ModelAttribute(name = "image") Image image, BindingResult imageResult,
			@PathVariable Long codeArticle, ModelMap model) {
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("action", "/article/update/" + codeArticle);
		model.addAttribute("title", "Mise à jour Article");
		model.addAttribute("method", "PUT");

		if (articleResult.hasErrors()) {
			return "/pages/article/form";
		}

		articleService.save(article);
		// image.setArticle(article);
		// imageService.save(image);

		return "redirect:/";
	}

	@DeleteMapping("article/delete/{codeArticle}")
	public String delete(@Valid @ModelAttribute(name = "article") Article article, BindingResult articleResult,
			@Valid @ModelAttribute(name = "image") Image image, BindingResult imageResult,
			@PathVariable Long codeArticle, ModelMap model) {
		model.addAttribute("lstCategorie", categorieService.getAll());
		model.addAttribute("action", "/article/delete/" + codeArticle);
		model.addAttribute("title", "Suppresion Article");
		model.addAttribute("method", "DELETE");
		imageService.delete(image);
		articleService.delete(article);
		return "redirect:/";

	}

}
