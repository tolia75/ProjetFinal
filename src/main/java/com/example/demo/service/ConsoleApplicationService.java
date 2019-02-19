package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class ConsoleApplicationService implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncode;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		/*// TODO Auto-generated method stub
		List<User> users = userRepo.findAll();
		for(User u : users) {
			u.setPassword(passwordEncode.encode(u.getPassword()));
			userRepo.save(u);
		}*/
	}

	

}
