package com.formation.boutique.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.boutique.entities.Commande;
@Repository
public interface CommandeRepository extends CrudRepository<Commande, Long> {

}
