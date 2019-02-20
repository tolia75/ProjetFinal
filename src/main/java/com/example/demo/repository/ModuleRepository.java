package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer>{
	
	@Query("select m from Module m left join fetch m.formateur")
	List<Module> findAllModuleWithFormateur();
	
}
