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

import com.example.demo.entity.Promotion;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.PromotionRepository;
import com.fasterxml.jackson.annotation.JsonView;



@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/promotion")
public class PromotionRestController {
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	@JsonView(JsonViews.PromotionWithAll.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Promotion>> findAllPromotion() {
		return new ResponseEntity<List<Promotion>>(promotionRepository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.PromotionWithAll.class)
	@GetMapping("/{id}")
	public ResponseEntity<Promotion> findById(@PathVariable(name = "id") Integer id) {
		Optional<Promotion> opt = promotionRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Promotion>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@JsonView(JsonViews.PromotionWithProgramme.class)
	@GetMapping("/programme")
	public List<Promotion> findAllPromotionWithProgramme() {
		return promotionRepository.findAll();
	}
	
	@JsonView(JsonViews.PromotionWithModule.class)
	@GetMapping("/module")
	public List<Promotion> findAllPromotionWithModule() {
		return promotionRepository.findAll();
	}
	
	@JsonView(JsonViews.PromotionWithStagiaire.class)
	@GetMapping("/stagiaire")
	public List<Promotion> findAllPromotionWithStagiaire() {
		return promotionRepository.findAll();
	}
	
	
	@JsonView(JsonViews.common.class)
	@PostMapping("/")
	private ResponseEntity<Void> insertPromotion(@Valid @RequestBody Promotion promotion, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			promotionRepository.save(promotion);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/promotion/{id}").buildAndExpand(promotion.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.CREATED);
		}

	}
	
	@JsonView(JsonViews.common.class)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") Integer id) {
		promotionRepository.deleteById(id);
	}
	
	@JsonView(JsonViews.common.class)
	@PutMapping("/promotion")
	private Promotion update(@Valid @RequestBody Promotion promotion, BindingResult br) {
		if (br.hasErrors()) {
			return null;
		} else {
			Optional<Promotion> opt = promotionRepository.findById(promotion.getId());
			if (opt.isPresent()) {
				Promotion promotionEnBase = opt.get();
				promotion.setVersion(promotionEnBase.getVersion());
				return promotionRepository.save(promotion);
			} else {
				return null;
			}

		}
	}
}




	
	

