package com.example.demo.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.ModuleRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/module")
public class ModuleRestController {
	
	@Autowired
	ModuleRepository moduleRepo;

}
