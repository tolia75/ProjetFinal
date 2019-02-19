package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse {

	@Column(name="rue")
	private String rue;
	@Column(name="code_postal")
	private String CP;
	@Column(name="ville")
	private String ville;
	
}
