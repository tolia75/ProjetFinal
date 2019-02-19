package com.example.demo.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/rest/user")
public class UserRestController {

	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/list")
	public ResponseEntity<List<User>> findAllUser(){
		return new ResponseEntity<List<User>>(userRepo.findAll(), HttpStatus.OK);
	}
	
}
