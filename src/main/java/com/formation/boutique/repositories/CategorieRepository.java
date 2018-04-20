package com.formation.boutique.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.boutique.entities.Categorie;
@Repository
public interface CategorieRepository extends CrudRepository<Categorie, Long>{

}
