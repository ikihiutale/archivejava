package com.eficode.buggywebservice.service;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.eficode.buggywebservice.domain.LoginInformation;
import com.eficode.buggywebservice.domain.LoginRequest;
import com.eficode.buggywebservice.repository.LoginRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {
	
    @Autowired
    private LoginService lockingService;
    @MockBean
    private LoginRepository loginRepository;
    
    final private String user = "user";
    final private String passwd = "password";

    @Before
    public void setUp() {
        when(loginRepository.findLogin(this.user, this.passwd)).thenReturn(
        		new LoginInformation(new ObjectId().toString(), this.user, this.passwd));

    }
    
    @Test
    public void testLogin(){
        Boolean result = lockingService.login(new LoginRequest(this.user, this.passwd));
        assertThat(result, is(true));
    }
}
