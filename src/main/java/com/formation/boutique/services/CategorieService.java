package com.formation.boutique.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.boutique.entities.Categorie;
import com.formation.boutique.repositories.CategorieRepository;

@Service
public class CategorieService {

	private final CategorieRepository categorieRepository;

	@Autowired
	public CategorieService(CategorieRepository categorieRepository) {

		this.categorieRepository = categorieRepository;
	}

	public Iterable<Categorie> getAll(){
		return categorieRepository.findAll();
	}
	
	public Categorie save(final Categorie categorie){
		return categorieRepository.save(categorie);
	}
	
	public Categorie getOne(final Long id){
		Categorie parent = new Categorie();
		parent = categorieRepository.findById(id).get(); 
		return parent;
	}

	public void delete(final Long id) {
		categorieRepository.deleteById(id);
		
	}
	
}