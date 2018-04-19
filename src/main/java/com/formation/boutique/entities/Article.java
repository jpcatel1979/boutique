package com.formation.boutique.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
}
