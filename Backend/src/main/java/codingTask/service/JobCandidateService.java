package codingTask.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import codingTask.model.JobCandidate;
import codingTask.model.Skill;
import codingTask.repository.JobCandidateRepository;

@Service
public class JobCandidateService extends AbstractService<JobCandidate, JobCandidateRepository>{

	public JobCandidateService(JobCandidateRepository repository) {
		super(repository);
	}
	
	public List<JobCandidate> findByFullName(String fullName){
		return repository.findCandidateByName(fullName);
	}
	
	
}
