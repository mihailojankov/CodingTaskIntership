package codingTask.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import codingTask.model.JobCandidate;
import codingTask.model.Skill;
import codingTask.repository.SkillRepository;

@Service
public class SkillService extends AbstractService<Skill, SkillRepository>{

	public SkillService(SkillRepository repository) {
		super(repository);
	}
	
	public ArrayList<JobCandidate> findBySkills(List<Skill> skills){
		ArrayList<Skill> listAll = (ArrayList<Skill>) repository.findAll();
		ArrayList<JobCandidate> listMatching = new ArrayList<>();
		
		for(Skill s: listAll){
			for(Skill sk: skills) {
				if(s.getName().equals(sk.getName())) {
					listMatching.addAll(s.getCandidates());
				}
			}
		}
		//Proba bez duplikata
		ArrayList<JobCandidate> listWithoutDuplicates = (ArrayList<JobCandidate>) listMatching.stream().distinct().collect(Collectors.toList());
		
		
		return listWithoutDuplicates;
	}
	
	
	
}
