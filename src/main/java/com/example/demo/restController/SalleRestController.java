package com.example.demo.restController;

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

import com.example.demo.entity.Salle;
import com.example.demo.entity.Stagiaire;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.SalleRepository;
import com.example.demo.repository.StagiaireRepository;
import com.fasterxml.jackson.annotation.JsonView;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/salle")
public class SalleRestController 

	{@Autowired
		private SalleRepository salleRepository;
	
	@JsonView(JsonViews.common.class)
	@GetMapping(value= {"/",""})
	public ResponseEntity<List<Salle>>findAll(){
		return new ResponseEntity<List<Salle>>(salleRepository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Salle> findById(@PathVariable(name = "id") Integer id) {
		Optional<Salle> opt = salleRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Salle>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@PostMapping("/insert")
	private ResponseEntity<Void> insertSalle(@Valid @RequestBody Salle salle, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			salleRepository.save(salle);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/personne/{id}").buildAndExpand(salle.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.CREATED);
		}

	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(name = "id") Integer id) {
		salleRepository.deleteById(id);
	}
	
	@PutMapping("/stagiaire")
	private Salle update(@Valid @RequestBody Salle salle, BindingResult br) {
		if (br.hasErrors()) {
			return null;
		} else {
			Optional<Salle> opt = salleRepository.findById(salle.getId());
			if (opt.isPresent()) {
				Salle personneEnBase = opt.get();
				salle.setVersion(personneEnBase.getVersion());
				return salleRepository.save(salle);
			} else {
				return null;
			}

		}
	}
}
