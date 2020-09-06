package com.socgen.ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public abstract class BaseControllerTest extends BaseTest {
	
 	@Autowired
 	private MockMvc mockMvc;
 	
 	protected MockHttpServletResponse executeGetRequest(String uri) throws Exception{
 		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
 		return mockMvc.perform(requestBuilder).andReturn().getResponse();
 	}

}
