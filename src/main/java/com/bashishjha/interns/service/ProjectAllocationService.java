package com.bashishjha.interns.service;

import java.util.List;

import com.bashishjha.interns.dto.MentorDTO;
import com.bashishjha.interns.dto.ProjectDTO;
import com.bashishjha.interns.exception.InternException;

public interface ProjectAllocationService {

	public Integer allocateProject(ProjectDTO projectAllocation) throws InternException;

	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InternException;

	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InternException;

	public void deleteProject(Integer projectId) throws InternException;
}
