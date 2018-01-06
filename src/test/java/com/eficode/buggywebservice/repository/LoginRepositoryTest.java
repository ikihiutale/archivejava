package com.eficode.buggywebservice.repository;

import com.eficode.buggywebservice.domain.LoginInformation;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.hamcrest.collection.IsCollectionWithSize;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DataMongoTest
public class LoginRepositoryTest {

    /*
    What other cases should be tested?
     */

	/*
	 * Answer:
	 * The <code>LoginController</code> only has the "/login" resource defined so no other test cases needed - although controllers usually have 
	 * CRUD operations for which it is necessary to implement test cases, both negative and positive.
	 */
    @Autowired
    private LoginRepository loginRepository;

    @Test
    public void testInsertAndSelect() {
        loginRepository.deleteAll();
        loginRepository.insert(new LoginInformation(ObjectId.get().toString(),"username", "password"));
        LoginInformation loginInformation = loginRepository.findLogin("username", "password");
        assertThat(loginInformation, notNullValue());
        // loginRepository.delete(loginInformation);
    }
    
    @Test
    public void testInsertAndSelectRegex() {
        loginRepository.deleteAll();
        loginRepository.insert(new LoginInformation(ObjectId.get().toString(), "username", "password"));
        loginRepository.insert(new LoginInformation(ObjectId.get().toString(), "username", "password"));
        List<LoginInformation> loginInfos = loginRepository.findLoginRegexQuery("username", "password");
        assertThat(loginInfos, IsCollectionWithSize.hasSize(2));
        //loginRepository.delete(loginInfos);
    }
    
    @Test
    public void testInsertAndSelectQueryMethod() {
        loginRepository.deleteAll();
        loginRepository.insert(new LoginInformation(ObjectId.get().toString(), "username", "password"));
        loginRepository.insert(new LoginInformation(ObjectId.get().toString(), "username", "password"));
        List<LoginInformation> loginInfos = loginRepository.findByUsernameAndPassword("username", "password");
        assertThat(loginInfos, IsCollectionWithSize.hasSize(2));
        //loginRepository.delete(loginInfos);
    }
}
