package com.socgen.ems;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseControllerTest extends BaseTest {
	
 	@Autowired
 	private MockMvc mockMvc;
 	
 	protected MockHttpServletResponse executeGetRequest(String uri) throws Exception{
 		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
 		return mockMvc.perform(requestBuilder).andReturn().getResponse();
 	}

 	protected MockHttpServletResponse executePostRequest(String uri, String data) throws Exception{
 		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).content(data).contentType(MediaType.APPLICATION_JSON);
 		return mockMvc.perform(requestBuilder).andReturn().getResponse();
 	}
 	
    protected String mapToJSON(Object obj) throws JsonProcessingException {

    	ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    
    protected <T> T mapFromJSON(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
            
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
    }

}
