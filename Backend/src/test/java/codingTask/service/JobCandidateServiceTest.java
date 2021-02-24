package codingTask.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import codingTask.model.JobCandidate;
import codingTask.repository.JobCandidateRepository;

class JobCandidateServiceTest {
	
	@InjectMocks
	JobCandidateService service;
	
	@Mock
	JobCandidateRepository repo;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void findAllTest() {
		
		ArrayList<JobCandidate> list = new ArrayList<JobCandidate>();
		
		JobCandidate candidate1 = new JobCandidate("Kandidat1", "email1", "123123", LocalDate.now(), null);
		JobCandidate candidate2 = new JobCandidate("Kandidat2", "email2", "123123", LocalDate.now(), null);
		
		list.add(candidate1);
		list.add(candidate2);
		
		when(repo.findAll()).thenReturn(list);
		
		ArrayList<JobCandidate> emptyList = (ArrayList<JobCandidate>) service.findAll();
		
		assertEquals(2, emptyList.size());
		verify(repo, times(1)).findAll();
		
	}
	
	@Test
	public void findById() {
		when(repo.findById((long) 1)).
		thenReturn(Optional.of( new JobCandidate("kandidat1", "email1", "123123", LocalDate.now(), null)));
		
		JobCandidate c1 = service.findById((long)1);
		assertEquals(c1.getFullName(), "kandidat1");
		assertEquals(c1.getContactNumber(), "123123");
		assertEquals(c1.getEmail(), "email1");
		assertEquals(c1.getDateOfBirth(), LocalDate.now());
		
	}
	
	@Test 
	public void saveTest() {
		JobCandidate candidate1 = new JobCandidate("Kandidat1", "email1", "123123", LocalDate.now(), null);
		
		service.save(candidate1);
		
		verify(repo, times(1)).save(candidate1);
	}
	
	@Test 
	public void deleteTest() {
		JobCandidate candidate1 = new JobCandidate("Kandidat1", "email1", "123123", LocalDate.now(), null);
		
		service.save(candidate1);
		service.deleteById((long)1);
		
		verify(repo, times(1)).save(candidate1);
		verify(repo, times(1)).deleteById((long)1);
	}
	
	@Test
	public void findByFullNameTest() {
		JobCandidate candidate1 = new JobCandidate("Kandidat1", "email1", "123123", LocalDate.now(), null);
		JobCandidate candidate2 = new JobCandidate("Kandidat1", "email2", "123123", LocalDate.now(), null);
		
		service.save(candidate1);
		service.save(candidate2);
		
		List<JobCandidate> lista = new ArrayList<>();
		lista.add(candidate1);
		lista.add(candidate2);
		
		
		when(repo.findCandidateByName("Kandidat1")).thenReturn(lista);
		List<JobCandidate> pronadjeni = service.findByFullName("Kandidat1");
		
		assertEquals(2, pronadjeni.size());
		assertEquals("Kandidat1", pronadjeni.get(0).getFullName());
		assertEquals("Kandidat1", pronadjeni.get(1).getFullName());
		
	}
	
	
	
	

	

}
