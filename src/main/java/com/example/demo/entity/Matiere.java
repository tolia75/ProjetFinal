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
@Table(name = "matiere")
public class Matiere {

	@Id
	@Column(name = "serial", length = 50)
	@JsonView(JsonViews.Common.class)
	private String nomMatiere;
	@OneToMany(mappedBy = "matiere")
	@JsonView(JsonViews.Common.class)
	private Set<Programme> programmes;
	@OneToMany(mappedBy = "matiere")
	@JsonView(JsonViews.Common.class)
	private Set<Formateur> formateurs;
	@OneToMany(mappedBy = "matiere")
	@JsonView(JsonViews.Common.class)
	private Set<Module> modules;
	@Version
	private int version;
	public Matiere(String nomMatiere) {
		super();
		this.nomMatiere = nomMatiere;
	}
	public Matiere() {
		super();
	}
	public String getNomMatiere() {
		return nomMatiere;
	}
	public void setNomMatiere(String nomMatiere) {
		this.nomMatiere = nomMatiere;
	}
	public Set<Programme> getProgrammes() {
		return programmes;
	}
	public void setProgrammes(Set<Programme> programmes) {
		this.programmes = programmes;
	}
	public Set<Formateur> getFormateurs() {
		return formateurs;
	}
	public void setFormateurs(Set<Formateur> formateurs) {
		this.formateurs = formateurs;
	}
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
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
		result = prime * result + ((nomMatiere == null) ? 0 : nomMatiere.hashCode());
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
		Matiere other = (Matiere) obj;
		if (nomMatiere == null) {
			if (other.nomMatiere != null)
				return false;
		} else if (!nomMatiere.equals(other.nomMatiere))
			return false;
		return true;
	}
	
	

	
}
