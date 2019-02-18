package com.example.demo.entity;


public class Stagiaire {

	private Integer id;
	private String nom;
	private String prenom;
	private String coordonnées;
	private Adresse adresse;
	private Integer version;
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
	public String getCoordonnées() {
		return coordonnées;
	}
	public void setCoordonnées(String coordonnées) {
		this.coordonnées = coordonnées;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public Stagiaire(String nom, String prenom, String coordonnées, Adresse adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.coordonnées = coordonnées;
		this.adresse = adresse;
	}
	public Stagiaire() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stagiaire other = (Stagiaire) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
