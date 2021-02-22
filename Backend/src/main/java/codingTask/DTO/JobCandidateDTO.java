package codingTask.DTO;

import java.time.LocalDate;
import java.util.List;

public class JobCandidateDTO extends AbstractDTO{
	
	String fullName;
	String contactNumber;
	String email;
	LocalDate dateOfBirth;
	List<SkillDTO> skillList;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<SkillDTO> getSkillList() {
		return skillList;
	}
	public void setSkillList(List<SkillDTO> skillList) {
		this.skillList = skillList;
	}
	
	
	
}
