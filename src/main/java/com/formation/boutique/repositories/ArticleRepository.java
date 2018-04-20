package com.formation.boutique.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.boutique.entities.Article;
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
	
	

}
