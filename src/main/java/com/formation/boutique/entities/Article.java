package com.formation.boutique.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Article {
	@Id
	private Long code;
	@NotNull
	private String nom;
	@NotNull
	@PositiveOrZero
	@Size(min = 1, max = 5)
	private Float prix;
	@NotNull
	@Column(columnDefinition = "text")
	private String description;
	@Positive
	private Integer nbVentes;
	@Column(length = 2)
	@Size(min = 1, max = 2)
	private Integer promo;
	
	@OneToMany(mappedBy = "article")
	private Collection <Image> image = new ArrayList<>();
	
	@ManyToOne
	private Categorie categorie ; 
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "Commande",
//		joinColumns = {@JoinColumn (name = "code")},
//		inverseJoinColumns = {@JoinColumn (name = "email")})
//	private Collection <Client> client;
	
	@OneToMany (mappedBy = "article")
	private Collection <Commande> commande = new ArrayList<>();
}
