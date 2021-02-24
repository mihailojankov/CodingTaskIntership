package codingTask.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import codingTask.model.JobCandidate;
import codingTask.model.Skill;
import codingTask.repository.SkillRepository;
import codingTask.service.SkillService;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class SkillControllerTest extends AbstractControllerTest{
	
	 @Autowired
	   protected MockMvc mvc;
	  
	   @Autowired
	   WebApplicationContext webApplicationContext;
	   
	   @MockBean
	   SkillService service;
	   
	   @MockBean
	   SkillRepository repo;
	   
	   List<Skill> baseList;
	   
	   @Before
	   public void setUp() {
		   baseList = new ArrayList<>();
		      
		   baseList.add(new Skill("Java", null));
		   baseList.add(new Skill("English", null));
	   }
	   
	   
	   @Test
	   public void findAllTest() throws Exception {
		   
		  Mockito.when(service.findAll()).thenReturn(baseList);
		   
	      String uri = "/skill";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      Skill[] skills = mapFromJson(content, Skill[].class);
	      assertTrue(skills.length > 0);
	   }
	   
	   @Test
	   public void findByIdTest() throws Exception{
		   when(service.findById((long) 1)).
		   thenReturn(baseList.get(1));
	   
		   String uri = "/skill/1";
		      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
		         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   Skill skill = mapFromJson(content, Skill.class);
		   assertEquals(baseList.get(1).getName(), skill.getName());
		
		   
	   }
	   
	   @Test
	   public void saveTest() throws Exception{ 
		   String uri = "/skill";
		   String inputJson = super.mapToJson(new Skill("German", null));
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(201, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   assertEquals(content, "Success");
	   }
	   
	   @Test
	   public void deleteTest() throws Exception{
		   String uri = "/skill/1";
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   assertEquals(content, "Success");
	   }
	   
	   @Test
	   public void findBySkills() throws Exception{
			JobCandidate candidate1 = new JobCandidate("Kandidat1", "email1", "123123", LocalDate.now(), null);
			JobCandidate candidate2 = new JobCandidate("Kandidat1", "email2", "123123", LocalDate.now(), null);
			ArrayList<JobCandidate> candidateList = new ArrayList<>();
			candidateList.add(candidate1);
			candidateList.add(candidate2);
			
			ArrayList<Skill> newSkills = new ArrayList<>();
		      
			newSkills.add(new Skill("Java", candidateList));
			newSkills.add(new Skill("English", null));
			
			when(service.findBySkills(newSkills)).thenReturn(candidateList);
			
			String uri = "/skill/findBySkills";
		    String input = super.mapToJson(baseList);
		    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
		        .contentType(MediaType.APPLICATION_JSON_VALUE)
		        .content(input))
		    	.andReturn();
		    
		    int status = mvcResult.getResponse().getStatus();
		    assertEquals(200, status);

	   }
}
