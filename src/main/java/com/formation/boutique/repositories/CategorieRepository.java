package com.formation.boutique.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.boutique.entities.Categorie;

@Repository
public interface CategorieRepository extends CrudRepository<Categorie, Long> {

	@Modifying
	@Transactional
	void deleteByNom(String nom);

	@Modifying
	@Transactional
	Integer countByParent(Categorie categorie);

}
