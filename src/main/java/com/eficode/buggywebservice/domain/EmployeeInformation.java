package com.eficode.buggywebservice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.List;

public class EmployeeInformation {

    /*
    What are good sides to use domain objects here and what things they make harder
     */
	
	/*
	 * Answer:
	 * The question is the choice between multiple collections with id references or embedded documents (<code>EmployeeInformation</code>).
	 * Separate collections:
	 * + offer better querying flexibility
	 * - require more work (multiple queries + extra programming to get the same data that is achieved with a single query via embedded documents)
	 *
     * Embedded documents:
     * + easy to implement and fast
     * - selecting is more limited (range can be selected but can't sort)
     * - a document, including all its embedded documents and arrays, cannot exceed 16MB
	 */
    @Id
    public String id;
    public final List<PreviousJobs> previousJobs;
    public final PersonalInformation personalInformation;

    @JsonCreator
    public EmployeeInformation(@JsonProperty("id") String id,@JsonProperty("previousJobs") List<PreviousJobs> previousJobs,
                               @JsonProperty("personalInformation") PersonalInformation personalInformation) {
        this.previousJobs = previousJobs;
        this.personalInformation = personalInformation;
        this.id=id;
    }

	public String getId() {
		return id;
	}

	public List<PreviousJobs> getPreviousJobs() {
		return previousJobs;
	}

	public PersonalInformation getPersonalInformation() {
		return personalInformation;
	}
    
}
