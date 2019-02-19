package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="modules")
@SequenceGenerator(name="seqModule", sequenceName="seq_module", allocationSize=1, initialValue=1)
public class Module {
	
	@Id
	@GeneratedValue(generator="seqModule", strategy= GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_debut")
	@DateTimeFormat(pattern="yyyy-MM--dd")
	private Date debut;
	@Temporal(TemporalType.DATE)
	@Column(name="date_fin")
	@DateTimeFormat(pattern="yyyy-MM--dd")
	private Date fin;
	
	@ManyToOne
	@JoinColumn(name="formateur_id")
	private Formateur formateur;
	@ManyToOne
	@JoinColumn(name="salle_id")
	private Salle salle;
	@Enumerated(EnumType.STRING)
	private Niveau niveau;
	@ManyToOne
	@JoinColumn(name="matiere_id")
	private Matiere matiere;
	@ManyToOne
	@JoinColumn(name="promotion_id")
	private Promotion promotion;
	@Version
	private Integer version;
	
	public Module(Date debut, Date fin) {
		super();
		this.debut = debut;
		this.fin = fin;
	}

	public Module() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
	
}
