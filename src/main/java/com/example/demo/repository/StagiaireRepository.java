package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Stagiaire;

public interface StagiaireRepository extends JpaRepository<Stagiaire,Integer>{

	
	@Query("select s from Stagiaire s left join fetch s.ordinateur")
	public List<Stagiaire> findAllWithOrdinateurAndPromotion();
}
