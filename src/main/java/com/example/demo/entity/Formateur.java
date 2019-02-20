package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.Version;

import com.example.demo.entity.jsonViews.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="formateur")
@SequenceGenerator(name="seqFormateur", sequenceName="seq_formateur",allocationSize=1,initialValue=1)
public class Formateur {

	@Id
	@GeneratedValue(generator="seqFormateur",strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	@JsonView(JsonViews.common.class)
	private Integer id;
	@Column(name="prenom")
	@JsonView(JsonViews.common.class)
	private String prenom;
	@JsonView(JsonViews.common.class)
	@Column(name="nom")
	private String nom;
	@JsonView(JsonViews.common.class)
	@Column(name="coordonnees")
	private String coordonnees;
	@JsonView(JsonViews.common.class)
	@Embedded
	private Adresse	adresse;
	@ManyToMany(mappedBy="formateurs")
	@JsonView(JsonViews.FormateurWithOrdinateur.class)
	private	Set<Matiere> matieres;
	@JsonView(JsonViews.FormateurWithOrdinateur.class)
	@OneToMany(mappedBy="formateur")
	private Set<Module> modules;
	@javax.persistence.Version
	private Integer version;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public Formateur() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public Set<Matiere> getMatieres() {
		return matieres;
	}

	public void setMatieres(Set<Matiere> matieres) {
		this.matieres = matieres;
	}

	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
		Formateur other = (Formateur) obj;
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
