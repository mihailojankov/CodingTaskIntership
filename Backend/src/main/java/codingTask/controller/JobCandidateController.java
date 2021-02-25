package codingTask.controller;


import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import codingTask.DTO.JobCandidateDTO;
import codingTask.model.JobCandidate;
import codingTask.service.JobCandidateService;

@Controller
@CrossOrigin
@RequestMapping(path = "/jobCandidate")
public class JobCandidateController extends AbstractController<JobCandidate, JobCandidateDTO, JobCandidateService>{

	public JobCandidateController(JobCandidateService service) {
		super(service, JobCandidateDTO.class);
	}
	
	@PostMapping(path = "/findByFullName")
	public ResponseEntity<ArrayList<JobCandidateDTO>> findByFullName(@RequestBody String fullName){
		System.out.println(fullName);
		ArrayList<JobCandidateDTO> matchingList = new ArrayList<JobCandidateDTO>();
		
		for(JobCandidate c: service.findByFullName(fullName)) {
			matchingList.add(mm.map(c, JobCandidateDTO.class));
		}
		
		return new ResponseEntity<ArrayList<JobCandidateDTO>>(matchingList, HttpStatus.OK);
	}
	
	
	
	
	
	
}
