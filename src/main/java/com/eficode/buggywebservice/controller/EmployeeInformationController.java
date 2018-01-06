package com.eficode.buggywebservice.controller;

import com.eficode.buggywebservice.domain.EmployeeInformation;
import com.eficode.buggywebservice.domain.EmployeeInformationRequest;
import com.eficode.buggywebservice.domain.EmployeeInformationResponse;
import com.eficode.buggywebservice.service.EmployeeInformationService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeInformationController {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeInformationController.class);
	
    /*
    Why it may be good idea to use response and request objects and not value classes themselves
     */
	
	/*
	 * Answer:  These "proxy" classes (EmployeeInformationRequest and EmployeeInformationRequest) make it possible to 
	 * avoid using unnecessary fields (EmployeeInformationRequest) and to add logic (e.g validation etc.) later without 
	 * the actual domain class (EmployeeInformation) being changed. 
	 * 
	 */
	
    @Autowired
    private EmployeeInformationService employeeInformationService;

    @RequestMapping(path = "/byName",method = RequestMethod.POST)
    public ResponseEntity<EmployeeInformationResponse> findByName(@RequestBody EmployeeInformationRequest employeeInformationRequest) {
    	List<EmployeeInformation> employeeInfos = employeeInformationService.searchByLastName(employeeInformationRequest.lastName);
        return new ResponseEntity<>(
                new EmployeeInformationResponse(employeeInfos), CollectionUtils.isEmpty(employeeInfos) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
    
    @RequestMapping(path = "/all",method = RequestMethod.GET)
    public ResponseEntity<EmployeeInformationResponse> findAllName(){
        return new ResponseEntity<>(
                new EmployeeInformationResponse(employeeInformationService.getAll()), HttpStatus.OK);
    }


}
