package com.example.demo.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.example.demo.entity.Stagiaire;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.StagiaireRepository;
import com.example.demo.service.StagiaireService;
import com.fasterxml.jackson.annotation.JsonView;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/stagiaire")
public class StagiaireRestController {

	@Autowired
	private StagiaireRepository stagiaireRepository;
	
	@JsonView(JsonViews.common.class)
	@GetMapping("/list")
	public ResponseEntity<List<Stagiaire>>findAll(){
		return new ResponseEntity<List<Stagiaire>>(stagiaireRepository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.StagiaireWithOrdinateur.class)
	@GetMapping("/")
	public List<Stagiaire>findAllWith(){
		List<Stagiaire> stagiaires = new ArrayList();
		return stagiaires = stagiaireRepository.findAll();
	}
	
	@JsonView(JsonViews.StagiaireWithOrdinateur.class)
	@GetMapping("/{id}")
	public ResponseEntity<Stagiaire> findById(@PathVariable(name = "id") Integer id) {
		Optional<Stagiaire> opt = stagiaireRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Stagiaire>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@PostMapping("/insert")
	private ResponseEntity<Void> insertStagiaire(@Valid @RequestBody Stagiaire stagiaire, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			stagiaireRepository.save(stagiaire);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/personne/{id}").buildAndExpand(stagiaire.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.CREATED);
		}

	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(name = "id") Integer id) {
		Optional<Stagiaire>stagiaire = stagiaireRepository.findById(id);
		if (stagiaire.isPresent()) {
			Stagiaire stagiaireEnBase = stagiaire.get();
			stagiaireEnBase.setOrdinateur(null);
			stagiaireEnBase.setPromotion(null);
			stagiaireRepository.save(stagiaireEnBase);
			stagiaireRepository.deleteById(id);
		}
		else {
			
		}


	}
	
	@PutMapping("/stagiaire")
	private Stagiaire update(@Valid @RequestBody Stagiaire stagiaire, BindingResult br) {
		if (br.hasErrors()) {
			return null;
		} else {
			Optional<Stagiaire> opt = stagiaireRepository.findById(stagiaire.getId());
			if (opt.isPresent()) {
				Stagiaire personneEnBase = opt.get();
				stagiaire.setVersion(personneEnBase.getVersion());
				return stagiaireRepository.save(stagiaire);
			} else {
				return null;
			}

		}
	}
}
