package codingTask.controller;


import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping(path = "/findByFullName")
	public ResponseEntity<ArrayList<JobCandidateDTO>> findByFullName(@RequestBody String fullName){
		ArrayList<JobCandidateDTO> matchingList = new ArrayList<JobCandidateDTO>();
		
		for(JobCandidate c: service.findByFullName(fullName)) {
			matchingList.add(mm.map(c, JobCandidateDTO.class));
		}
		
		return new ResponseEntity<ArrayList<JobCandidateDTO>>(matchingList, HttpStatus.OK);
	}
	
	
	
	
	
	
}
