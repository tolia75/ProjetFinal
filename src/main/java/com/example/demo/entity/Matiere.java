package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Cascade;

import com.example.demo.entity.jsonViews.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name = "matiere")
@SequenceGenerator(name="seqMatiere",sequenceName="seq_matiere",allocationSize=1,initialValue=1)
public class Matiere {

	@JsonView(JsonViews.common.class)
	@Id
	@Column(name = "id")
	@GeneratedValue(generator="seqMatiere",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "nom_matiere", length = 50)
	@JsonView(JsonViews.common.class)
	private String nomMatiere;
	@ManyToMany()
	@JoinTable(name="programmes_matieres", joinColumns=@JoinColumn(name="matieres", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="programmes", referencedColumnName="id"))
	@JsonView(JsonViews.MatiereWithProgramme.class)
	private Set<Programme> programmes;
	@ManyToMany
	@JoinTable(name="formateurs_matieres", joinColumns=@JoinColumn(name="matieres", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="formateurs", referencedColumnName="id"))
	@JsonView(JsonViews.MatiereWithFormateur.class)
	private Set<Formateur> formateurs;
	@OneToMany(mappedBy = "matiere")
	@JsonView(JsonViews.MatiereWithModule.class)
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
		Matiere other = (Matiere) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

	
}
