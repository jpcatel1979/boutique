package com.formation.boutique.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.formation.boutique.entities.Article;
import com.formation.boutique.entities.Categorie;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

	@Modifying
	@Transactional
	public Article getByNom(String nom);

}