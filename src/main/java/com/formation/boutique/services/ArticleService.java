package com.formation.boutique.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.boutique.entities.Article;
import com.formation.boutique.entities.Categorie;
import com.formation.boutique.repositories.ArticleRepository;

@Service
public class ArticleService {

	private final ArticleRepository articleRepository;

	@Autowired
	public ArticleService(ArticleRepository articleRepository) {

		this.articleRepository = articleRepository;
	}
	
	public Article getOne(final Long id){
		Article article	= articleRepository.findById(id).get() ; 
		return article;
	}
	
	public Iterable<Article> getAll(){
		return articleRepository.findAll();
	}
	
	public Article save(Article article){
		return articleRepository.save(article);
	}

	public void delete(@Valid Article article) {
		articleRepository.deleteById(article.getCode());
	}

	public int countByCategorie(final Long idCategorie) {
		return countByCategorie(idCategorie);
		
	}
	
}
