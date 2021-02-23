package codingTask.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import codingTask.model.JobCandidate;
import codingTask.model.Skill;
import codingTask.repository.JobCandidateRepository;
import codingTask.repository.SkillRepository;

class SkillServiceTest {

	@InjectMocks
	SkillService service;
	
	@Mock
	SkillRepository repo;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findAllTest() {
		
		ArrayList<Skill> list = new ArrayList<Skill>();
		
		Skill skill1 = new Skill("English", null);
		Skill skill2 = new Skill("Java", null);
		
		list.add(skill1);
		list.add(skill2);
		
		when(repo.findAll()).thenReturn(list);
		
		ArrayList<Skill> emptyList = (ArrayList<Skill>) service.findAll();
		
		assertEquals(2, emptyList.size());
		verify(repo, times(1)).findAll();
		
	}
	
	@Test
	public void findById() {
		when(repo.findById((long) 1)).
		thenReturn(Optional.of( new Skill("Java", null)));
		
		Skill s1 = service.findById((long)1);
		assertEquals(s1.getName(), "Java");
		
		
	}
	
	@Test 
	public void saveTest() {
		Skill skill1 = new Skill("Java", null);
		
		service.save(skill1);
		
		verify(repo, times(1)).save(skill1);
	}
	
	@Test 
	public void deleteTest() {
		Skill skill1 = new Skill("Java", null);
		
		service.save(skill1);
		service.deleteById((long)1);
		
		verify(repo, times(1)).save(skill1);
		verify(repo, times(1)).deleteById((long)1);
	}
	
	@Test
	public void findBySkillsTest() {
		ArrayList<Skill> list = new ArrayList<Skill>();
		JobCandidate candidate1 = new JobCandidate("Kandidat1", "email1", "123123", LocalDate.now(), null);
		JobCandidate candidate2 = new JobCandidate("Kandidat1", "email2", "123123", LocalDate.now(), null);
		
		ArrayList<JobCandidate> toAddList = new ArrayList<>();
		toAddList.add(candidate1);
		toAddList.add(candidate2);
		
		Skill skill1 = new Skill("English", null);
		Skill skill2 = new Skill("Java", toAddList);
		
		list.add(skill1);
		list.add(skill2);
		
		when(repo.findAll()).thenReturn(list);
		
		ArrayList<Skill> argumentList = new ArrayList<>();
		argumentList.add(skill2);
		
		assertEquals(2, service.findBySkills(argumentList).size());
		
	}
}
