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
	private Integer idCommande;
	@NotNull
	private Integer code;
	@DateTimeFormat
	private Date date;
	
	@ManyToOne
	private Client client;
	@ManyToOne
	private Article article;
}
