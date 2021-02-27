package com.bashishjha.interns.repository;

import org.springframework.data.repository.CrudRepository;

import com.bashishjha.interns.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer>{
	
}
