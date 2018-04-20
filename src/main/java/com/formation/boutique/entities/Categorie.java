package com.formation.boutique.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Categorie {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank
	@Column(length = 100)
	private String nom;
	
	@OneToOne
	private Categorie categorie;
	
	@OneToMany(mappedBy="categorie")
	private Collection <Article> article = new ArrayList<Article> ();


	public Categorie(Long id, @NotNull String nom, Categorie categorie, Collection<Article> article) {

		this.id = id;
		this.nom = nom;
		this.categorie = categorie;
		this.article = article;
	}

	public Categorie() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Collection<Article> getArticle() {
		return article;
	}

	public void setArticle(Collection<Article> article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + ", categorie=" + categorie + ", article=" + article + "]";
	}
	
	
}
