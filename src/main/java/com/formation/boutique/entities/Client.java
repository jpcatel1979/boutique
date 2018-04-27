package com.formation.boutique.entities;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Client {
	@Id
	@NotBlank
	@NotNull
	@Email
	@Column(length = 150)
	private String email;
	@NotBlank
	@NotNull
	@Column(length = 150)
	private String nom;
	@NotBlank
	private String prenom;
	@NotBlank
	@NotNull
	private String password;

	private String numAdresse;
	@NotBlank
	private String rueAdresse;
	@NotNull
	@Positive
	private Integer cpAdresse;
	@NotNull
	private String compAdresse;
	@NotNull
	@Size(min = 1, max = 50)
	private String villeAdresse;
	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateNaissance;
	@NotNull
	@Column(length = 10)
	@Size(min = 10, max = 10)
	private String telephone;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(20)")
	private Civilite civilite;
	

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(20)")
	private Droit droit;
	
	
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
			@NotNull @Size(min = 10, max = 10) String telephone, @NotNull Civilite civilite, Droit droit,
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
		this.droit = droit;
		this.commande = commande;
	}
	
	public static String get_SHA_512_SecurePassword(String passwordToHash) {
        String generatedPassword = null;
        String salt = "olprog";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
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
		this.password = Client.get_SHA_512_SecurePassword(password);
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

	
	public Droit getDroit() {
		return droit;
	}

	public void setDroit(Droit droit) {
		this.droit = droit;
	}

	public Collection<Commande> getCommande() {
		return commande;
	}

	public void setCommande(Collection<Commande> commande) {
		this.commande = commande;
	}

	@Override
	public String toString() {
		return "Client [email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password
				+ ", numAdresse=" + numAdresse + ", rueAdresse=" + rueAdresse + ", cpAdresse=" + cpAdresse
				+ ", compAdresse=" + compAdresse + ", villeAdresse=" + villeAdresse + ", dateNaissance=" + dateNaissance
				+ ", telephone=" + telephone + ", civilite=" + civilite + ", droit=" + droit + "]";
	}

}
