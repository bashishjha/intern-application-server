package com.bashishjha.interns;

import com.bashishjha.interns.repository.MentorRepository;
import com.bashishjha.interns.service.ProjectAllocationService;
import com.bashishjha.interns.service.ProjectAllocationServiceImpl;

public class InternsApplicationTests {

	
	private MentorRepository mentorRepository;

	
	private ProjectAllocationService projectAllocationService = new ProjectAllocationServiceImpl();

	
	public void allocateProjectCannotAllocateTest() throws Exception {

		

	}

	
	public void allocateProjectMentorNotFoundTest() throws Exception {
	

	}
}