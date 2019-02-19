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
	
	@JsonView(JsonViews.common.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Promotion>> findAllPromotion() {
		return new ResponseEntity<List<Promotion>>(promotionRepository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.common.class)
	@GetMapping("/{nom}")
	public ResponseEntity<Promotion> findById(@PathVariable(name = "nom") String nom) {
		Optional<Promotion> opt = promotionRepository.findById(nom);
		if (opt.isPresent()) {
			return new ResponseEntity<Promotion>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	


	private ResponseEntity<Void> insertPromotion(Promotion promotion, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			promotionRepository.save(promotion);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/promotion/{nom}").buildAndExpand(promotion.getNom()).toUri());
			return new ResponseEntity<>(header, HttpStatus.CREATED);
		}

	}

	@DeleteMapping("/{nom}")
	public void delete(@PathVariable(name = "nom") String nom) {
		promotionRepository.deleteById(nom);
	}


	private Promotion update(Promotion promotion, BindingResult br) {
		if (br.hasErrors()) {
			return null;
		} else {
			Optional<Promotion> opt = promotionRepository.findById(promotion.getNom());
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




	
	

