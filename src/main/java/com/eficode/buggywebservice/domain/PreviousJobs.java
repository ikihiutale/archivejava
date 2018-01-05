package com.eficode.buggywebservice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

public class PreviousJobs {
    /**
     * Why there shouldn't be id filed in this class
     */
	
	/*
	 * Answer:
	 * The <code>EmployeeInformation</code> document forms an embedded model where the <code>PreviousJobs</code> is a subdocuments.
	 * IDs are only for root documents by default and subdocuments are always returned within the parent document.
     * 
     * IDS can be created for subdocuments, but this must be done by programming.
	 */

    @Id
    public String id;
    public final String employerName;
    public final Date startDate;
    public final Date endDate;
    public final List<Skill> skills;

    @JsonCreator
    public PreviousJobs(@JsonProperty("employerName") String employerName,@JsonProperty("startDate") Date startDate,
                        @JsonProperty("endDate") Date endDate,@JsonProperty("skills") List<Skill> skills) {
        this.employerName = employerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.skills = skills;
    }
}
