package com.eficode.buggywebservice.service;


import com.eficode.buggywebservice.domain.*;
import com.eficode.buggywebservice.repository.EmployeeInformationRepository;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeInformationServiceTest {

    /**
     * Is this test good place to use mock?
     */
	
	/*
	 * Answer:
	 * To test the Service layer, it's ideally if we don't need to know or care about how the persistence layer is implemented.
	 * This is achieved by using mocking.
	 * 
	 * http://www.baeldung.com/spring-boot-testing 
	 */
    @Autowired
    private EmployeeInformationService employeeInformationService;
    @MockBean
    private EmployeeInformationRepository employeeInformationRepository;

    @Before
    public void setUp() {
        when(employeeInformationRepository.findByLastName(anyString())).thenReturn(
                Arrays.asList(new EmployeeInformation(new ObjectId().toString(),
                        Arrays.asList(new PreviousJobs("test",
                                Date.from(LocalDateTime.now().minusYears(5).toInstant(ZoneOffset.UTC)),
                                Date.from(LocalDateTime.now().minusYears(1).toInstant(ZoneOffset.UTC)),
                                Arrays.asList(new Skill("skill", ExperienceLevel.AVERAGE)))), new PersonalInformation(
                        "Teppo", "Testi"
                        , Date.from(LocalDateTime.now().minusYears(30).toInstant(ZoneOffset.UTC)), "Street", "zipCode",
                        "postalOffice")
                )));
    }
    
    @Test
    public void testNameSearch(){
        List<EmployeeInformation> employeeInformations = employeeInformationService.searchByLastName("test");
        assertThat(employeeInformations,notNullValue());
    }
}
