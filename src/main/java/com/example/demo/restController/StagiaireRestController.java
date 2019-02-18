package com.example.demo.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.StagiaireRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/stagiaire")
public class StagiaireRestController {

	//@Autowired
	//private StagiaireRepository stagiaireRepository;
}
