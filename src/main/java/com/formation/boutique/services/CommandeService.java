package com.formation.boutique.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.boutique.entities.Commande;
import com.formation.boutique.repositories.CommandeRepository;

@Service
public class CommandeService {

	private final CommandeRepository commandeRepository;

	@Autowired
	public CommandeService(CommandeRepository commandeRepository) {
		super();
		this.commandeRepository = commandeRepository;
	}
	
	public Iterable<Commande> getAll(){
		return commandeRepository.findAll();
	}
	
	public Commande save(Commande c){
		return commandeRepository.save(c);
	}
	
}
