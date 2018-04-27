package com.formation.boutique.entities;

public enum Civilite {
	MONSIEUR("titre.monsieur"), MADAME("titre.madame");
	private String titre;

	private Civilite(String titre) {
		this.titre = titre;
	}

	private Civilite() {
	}
	public String getTitre() {
		return titre;
	}

}
