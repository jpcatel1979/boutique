package com.formation.boutique.entities;

public enum Civilite {
	MONSIEUR("titre.monsieur"), MADAME("titre.madame");
	private final String titre;

	private Civilite(String titre) {
		this.titre = titre;
	}

	public String getTitre() {
		return titre;
	}
}
