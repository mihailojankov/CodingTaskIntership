package codingTask.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import codingTask.model.JobCandidate;
import codingTask.service.JobCandidateService;



@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class JobCandidateControllerTest extends AbstractControllerTest{
		
	   @Autowired
	   protected MockMvc mvc;
	  
	   @Autowired
	   WebApplicationContext webApplicationContext;
	   
	   @MockBean
	   JobCandidateService service;
	   
	   List<JobCandidate> baseList;
	   
	   @BeforeEach
	   public void setUp() {
		   baseList = new ArrayList<>();
		      
		   baseList.add(new JobCandidate("Kandidat1", "email1", "123123", LocalDate.now(), null));
		   baseList.add(new JobCandidate("Kandidat2", "email2", "123123123", LocalDate.now(), null));
	   }
	   
	   
	   @Test
	   public void findAllTest() throws Exception {
		   
		  Mockito.when(service.findAll()).thenReturn(baseList);
		   
	      String uri = "/jobCandidate";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      JobCandidate[] candidates = mapFromJson(content, JobCandidate[].class);
	      assertTrue(candidates.length > 0);
	   }
	   
	   @Test
	   public void findByIdTest() throws Exception{
		   when(service.findById((long) 1)).
		   thenReturn(baseList.get(1));
	   
		   String uri = "/jobCandidate/1";
		      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
		         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   JobCandidate candidate = mapFromJson(content, JobCandidate.class);
		   assertEquals(baseList.get(1).getFullName(), candidate.getFullName());
		   assertEquals(baseList.get(1).getContactNumber(), candidate.getContactNumber());
		   assertEquals(baseList.get(1).getEmail(), candidate.getEmail());
		   assertEquals(baseList.get(1).getDateOfBirth(), candidate.getDateOfBirth());
		   
	   }
	   
	   @Test
	   public void saveTest() throws Exception{ 
		   String uri = "/jobCandidate";
		   String inputJson = super.mapToJson(baseList.get(0));
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(201, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   assertEquals(content, "Success");
	   }
	   
	   @Test
	   public void deleteTest() throws Exception{
		   String uri = "/jobCandidate/1";
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		   int status = mvcResult.getResponse().getStatus();
		   assertEquals(200, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   assertEquals(content, "Success");
	   }
	   
	   
	   @Test
	   public void findByFullNameTest() throws Exception{
		   	  Mockito.when(service.findByFullName("Kandidat2")).thenReturn(baseList);
		   
		      String uri = "/jobCandidate/findByFullName";
		      String input = "Kandidat2";
		      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
		         .contentType(MediaType.APPLICATION_JSON_VALUE).content(input))
		    		  .andReturn();
		      
		      int status = mvcResult.getResponse().getStatus();
		      assertEquals(200, status);
		      String content = mvcResult.getResponse().getContentAsString();
		      JobCandidate[] candidates = mapFromJson(content, JobCandidate[].class);
		      assertEquals(2, candidates.length);
	   }
	   
	   
}
