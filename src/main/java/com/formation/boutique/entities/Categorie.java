package com.formation.boutique.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Categorie {
	@Id
	@GeneratedValue
	@Column(name = "id_categorie")
	private Long id;
	@NotNull
	@Column(length = 100)
	private String nom;
}
