package com.formation.boutique.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Client {
	@Id
	@Email
	@Column(length = 150)
	private String email;
	@NotNull
	@Column(length = 150)
	private String nom;
	@NotNull
	private String prenom;
	@NotNull
	private String password;
	@NotNull
	@Positive
	@Size(max = 5)
	private String numAdresse;
	@NotNull
	private String rueAdresse;
	@NotNull
	@Positive
	@Size(min = 5, max = 5)
	private Integer cpAdresse;
	@NotNull
	private String compAdresse;
	@NotNull
	@Size(min = 1, max = 50)
	private String villeAdresse;
	@PastOrPresent
	@DateTimeFormat
	private Date dateNaissance;
	@NotNull
	@Column(length = 10)
	@Size(min = 10, max = 10)
	private String Telephone;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(20)")
	private Civilite civilite;

}
