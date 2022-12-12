package com.example.project3.service.interfaces;

import com.example.project3.entity.Registration;
import com.example.project3.model.RegistrationModel;
import com.example.project3.model.RegistrationUpdate;
import com.example.project3.model.StudentSlotModel;
import com.example.project3.response.RegisterStudentResponse;

import java.util.List;

import org.springframework.ui.Model;

public interface BaseService {

    void setIndexParams(Model model);

    RegisterStudentResponse registerStudent(RegistrationModel registrationModel);

    void updateSlot(RegistrationUpdate registrationUpdate);

    List<Registration> getStudentSlotDetails();
}
