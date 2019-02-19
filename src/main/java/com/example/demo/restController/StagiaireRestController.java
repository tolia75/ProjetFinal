package com.example.demo.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Stagiaire;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.StagiaireRepository;
import com.fasterxml.jackson.annotation.JsonView;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/stagiaire")
public class StagiaireRestController {

	@Autowired
	private StagiaireRepository stagiaireRepository;
	
	@JsonView(JsonViews.common.class)
	@GetMapping(value= {"/",""})
	public ResponseEntity<List<Stagiaire>>findAll(){
		return new ResponseEntity<List<Stagiaire>>(stagiaireRepository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Stagiaire> findById(@PathVariable(name = "id") Integer id) {
		Optional<Stagiaire> opt = stagiaireRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Stagiaire>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
}
