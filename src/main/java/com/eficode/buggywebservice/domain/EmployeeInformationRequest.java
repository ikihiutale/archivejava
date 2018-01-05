package com.eficode.buggywebservice.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeInformationRequest {
    public final String lastName;
    
    @JsonCreator
    public EmployeeInformationRequest(@JsonProperty("lastName") String lastName) {
        this.lastName = lastName;
    }
}
