package com.example.project3.response;

import com.example.project3.entity.Slot;
import com.example.project3.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterStudentResponse {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("message")
    private String message;

    @JsonProperty("alreadyRegistered")
    private Boolean alreadyRegistered;

    @JsonProperty("existingSlot")
    private Slot existingSlot;

    @JsonProperty("newSlot")
    private Slot newSlot;

    @JsonProperty("student")
    private Student student;

}
