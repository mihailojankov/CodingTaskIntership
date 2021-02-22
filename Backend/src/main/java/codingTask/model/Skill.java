package codingTask.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Skill extends AbstractModel{
	
	@Column(nullable = false)
	String name;
	
	@ManyToMany(mappedBy = "skillList")
	List<JobCandidate> candidates;

	public Skill(String name, List<JobCandidate> candidates) {
		super();
		this.name = name;
		this.candidates = candidates;
	}
	
	public Skill() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<JobCandidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<JobCandidate> candidates) {
		this.candidates = candidates;
	}
	
	
}
