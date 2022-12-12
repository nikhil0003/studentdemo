package com.example.project3.model;

import java.util.List;

import com.example.project3.entity.Slot;
import com.example.project3.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListOfStudentsModel {
		
	@JsonProperty("studentsDetails")
	private List<StudentSlotModel> studentsDetails;

}
