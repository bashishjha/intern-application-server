package com.bashishjha.interns.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bashishjha.interns.entity.Mentor;

public interface MentorRepository extends CrudRepository<Mentor, Integer>{
	List<Mentor> findByNumberOfProjectsMentored(Integer numberOfProjectsMentored);
}
