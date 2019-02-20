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

import com.example.demo.entity.Formateur;
import com.example.demo.entity.Stagiaire;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.FormateurRepository;
import com.example.demo.repository.StagiaireRepository;
import com.fasterxml.jackson.annotation.JsonView;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/formateur")
public class FormateurRestController {

	@Autowired
	private FormateurRepository formateurRepository;
	

	@JsonView(JsonViews.common.class)
	@GetMapping("/list")
	public ResponseEntity<List<Formateur>>findAll(){
		return new ResponseEntity<List<Formateur>>(formateurRepository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.StagiaireWithOrdinateur.class)
	@GetMapping("/")
	public List<Formateur>findAllWith(){
		List<Formateur> formateurs = new ArrayList();
		return formateurs = formateurRepository.findAll();
	}
	
	@JsonView(JsonViews.StagiaireWithOrdinateur.class)
	@GetMapping("/{id}")
	public ResponseEntity<Formateur> findById(@PathVariable(name = "id") Integer id) {
		Optional<Formateur> opt = formateurRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Formateur>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@PostMapping("/insert")
	private ResponseEntity<Void> insertFormateur(@Valid @RequestBody Formateur formateur, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			formateurRepository.save(formateur);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/personne/{id}").buildAndExpand(formateur.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.CREATED);
		}

	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable(name = "id") Integer id) {
		Optional<Formateur>formateur = formateurRepository.findById(id);
		formateur.get().setMatieres(null);
		formateur.get().setModules(null);
		formateurRepository.save(formateur.get());
		formateurRepository.deleteById(id);
	}
	
	@PutMapping("/stagiaire")
	private Formateur update(@Valid @RequestBody Formateur formateur, BindingResult br) {
		if (br.hasErrors()) {
			return null;
		} else {
			Optional<Formateur> opt = formateurRepository.findById(formateur.getId());
			if (opt.isPresent()) {
				Formateur personneEnBase = opt.get();
				formateur.setVersion(personneEnBase.getVersion());
				return formateurRepository.save(formateur);
			} else {
				return null;
			}

		}
	}
}
