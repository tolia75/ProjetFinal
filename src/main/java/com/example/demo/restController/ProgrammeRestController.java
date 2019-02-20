package com.example.demo.restController;

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

import com.example.demo.entity.Matiere;
import com.example.demo.entity.Programme;
import com.example.demo.entity.Promotion;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.MatiereRepository;
import com.example.demo.repository.ProgrammeRepository;
import com.example.demo.repository.PromotionRepository;
import com.fasterxml.jackson.annotation.JsonView;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/programme")
public class ProgrammeRestController {
	
	@Autowired
	private ProgrammeRepository programmeRepository;
	@Autowired
	private PromotionRepository promotionRepository;
	@Autowired
	private MatiereRepository matiereRepository;
	
	@JsonView(JsonViews.common.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Programme>> findAllProgramme() {
		return new ResponseEntity<List<Programme>>(programmeRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Programme> findById(@PathVariable(name = "id") Integer id) {
		Optional<Programme> opt = programmeRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Programme>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	
	@JsonView(JsonViews.ProgrammeWithPromotion.class)
	@GetMapping("/promotion")
	public List<Programme> findAllProgrammeWithPromotion() {
		return programmeRepository.findAll();
	}
	
	@JsonView(JsonViews.ProgrammeWithMatiere.class)
	@GetMapping("/matiere")
	public List<Programme> findAllProgrammeWithMatiere() {
		return programmeRepository.findAll();
	}

	@JsonView(JsonViews.common.class)
	@PostMapping("/")
	private ResponseEntity<Void> insertProgramme(@Valid @RequestBody Programme programme, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			programmeRepository.save(programme);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/programme/{id}").buildAndExpand(programme.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.CREATED);
		}

	}
	
	@JsonView(JsonViews.common.class)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") Integer id) {
		Optional<Programme> programme = programmeRepository.findById(id);
		if (programme.isPresent()) {
			Programme programmeEnBase = programme.get();
			Set<Promotion> promotions=programmeEnBase.getPromotions();
			Set<Matiere> matieres=programmeEnBase.getMatieres();
				for(Promotion p : promotions) {
					p.setProgramme(null);
					promotionRepository.save(p);
				}
				for (Matiere m : matieres) {
					m.setProgrammes(null);
					matiereRepository.save(m);
				}
			
			programmeEnBase.setPromotions(null);
			programmeRepository.save(programmeEnBase);
			programmeRepository.deleteById(id);
		}
		
	}

	@JsonView(JsonViews.common.class)
	@PutMapping("/programme")
	private Programme update(@Valid @RequestBody Programme programme, BindingResult br) {
		if (br.hasErrors()) {
			return null;
		} else {
			Optional<Programme> opt = programmeRepository.findById(programme.getId());
			if (opt.isPresent()) {
				Programme programmeEnBase = opt.get();
				programme.setVersion(programmeEnBase.getVersion());
				return programmeRepository.save(programme);
			} else {
				return null;
			}

		}
	}
}




