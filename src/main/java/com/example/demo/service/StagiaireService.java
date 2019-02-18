package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Stagiaire;
import com.example.demo.repository.StagiaireRepository;

@Service
public class StagiaireService{

	@Autowired
private StagiaireRepository stagiaireRepository;
}
