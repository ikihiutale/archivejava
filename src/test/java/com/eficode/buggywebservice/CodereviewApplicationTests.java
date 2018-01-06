package com.eficode.buggywebservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.apache.commons.lang3.StringUtils;


import com.eficode.buggywebservice.domain.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CodereviewApplicationTests {
	
	// Run test either with help of Eclipse IDE (CodereviewApplicationTests.java > Run As -> JUnit Test) or on the 
	// command line 'mvn test'
	
	// https://spring.io/guides/gs/testing-web/
	// Spring application context is started: 
	// - MockMvc injected by using the @AutoConfigureMockMvc annotation
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext context;

	// https://spring.io/guides/gs/testing-web/
	// The @SpringBootTest annotation tells Spring Boot to go and look for a main 
	// configuration class (the one with @SpringBootApplication), and use that 
	// to start a Spring application context. 
	@Test
	public void contextLoads() {
		assertThat(context).isNotNull();
	}
	
	@Test
    public void shouldReturnForbidden() throws Exception {
		mockMvc.perform(post("/login/")
                .content(this.toJson(new LoginRequest(StringUtils.EMPTY, StringUtils.EMPTY)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

	/**
	 * Helper method for convertion an obj 2 JSON 
	 * @param obj Sourse obj
	 * @return JSON
	 * @throws Exception
	 */
    private byte[] toJson(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj).getBytes();
    }
}
