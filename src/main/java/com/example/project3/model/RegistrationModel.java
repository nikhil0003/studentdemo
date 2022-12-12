package com.example.project3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String projectTitle;
    private String email;
    private String phoneNumber;
    private Long slotId;
}
