package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

import org.formation.demoBoot.entity.jsonview.JsonViews;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "programme")
public class Programme {
	@Id
	@Column(name = "titre", length = 50)
	@JsonView(JsonViews.Common.class)
	private String titre;
	@OneToMany(mappedBy = "programme")
	@JsonView(JsonViews.Common.class)
	private Set<Promotion> promotions;
	@OneToMany(mappedBy = "programme")
	@JsonView(JsonViews.Common.class)
	private Set<Matiere> matieres;
	@Version
	private int version;

	
	public Programme(String titre) {
		super();
		this.titre = titre;
	}
	public Programme() {
		super();
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Set<Promotion> getPromotions() {
		return promotions;
	}
	public void setPromotions(Set<Promotion> promotions) {
		this.promotions = promotions;
	}
	public Set<Matiere> getMatieres() {
		return matieres;
	}
	public void setMatieres(Set<Matiere> matieres) {
		this.matieres = matieres;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
		Programme other = (Programme) obj;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	
	
	

}
