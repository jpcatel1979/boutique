package com.formation.boutique.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Categorie {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(length = 100)
	private String nom;
	
	@OneToOne
	private Categorie categorie;
	
	@OneToMany(mappedBy="categorie")
	private Collection <Article> article = new ArrayList<Article> ();
	
	
}
