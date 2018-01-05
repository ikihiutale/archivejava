package com.eficode.buggywebservice.controller;

import com.eficode.buggywebservice.domain.LoginRequest;
import com.eficode.buggywebservice.service.LoginService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    /*
    What is problem with this controller write test to prove it and fix it
     */
	
	/*
	 * Answer:
	 * The <code>ResponseEntity</code> status code did not take into account the situation where the requested resource was not found.
	 * Nor is it possible for a new user to sign in & security but that is out of the scope hopefully..
	 */

    @Autowired
    private LoginService loginService;
    
    
    /**
     * curl -X POST -i -H "Content-type: application/json" -X POST http://localhost:8080/login -d '{"userName":"username", "password":"password"}'
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest loginRequest) {
    	LOGGER.debug("Login: user = {}, passwd = {}", loginRequest.userName, loginRequest.password); 
    	Boolean result = loginService.login(loginRequest);
        return new ResponseEntity<>(result, (result ? HttpStatus.OK : HttpStatus.FORBIDDEN));
    }
}
