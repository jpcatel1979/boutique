package com.formation.boutique.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Qualifier;

@Entity
public class Categorie {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	@Column(length = 100)
	private String nom;
	
	@OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = false)
	private Categorie parent;
	
	@OneToMany(mappedBy="categorie")
	private Collection <Article> article = new ArrayList<Article> ();


	public Categorie(Long id, @NotNull String nom, Categorie parent, Collection<Article> article) {

		this.id = id;
		this.nom = nom;
		this.parent = parent;
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
	@Qualifier("nom")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categorie getParent() {
		return parent;
	}

	public void setParent(Categorie parent) {
		this.parent = parent;
	}

	public Collection<Article> getArticle() {
		return article;
	}

	public void setArticle(Collection<Article> article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + ", parent=" + parent + ", article=" + article + "]";
	}
	
	
}
