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

import com.example.demo.entity.Module;
import com.example.demo.entity.jsonViews.JsonViews;
import com.example.demo.repository.ModuleRepository;
import com.fasterxml.jackson.annotation.JsonView;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/module")
public class ModuleRestController {
	
	@Autowired
	ModuleRepository moduleRepo;
	
	@JsonView(JsonViews.common.class)
	@GetMapping(value= {"","/","/list"})
	public ResponseEntity<List<Module>> findAllModule(){
		return new ResponseEntity<List<Module>>(moduleRepo.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Module> findById(@PathVariable(name="id") Integer id){
		Optional<Module> opt = moduleRepo.findById(id);
		if(opt.isPresent()) {
			return new ResponseEntity<Module>(opt.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	//@JsonView(JsonViews.ModuleWithFormateur.class)
	@GetMapping("/formateur")
	public List<Module> findAllModuleWithFormateur(){
		return moduleRepo.findAll();
	}
	
	//@JsonView(JsonViews.ModuleWithSalle.class)
	@GetMapping("/salle")
	public List<Module> findAllModuleWithSalle(){
		return moduleRepo.findAll();
	}
	
	//@JsonView(JsonViews.ModuleWithPromotion.class)
	@GetMapping("/promotion")
	public List<Module> findAllModuleWithPromotion(){
		return moduleRepo.findAll();
	}
	
	//@JsonView(JsonViews.ModuleWithMatiere.class)
	@GetMapping("/matiere")
	public List<Module> findAllModuleWithMatiere(){
		return moduleRepo.findAll();
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Void> insertModule(@Valid @RequestBody Module module, BindingResult br, UriComponentsBuilder uCB){
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		else {
			moduleRepo.save(module);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(uCB.path("/rest/module/{id}").buildAndExpand(module.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name="id") Integer id) {
		moduleRepo.deleteById(id);
	}
	
	@PutMapping("/updtaeModule")
	public Module updateModule(@Valid @RequestBody Module module, BindingResult br) {
		if(br.hasErrors()) {
			return null;
		}
		else {
			Optional<Module> opt = moduleRepo.findById(module.getId());
			if(opt.isPresent()) {
				Module ModuleEnBase = opt.get();
				module.setVersion(ModuleEnBase.getVersion());
				return moduleRepo.save(module);
			}
			else {
				return null;
			}
		}
	}

}
