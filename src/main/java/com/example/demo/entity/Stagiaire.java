package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.Version;

@Entity
@Table(name="stagiaire")
@SequenceGenerator(name="seqgenerator", sequenceName="seq_generator",allocationSize=1,initialValue=1)
public class Stagiaire {

	@GeneratedValue(generator="seqgenerator",strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	@Column(name="coordonnees")
	private String coordonnees;
	@Embedded
	private Adresse adresse;
	private Ordinateur ordinateur;
	@Version
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
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Ordinateur getOrdinateur() {
		return ordinateur;
	}
	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
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
