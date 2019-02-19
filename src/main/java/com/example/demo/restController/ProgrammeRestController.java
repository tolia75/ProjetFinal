package com.example.demo.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entity.Programme;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.ProgrammeRepository;
import com.fasterxml.jackson.annotation.JsonView;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/programme")
public class ProgrammeRestController {
	
	@Autowired
	private ProgrammeRepository programmeRepository;
	
	@JsonView(JsonViews.common.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Programme>> findAllProgramme() {
		return new ResponseEntity<List<Programme>>(programmeRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.common.class)
	@GetMapping("/{titre}")
	public ResponseEntity<Programme> findById(@PathVariable(name = "titre") String titre) {
		Optional<Programme> opt = programmeRepository.findById(titre);
		if (opt.isPresent()) {
			return new ResponseEntity<Programme>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	

	private ResponseEntity<Void> insertProgramme(Programme programme, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			programmeRepository.save(programme);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/programme/{titre}").buildAndExpand(programme.getTitre()).toUri());
			return new ResponseEntity<>(header, HttpStatus.CREATED);
		}

	}

	@DeleteMapping("/{titre}")
	public void delete(@PathVariable(name = "titre") String titre) {
		programmeRepository.deleteById(titre);
	}


	private Programme update(Programme programme, BindingResult br) {
		if (br.hasErrors()) {
			return null;
		} else {
			Optional<Programme> opt = programmeRepository.findById(programme.getTitre());
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




