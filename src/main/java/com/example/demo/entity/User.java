package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	private String userName;
	private String password;
	private boolean enable;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToOne(mappedBy="user")
	private Formateur formateur;
	
	@OneToOne(mappedBy="user")
	private Stagiaire stagiaire;
	
	@OneToOne(mappedBy="user")
	private Technicien technicien;
	
	@OneToOne(mappedBy="user")
	private Gestionnaire gestionnaire;
	
	
	public User(String userName, String password, boolean enable) {
		super();
		this.userName = userName;
		this.password = password;
		this.enable = enable;
	}


	public User() {
		super();
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnable() {
		return enable;
	}


	public void setEnable(boolean enable) {
		this.enable = enable;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Formateur getFormateur() {
		return formateur;
	}


	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}


	public Stagiaire getStagiaire() {
		return stagiaire;
	}


	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}


	public Technicien getTechnicien() {
		return technicien;
	}


	public void setTechnicien(Technicien technicien) {
		this.technicien = technicien;
	}


	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}


	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
	}
	
	
	

}
