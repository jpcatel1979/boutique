package com.formation.boutique.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Commande {
	@Id
	@NotNull
	private Long idCommande;
	@NotNull
	private Integer code;
	@DateTimeFormat
	private Date date;
	
	@ManyToOne
	private Client client;
	@ManyToOne
	private Article article;
	
	public Commande(@NotNull Long idCommande, @NotNull Integer code, Date date, Client client, Article article) {
		
		this.idCommande = idCommande;
		this.code = code;
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
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
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
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}

}
