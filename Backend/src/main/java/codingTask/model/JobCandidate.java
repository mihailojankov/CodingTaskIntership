package codingTask.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class JobCandidate extends AbstractModel{
	
	@Column(nullable = false)
	String fullName;
	@Column(nullable = false)
	String email;
	@Column(nullable = false)
	String contactNumber;
	@Column(nullable = false)
	LocalDate dateOfBirth;
	
	@ManyToMany
	@JoinTable(name = "candidate_skills", 
	joinColumns = @JoinColumn(name = "job_candidate_id"), 
	inverseJoinColumns = @JoinColumn(name = "skill_id"))
	List<Skill> skillList;

	public JobCandidate(String fullName, String email, String contactNumber, LocalDate dateOfBirth,
			List<Skill> skillList) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.skillList = skillList;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Skill> skillList) {
		this.skillList = skillList;
	}

	
	
	
}
