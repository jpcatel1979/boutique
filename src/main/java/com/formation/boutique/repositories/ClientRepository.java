package com.formation.boutique.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.boutique.entities.Client;
@Repository
public interface ClientRepository extends CrudRepository<Client, String>{

}
