package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
	
	
	

}
