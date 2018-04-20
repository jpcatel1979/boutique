package com.formation.boutique.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private String telephone;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(20)")
	private Civilite civilite;

	// @ManyToMany(cascade = CascadeType.ALL)
	// @JoinTable(name = "Commande",
	// joinColumns = {@JoinColumn (name = "email")},
	// inverseJoinColumns = {@JoinColumn (name = "code")})

	// private Collection <Article> article;
	@OneToMany(mappedBy = "client")
	private Collection<Commande> commande = new ArrayList<>();

	public Client() {

	}

	public Client(@Email String email, @NotNull String nom, @NotNull String prenom, @NotNull String password,
			@NotNull @Positive @Size(max = 5) String numAdresse, @NotNull String rueAdresse,
			@NotNull @Positive @Size(min = 5, max = 5) Integer cpAdresse, @NotNull String compAdresse,
			@NotNull @Size(min = 1, max = 50) String villeAdresse, @PastOrPresent Date dateNaissance,
			@NotNull @Size(min = 10, max = 10) String telephone, @NotNull Civilite civilite,
			Collection<Commande> commande) {
	
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.password = password;
		this.numAdresse = numAdresse;
		this.rueAdresse = rueAdresse;
		this.cpAdresse = cpAdresse;
		this.compAdresse = compAdresse;
		this.villeAdresse = villeAdresse;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
		this.civilite = civilite;
		this.commande = commande;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNumAdresse() {
		return numAdresse;
	}

	public void setNumAdresse(String numAdresse) {
		this.numAdresse = numAdresse;
	}

	public String getRueAdresse() {
		return rueAdresse;
	}

	public void setRueAdresse(String rueAdresse) {
		this.rueAdresse = rueAdresse;
	}

	public Integer getCpAdresse() {
		return cpAdresse;
	}

	public void setCpAdresse(Integer cpAdresse) {
		this.cpAdresse = cpAdresse;
	}

	public String getCompAdresse() {
		return compAdresse;
	}

	public void setCompAdresse(String compAdresse) {
		this.compAdresse = compAdresse;
	}

	public String getVilleAdresse() {
		return villeAdresse;
	}

	public void setVilleAdresse(String villeAdresse) {
		this.villeAdresse = villeAdresse;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
	}

	public Collection<Commande> getCommande() {
		return commande;
	}

	public void setCommande(Collection<Commande> commande) {
		this.commande = commande;
	}

}
