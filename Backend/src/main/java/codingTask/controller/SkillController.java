package codingTask.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import codingTask.DTO.JobCandidateDTO;
import codingTask.DTO.SkillDTO;
import codingTask.model.JobCandidate;
import codingTask.model.Skill;
import codingTask.service.SkillService;

@Controller
@CrossOrigin
@RequestMapping(path = "/skill")
public class SkillController extends AbstractController<Skill, SkillDTO, SkillService>{

	public SkillController(SkillService service) {
		super(service, SkillDTO.class);
	}
	
	@PostMapping (path = "/findBySkills")
	public ResponseEntity<ArrayList<JobCandidateDTO>> findByFullName(@RequestBody List<Skill> skills){
		ArrayList<JobCandidateDTO> matchingList = new ArrayList<JobCandidateDTO>();
		
		for(JobCandidate c: service.findBySkills(skills)) {
			matchingList.add(mm.map(c, JobCandidateDTO.class));
		}
		return new ResponseEntity<ArrayList<JobCandidateDTO>>(matchingList, HttpStatus.OK);
	}

}
