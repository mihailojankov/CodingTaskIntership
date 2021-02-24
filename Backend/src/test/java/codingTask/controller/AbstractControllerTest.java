package codingTask.controller;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public abstract class AbstractControllerTest {
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
		   
	      ObjectMapper objectMapper = new ObjectMapper();
	      objectMapper.registerModule(new JavaTimeModule());
	      objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	      
	      return objectMapper.writeValueAsString(obj);
	   }
	   protected <T> T mapFromJson(String json, Class<T> clazz)
	      throws JsonParseException, JsonMappingException, IOException {
	      
	      ObjectMapper objectMapper = new ObjectMapper();
	      objectMapper.registerModule(new JavaTimeModule());
	      objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	      return objectMapper.readValue(json, clazz);
	   }

}
