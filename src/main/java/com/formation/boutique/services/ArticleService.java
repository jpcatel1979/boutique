package com.formation.boutique.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.boutique.entities.Article;
import com.formation.boutique.repositories.ArticleRepository;

@Service
public class ArticleService {

	private final ArticleRepository articleRepository;

	@Autowired
	public ArticleService(ArticleRepository articleRepository) {

		this.articleRepository = articleRepository;
	}
	
	public Iterable<Article> getAll(){
		return articleRepository.findAll();
	}
	
	public Article save(Article article){
		return articleRepository.save(article);
	}
	
}
