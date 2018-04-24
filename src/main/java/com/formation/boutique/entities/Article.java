package com.formation.boutique.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Article {
	@Id
	private Long code;
	@NotBlank
	private String nom;
	@NotNull
	@PositiveOrZero
	private Float prix;
	@NotNull
	@Column(columnDefinition = "text")
	private String description;
	@Positive
	private Integer nbVentes;
	@Column(length = 2)
	@PositiveOrZero
	private Integer promo;
	
	@OneToMany(mappedBy = "article")
	private Collection <Image> image = new ArrayList<>();
	

	@ManyToOne
	@NotNull
	private Categorie categorie ; 
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "Commande",
//		joinColumns = {@JoinColumn (name = "code")},
//		inverseJoinColumns = {@JoinColumn (name = "email")})
//	private Collection <Client> client;
	
	@OneToMany (mappedBy = "article")
	private Collection <Commande> commande = new ArrayList<>();

	
	public Article() {
}

	public Article(Long code, @NotNull String nom, @NotNull @PositiveOrZero @Size(min = 1, max = 5) Float prix,
		@NotNull String description, @Positive Integer nbVentes, @Size(min = 1, max = 2) Integer promo,
		Collection<Image> image, Categorie categorie, Collection<Commande> commande) {

	this.code = code;
	this.nom = nom;
	this.prix = prix;
	this.description = description;
	this.nbVentes = nbVentes;
	this.promo = promo;
	this.image = image;
	this.categorie = categorie;
	this.commande = commande;
}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNbVentes() {
		return nbVentes;
	}

	public void setNbVentes(Integer nbVentes) {
		this.nbVentes = nbVentes;
	}

	public Integer getPromo() {
		return promo;
	}

	public void setPromo(Integer promo) {
		this.promo = promo;
	}

	public Collection<Image> getImage() {
		return image;
	}

	public void setImage(Collection<Image> image) {
		this.image = image;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Collection<Commande> getCommande() {
		return commande;
	}

	public void setCommande(Collection<Commande> commande) {
		this.commande = commande;
	}
	
}
