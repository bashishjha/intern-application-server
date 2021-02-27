package com.bashishjha.interns.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bashishjha.interns.dto.MentorDTO;
import com.bashishjha.interns.dto.ProjectDTO;
import com.bashishjha.interns.exception.InternException;
import com.bashishjha.interns.service.ProjectAllocationService;

@RestController
@RequestMapping("/interns")
public class ProjectAllocationAPI {
	@Autowired
	ProjectAllocationService projectAllocationService;
	
	@Autowired
	Environment environment;

	// add new project along with mentor details
	@PostMapping("/project")
	public ResponseEntity<String> allocateProject(@RequestBody ProjectDTO project) throws InternException {
		Integer id=projectAllocationService.allocateProject(project);
		String success=environment.getProperty("API.ALLOCATION_SUCCESS")+id;
		return new ResponseEntity<>(success,HttpStatus.CREATED);
	}

	// get mentors based on idea owner
	@GetMapping("/mentor/{numberOfProjectsMentored}")
	public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable Integer numberOfProjectsMentored) throws InternException {
		List<MentorDTO>list=projectAllocationService.getMentors(numberOfProjectsMentored);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// update the mentor of a project
	@PutMapping("/project/{ projectId }/{ mentorId }”")
	public ResponseEntity<String> updateProjectMentor(@PathVariable Integer projectId, @PathVariable Integer mentorId) throws InternException {
		projectAllocationService.updateProjectMentor(projectId, mentorId);
		String success=environment.getProperty("API.PROJECT_UPDATE_SUCCESS");
		return new ResponseEntity<>(success,HttpStatus.OK);
	}

	// delete a project
	@DeleteMapping("/project/{ projectId }")
	public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) throws InternException {
		projectAllocationService.deleteProject(projectId);
		String success=environment.getProperty("API.PROJECT_DELETE_SUCCESS");
		return new ResponseEntity<>(success,HttpStatus.OK);
	}

}
