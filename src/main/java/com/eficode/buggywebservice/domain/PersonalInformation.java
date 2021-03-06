package com.eficode.buggywebservice.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class PersonalInformation {
	
	@Id
    public String id;
    public final String firstName;
    public final String lastName;
    public final Date dateOfBirth;
    public final String streetAddress;
    public final String zipCode;
    public final String postalOffice;

    @JsonCreator
    public PersonalInformation(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName,
                               @JsonProperty("dateOfBirth") Date dateOfBirth,@JsonProperty("streetAddress") String streetAddress,
                               @JsonProperty("zipCode") String zipCode,@JsonProperty("postalOffice") String postalOffice) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.postalOffice = postalOffice;
    }

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getPostalOffice() {
		return postalOffice;
	}
}
