package codingTask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import codingTask.model.JobCandidate;

@Repository
public interface JobCandidateRepository extends InheritRepository<JobCandidate>{
	@Query("SELECT c FROM JobCandidate c WHERE c.fullName = :name")
	public List<JobCandidate> findCandidateByName(@PathVariable("name") String name);
	//Stavljena lista zbog kandidata sa istim imenom i prezimenom
}
