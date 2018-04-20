package com.formation.boutique.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.boutique.entities.Image;
@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

}
