package com.eficode.buggywebservice.controller;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.eficode.buggywebservice.CodereviewApplication;
import com.eficode.buggywebservice.domain.LoginInformation;
import com.eficode.buggywebservice.domain.LoginRequest;
import com.eficode.buggywebservice.repository.LoginRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodereviewApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {
	
	@LocalServerPort
	private int port;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	// Provide Spring MVC infrastructure without starting the HTTP Server
    private MockMvc mockMvc;
    
    final private String userName = "user";
    final private String passwd = "passwd";

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private LoginRepository loginRepository;
    
    @SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
            .findAny().orElse(null);
        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() {
       	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.loginRepository.deleteAll();
    }
    
    
    /**
     * Verify that the status code when <code>LoginInformation</code> collection doesn't include a document that contains 
     * the given <code>userName</code> & <code>password</code> pair 
     */
    @Test
    public void userNotFound() throws Exception {
        mockMvc.perform(post("/login/")
                .content(this.toJson(new LoginRequest(this.userName, this.passwd)))
                .contentType(contentType))
                .andExpect(status().isForbidden());
    }

    /**
     * Verify that the status code when <code>LoginInformation</code> collection includes a document that contains 
     * the given <code>userName</code> & <code>password</code> pair 
     */
    @Test
    public void userFound() throws Exception {
    	loginRepository.save(new LoginInformation(ObjectId.get().toString(), this.userName, this.passwd));
        mockMvc.perform(post("/login/")
                .content(this.toJson(new LoginRequest(this.userName, this.passwd)))
                .contentType(contentType))
                .andExpect(status().isOk());
    }
    
    @SuppressWarnings("unchecked")
	protected String toJson(Object obj) throws IOException {
        MockHttpOutputMessage outputMsg = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(obj, MediaType.APPLICATION_JSON, outputMsg);
        return outputMsg.getBodyAsString();
    }
    
}
