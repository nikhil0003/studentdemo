package com.example.project3.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "first_name")
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("lastName")
    private String lastName;

    @Column(name = "project_title")
    @JsonProperty("projectTitle")
    private String projectTitle;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "phone_number")
    @JsonProperty("phoneNumber")
    private String phoneNumber;

}
