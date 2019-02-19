package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer>{

}
