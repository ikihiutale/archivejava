package com.eficode.buggywebservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eficode.buggywebservice.domain.EmployeeInformation;
import com.eficode.buggywebservice.domain.ExperienceLevel;
import com.eficode.buggywebservice.domain.PersonalInformation;
import com.eficode.buggywebservice.domain.PreviousJobs;
import com.eficode.buggywebservice.domain.Skill;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.bson.types.ObjectId;
import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.contains;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeInformationRepositoryTest {

    @Autowired
    private EmployeeInformationRepository employeeInfoRepository;
    
    final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    
    @Test
    public void insertEmployeeInfoAndFindByLastName() {
    	
    	employeeInfoRepository.deleteAll();
    	
    	final String lastName = "lastName";
    	final String id = ObjectId.get().toString();
    	PersonalInformation personalInfo = null;
		try {
			personalInfo = new PersonalInformation("firstName", lastName,
					formatter.parse("01/08/1985"), "Foo", "12345", "Bar");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	List<PreviousJobs> prevJobs = new ArrayList<>();
    	EmployeeInformation employeeInfo = new EmployeeInformation(id, prevJobs, personalInfo);

    	employeeInfo = employeeInfoRepository.save(employeeInfo);
    	assertEquals(employeeInfo.id, id);
    	
    	List<EmployeeInformation> employeeInfos = employeeInfoRepository.findByLastName(lastName);
    	assertThat(employeeInfos, not(IsEmptyCollection.empty()));
    	
    	assertThat(employeeInfos, contains(
                hasProperty("personalInformation", hasProperty("lastName", is(lastName)))));
    	//assertThat(employeeInfos, hasItems(employeeInfo));
    }
}
