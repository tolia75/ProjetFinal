package com.example.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entity.jsonViews.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="promotion")
@SequenceGenerator(name="seqPromotion",sequenceName="seq_promotion",allocationSize=1,initialValue=1)
public class Promotion {
	@Id
	@Column(name = "nom", length = 50)
	@JsonView(JsonViews.common.class)
	@GeneratedValue(generator="seqPromotion",strategy=GenerationType.SEQUENCE)
	private String nom;
	@Column(name = "debut")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonView(JsonViews.common.class)
	private Date debut;
	@Column(name = "fin")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonView(JsonViews.common.class)
	private Date fin;
	@OneToMany(mappedBy = "promotion")
	@JsonView(JsonViews.common.class)
	private Set<Stagiaire> stagiaires;
	@OneToMany(mappedBy = "promotion")
	@JsonView(JsonViews.common.class)
	private Set<Module> modules;
	@ManyToOne
	@JoinColumn(name = "programme_titre")
	@JsonView(JsonViews.common.class)
	private Programme programme;
	@Version
	private int version;
	
	
	
	public Promotion(Date debut, Date fin, String nom) {
		super();
		this.debut = debut;
		this.fin = fin;
		this.nom = nom;
	}
	public Promotion() {
		super();
	}
	public Date getDebut() {
		return debut;
	}
	public void setDebut(Date debut) {
		this.debut = debut;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Set<Stagiaire> getStagiaires() {
		return stagiaires;
	}
	public void setStagiaires(Set<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	public Programme getProgramme() {
		return programme;
	}
	public void setProgramme(Programme programme) {
		this.programme = programme;
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
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		Promotion other = (Promotion) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	
	
	
	
}
