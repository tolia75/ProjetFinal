package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.criteria.CriteriaBuilder.In;

@Entity
@Table(name="techicien")
@SequenceGenerator(name="seqTechnicien", sequenceName="seq_technicien",allocationSize=1,initialValue=1)
public class Technicien {
	
	@Id
	@GeneratedValue(generator="seqTechnicien",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	@Column(name="coordonnees")
	private String coordonnees;
	@Embedded
	private Adresse adresse;
	@Version
	private Integer version;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
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
	public String getCoordonnees() {
		return coordonnees;
	}
	public void setCoordonnees(String coordonnees) {
		this.coordonnees = coordonnees;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Technicien() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		Technicien other = (Technicien) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	
}
