package com.eficode.buggywebservice.controller;

import com.eficode.buggywebservice.domain.EmployeeInformationRequest;
import com.eficode.buggywebservice.domain.EmployeeInformationResponse;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.hamcrest.collection.IsEmptyCollection;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeInformationControllerTest {

	// http://www.lucassaldanha.com/unit-and-integration-tests-in-spring-boot/
	// The  TestRestTemplate is configured automatically when you use the @SpringBootTest annotation, 
	// and is configured to resolve relative paths to http://localhost:${local.server.port}
    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @Test
    public void testFindAll(){
        ResponseEntity<EmployeeInformationResponse> forEntity = 
        		testRestTemplate.getForEntity("/all", EmployeeInformationResponse.class, Collections.emptyMap());
        assertThat(forEntity,notNullValue());
        assertThat(forEntity.getStatusCode(),is(HttpStatus.OK));
        assertThat(forEntity.getBody(),notNullValue());
    }
    
    
    @Test
    public void testNotFindByInvalidName() {
        EmployeeInformationRequest employeeInfoReq = new EmployeeInformationRequest(StringUtils.EMPTY);        
    	ResponseEntity<EmployeeInformationResponse> responseEntity =
            testRestTemplate.postForEntity("/byName", employeeInfoReq, EmployeeInformationResponse.class, Collections.emptyMap());
    	EmployeeInformationResponse response = responseEntity.getBody();
    	assertThat(response.employeeInformations, IsEmptyCollection.empty());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
