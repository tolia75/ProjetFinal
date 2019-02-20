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

import com.example.demo.entity.Matiere;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.MatiereRepository;
import com.fasterxml.jackson.annotation.JsonView;





@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/matiere")
public class MatiereRestController {
	@Autowired
	private MatiereRepository matiereRepository;

	@JsonView(JsonViews.common.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Matiere>> findAllMatiere() {
		return new ResponseEntity<List<Matiere>>(matiereRepository.findAll(), HttpStatus.OK);
	}
/*
	@JsonView(JsonViews.common.class)
	@GetMapping("/{nomMatiere}")
	public ResponseEntity<Matiere> findById(@PathVariable(name = "nomMatiere") String nomMatiere) {
		Optional<Matiere> opt = matiereRepository.findById(nomMatiere);
		if (opt.isPresent()) {
			return new ResponseEntity<Matiere>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}*/

	

	private ResponseEntity<Void> insertMatiere(Matiere matiere, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			matiereRepository.save(matiere);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/matiere/{nomMatiere}").buildAndExpand(matiere.getNomMatiere()).toUri());
			return new ResponseEntity<>(header, HttpStatus.CREATED);
		}

	}
/*
	@DeleteMapping("/{nomMatiere}")
	public void delete(@PathVariable(name = "nomMatiere") String nomMatiere) {
		matiereRepository.deleteById(nomMatiere);
	}*/

/*
	private Matiere update(Matiere matiere, BindingResult br) {
		if (br.hasErrors()) {
			return null;
		} else {
			Optional<Matiere> opt = matiereRepository.findById(matiere.getNomMatiere());
			if (opt.isPresent()) {
				Matiere matiereEnBase = opt.get();
				matiere.setVersion(matiereEnBase.getVersion());
				return matiereRepository.save(matiere);
			} else {
				return null;
			}

		}
	}*/
}



