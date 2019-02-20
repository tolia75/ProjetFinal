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

import com.example.demo.entity.Formateur;
import com.example.demo.entity.Matiere;
import com.example.demo.entity.Module;
import com.example.demo.entity.Stagiaire;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.FormateurRepository;
import com.example.demo.repository.MatiereRepository;
import com.example.demo.repository.ModuleRepository;
import com.example.demo.repository.StagiaireRepository;
import com.fasterxml.jackson.annotation.JsonView;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/formateur")
public class FormateurRestController {

	@Autowired
	private FormateurRepository formateurRepository;
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private MatiereRepository matiereRepository;
	

	@JsonView(JsonViews.common.class)
	@GetMapping("/list")
	public ResponseEntity<List<Formateur>>findAll(){
		return new ResponseEntity<List<Formateur>>(formateurRepository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.FormateurWithOrdinateur.class)
	@GetMapping("/")
	public List<Formateur>findAllWith(){
		List<Formateur> formateurs = new ArrayList();
		return formateurs = formateurRepository.findAll();
	}
	
	@JsonView(JsonViews.FormateurWithOrdinateur.class)
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
		if (formateur.isPresent()) {
			Formateur formateurEnBase=formateur.get();
			Set<Matiere> matieres = formateurEnBase.getMatieres();
			Set<Module> modules = formateurEnBase.getModules();
			for (Matiere matiere : matieres) {
				for (Formateur formateur2: matiere.getFormateurs()) {
					if (formateur2.getId()==formateurEnBase.getId()) {
						matiere.getFormateurs().remove(formateurEnBase);
						matiereRepository.save(matiere);
					}
					matiereRepository.save(matiere);
				}	
			}
			for (Module module : modules) {
				module.setFormateur(null);
				moduleRepository.save(module);
			}
			formateurRepository.save(formateurEnBase);
			formateurRepository.deleteById(id);
		}else {
			
		}

	}
	
	@PutMapping("/update")
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
