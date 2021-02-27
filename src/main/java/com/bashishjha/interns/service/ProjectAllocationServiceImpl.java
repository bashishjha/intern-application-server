package com.bashishjha.interns.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bashishjha.interns.dto.MentorDTO;
import com.bashishjha.interns.dto.ProjectDTO;
import com.bashishjha.interns.entity.Mentor;
import com.bashishjha.interns.entity.Project;
import com.bashishjha.interns.exception.InternException;
import com.bashishjha.interns.repository.MentorRepository;
import com.bashishjha.interns.repository.ProjectRepository;

@Service(value = "projectAllocationService")
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {
	
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	MentorRepository mentorRepository;

	@Override
	public Integer allocateProject(ProjectDTO projectDTO) throws InternException {
		Optional<Mentor> mentorOptional=mentorRepository.findById(projectDTO.getMentorDTO().getMentorId());
		if(mentorOptional.isEmpty()) {
			throw new InternException("Service.MENTOR_NOT_FOUND");
		}
		Mentor mentor=mentorOptional.get();
		if(mentor.getNumberOfProjectsMentored()>=3) {
			throw new InternException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Project project=new Project();
		project.setProjectName(projectDTO.getProjectName());
		project.setIdeaOwner(projectDTO.getIdeaOwner());
		project.setMentor(mentor);
		Integer temp=mentor.getNumberOfProjectsMentored();
		mentor.setNumberOfProjectsMentored(temp+1);
		project.setReleaseDate(projectDTO.getReleaseDate());
		Project project2=projectRepository.save(project);
		return project2.getProjectId();
	}

	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InternException {
		List<Mentor> list=mentorRepository.findByNumberOfProjectsMentored(numberOfProjectsMentored);
		if(list.isEmpty()) {
			throw new InternException("Service.MENTOR_NOT_FOUND");
		}
		List<MentorDTO> dtos=new ArrayList<MentorDTO>();
		for(Mentor mentor:list) {
			MentorDTO dto=new MentorDTO();
			dto.setMentorId(mentor.getMentorId());
			dto.setMentorName(mentor.getMentorName());
			dto.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InternException {
		Optional<Mentor> mentorOptional=mentorRepository.findById(mentorId);
		if(mentorOptional.isEmpty()) {
			throw new InternException("Service.MENTOR_NOT_FOUND");
		}
		Mentor mentor=mentorOptional.get();
		if(mentor.getNumberOfProjectsMentored()>=3) {
			throw new InternException("Service.CANNOT_ALLOCATE_PROJECT");
		}
		Optional<Project> projectOptional=projectRepository.findById(projectId);
		if(projectOptional.isEmpty()) {
			throw new InternException("Service.PROJECT_NOT_FOUND");
		}
		Project project=projectOptional.get();
		project.setMentor(mentor);
		Integer temp=mentor.getNumberOfProjectsMentored();
		mentor.setNumberOfProjectsMentored(temp+1);
		projectRepository.save(project);
		
	}

	@Override
	public void deleteProject(Integer projectId) throws InternException {
		Optional<Project> projectOptional=projectRepository.findById(projectId);
		if(projectOptional.isEmpty()) {
			throw new InternException("Service.PROJECT_NOT_FOUND");
		}
		Project project=projectOptional.get();
		Mentor mentor=project.getMentor();
		project.setMentor(null);
		projectRepository.delete(project);
		Integer temp=mentor.getNumberOfProjectsMentored();
		mentor.setNumberOfProjectsMentored(temp-1);
	}
}