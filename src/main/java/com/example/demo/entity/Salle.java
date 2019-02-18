package com.example.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="salle")
@SequenceGenerator(name="seqSalle", sequenceName="seq_salle",allocationSize=1,initialValue=1)
public class Salle {
	@Id
	@GeneratedValue(generator="seqSalle", strategy=GenerationType.SEQUENCE)
	private Integer id;
	@JsonView(com.example.demo.entity.jsonViews.JsonView.common.class)
	@Column(name="code")
	private String code;
	@JsonView(com.example.demo.entity.jsonViews.JsonView.common.class)
	@Column(name="disponibilite")
	private boolean disponibilite;
	@JsonView(com.example.demo.entity.jsonViews.JsonView.common.class)
	@Column(name="capacite")
	private Integer capacite;
	@JsonView(com.example.demo.entity.jsonViews.JsonView.common.class)
	@OneToMany
	@JoinColumn(name="salle")
	private List<Module> modules;
	@JsonView(com.example.demo.entity.jsonViews.JsonView.common.class)
	@OneToOne
	@JoinColumn(name="salle")
	private VideoProjecteur videoProjecteur;
	@Version
	private Integer version;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isDisponibilite() {
		return disponibilite;
	}
	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}
	public Integer getCapacite() {
		return capacite;
	}
	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public VideoProjecteur getVideoProjecteur() {
		return videoProjecteur;
	}
	public void setVideoProjecteur(VideoProjecteur videoProjecteur) {
		this.videoProjecteur = videoProjecteur;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Salle() {
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
		Salle other = (Salle) obj;
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
	
	

}
