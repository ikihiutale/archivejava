package com.eficode.buggywebservice.service;

import com.eficode.buggywebservice.domain.LoginInformation;
import com.eficode.buggywebservice.domain.LoginRequest;
import com.eficode.buggywebservice.repository.LoginRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
	
    /*
    How you would change service?  Write test to it to prove point.
     */
	
	/*
	 * Answer:
	 * If <code>LoginInformation</code> collection doesn't include a document that contains matching <code>userName</code> & <code>password</code>
	 * pair then the return value is null. 
	 */

    @Autowired
    private LoginRepository loginRepository;

    public Boolean login(final LoginRequest loginRequest) {
       	LoginInformation login = loginRepository.findLogin(loginRequest.userName, loginRequest.password);
    	return login != null ? Boolean.TRUE : Boolean.FALSE;
    }
}
