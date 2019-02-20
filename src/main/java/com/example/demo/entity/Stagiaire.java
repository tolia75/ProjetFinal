package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.example.demo.entity.jsonViews.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="stagiaire")
@SequenceGenerator(name="seqStagiaire", sequenceName="seq_stagiaire",allocationSize=1,initialValue=1)
public class Stagiaire {

	@Id
	@GeneratedValue(generator="seqStagiaire", strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	@JsonView(JsonViews.common.class)
	private Integer id;
	@Column(name="nom")
	@JsonView(JsonViews.common.class)
	private String nom;
	@Column(name="prenom")
	@JsonView(JsonViews.common.class)
	private String prenom;
	@Column(name="coordonnee")
	@JsonView(JsonViews.common.class)
	private String coordonnee;
	@Embedded
	@JsonView(JsonViews.common.class)
	private Adresse adresse;
	@JsonView(JsonViews.StagiaireWithOrdinateur.class)
	@ManyToOne
	@JoinColumn(name="promotion")
	private Promotion promotion;
	@JsonView(JsonViews.StagiaireWithOrdinateur.class)
	@OneToOne
	@JoinColumn(name="ordinateur")
	private Ordinateur ordinateur;
	@Version
	private Integer version;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getCoordonnee() {
		return coordonnee;
	}
	public void setCoordonnee(String coordonnee) {
		this.coordonnee = coordonnee;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
//	public Promotion getPromotion() {
//		return promotion;
//	}
//	public void setPromotion(Promotion promotion) {
//		this.promotion = promotion;
//	}
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
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	
	
	
	
	
	
}
