package com.formation.boutique.entities;

public enum Droit {
	ROLE_USER("role.user"),RULE_WEBMASTER("role.webmaster"),RULE_ADMIN("role.admin");
	private String valeur;

	private Droit(){};
	
	private Droit(String valeur){
		this.valeur = valeur;
	}

	public String getValeur() {
		return valeur;
	}
}
