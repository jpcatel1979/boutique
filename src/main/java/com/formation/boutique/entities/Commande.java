package com.formation.boutique.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Commande {
	@Id
	@GeneratedValue(/*strategy = GenerationType.IDENTITY*/)
	private Long idCommande;
	@DateTimeFormat
	private Date date;
	@ManyToOne
	private Client client;
	@ManyToMany
	private Collection<Article> article = new ArrayList<>();

	public Commande(Long idCommande, Date date, Client client,
			Collection<Article> article) {

		this.idCommande = idCommande;
		this.date = date;
		this.client = client;
		this.article = article;
	}

	public Commande() {
	}

	public Long getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Article> getArticle() {
		return article;
	}

	public void setArticle(Collection<Article> article) {
		this.article = article;
	}
}
