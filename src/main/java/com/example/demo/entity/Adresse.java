package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.example.demo.entity.jsonViews.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Adresse {
	@JsonView(JsonViews.common.class)
	@Column(name="rue")
	private String rue;
	@JsonView(JsonViews.common.class)
	@Column(name="code_postal")
	private String CP;
	@JsonView(JsonViews.common.class)
	@Column(name="ville")
	private String ville;
	
}
