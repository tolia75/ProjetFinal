package com.example.demo.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entity.Ordinateur;
import com.example.demo.entity.Stagiaire;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.OrdinateurRepository;
import com.example.demo.repository.StagiaireRepository;
import com.fasterxml.jackson.annotation.JsonView;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/ordinateur")
public class OrdinateurRestController {
	@Autowired
	private OrdinateurRepository ordinateurRepository;
	
	@JsonView(JsonViews.common.class)
	@GetMapping("/list")
	public ResponseEntity<List<Ordinateur>>findAll(){
		return new ResponseEntity<List<Ordinateur>>(ordinateurRepository.findAll(), HttpStatus.OK);
	}
	
	
	
	@JsonView(JsonViews.StagiaireWithOrdinateur.class)
	@GetMapping("/{id}")
	public ResponseEntity<Ordinateur> findById(@PathVariable(name = "id") Integer id) {
		Optional<Ordinateur> opt = ordinateurRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Ordinateur>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@PostMapping("/insert")
	private ResponseEntity<Void> insertOrdinateur(@Valid @RequestBody Ordinateur ordinateur, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			ordinateurRepository.save(ordinateur);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/ordinateur/{id}").buildAndExpand(ordinateur.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.CREATED);
		}

	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(name = "id") Integer id) {
		ordinateurRepository.deleteById(id);
	}
	
	@PutMapping("/update")
	private Ordinateur update(@Valid @RequestBody Ordinateur ordinateur, BindingResult br) {
		if (br.hasErrors()) {
			return null;
		} else {
			Optional<Ordinateur> opt = ordinateurRepository.findById(ordinateur.getId());
			if (opt.isPresent()) {
				Ordinateur ordinateurEnBase = opt.get();
				ordinateur.setVersion(ordinateurEnBase.getVersion());
				return ordinateurRepository.save(ordinateur);
			} else {
				return null;
			}

		}
	}
}
