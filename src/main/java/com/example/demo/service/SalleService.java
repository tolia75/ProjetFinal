package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.SalleRepository;
import com.example.demo.repository.StagiaireRepository;

@Service
public class SalleService {

@Autowired
private SalleRepository salleRepository;

}
