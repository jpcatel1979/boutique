package com.formation.boutique.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.boutique.entities.Article;
@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {

	@Modifying
	@Transactional
	public Article getByNom(String nom);
}
