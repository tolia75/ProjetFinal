package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

import com.example.demo.entity.jsonViews.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "programme")
@SequenceGenerator(name="seqProgramme",sequenceName="seq_programme",allocationSize=1,initialValue=1)
public class Programme {

	@JsonView(JsonViews.common.class)
	@Id
	@Column(name = "id")
	@GeneratedValue(generator="seqProgramme",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "titre", length = 50)
	@JsonView(JsonViews.common.class)
	private String titre;
	@OneToMany(mappedBy = "programme")
	@JsonView(JsonViews.ProgrammeWithAll.class)
	private Set<Promotion> promotions;
	@ManyToMany(mappedBy = "programmes")
	@JsonView(JsonViews.ProgrammeWithAll.class)
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
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		Programme other = (Programme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
