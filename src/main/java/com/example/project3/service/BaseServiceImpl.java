package com.example.project3.service;

import com.example.project3.entity.Registration;
import com.example.project3.entity.Slot;
import com.example.project3.entity.Student;
import com.example.project3.model.RegistrationModel;
import com.example.project3.model.RegistrationUpdate;
import com.example.project3.model.StudentSlotModel;
import com.example.project3.repository.RegistrationRepository;
import com.example.project3.repository.SlotRepository;
import com.example.project3.repository.StudentRepository;
import com.example.project3.response.RegisterStudentResponse;
import com.example.project3.service.interfaces.BaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class BaseServiceImpl implements BaseService {

	@Autowired
	private SlotRepository slotRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void setIndexParams(Model model) {
		List<Slot> slots = slotRepository.findAll();
		model.addAttribute("slots", slots);
	}

	@Override
	public RegisterStudentResponse registerStudent(RegistrationModel registrationModel) {
		Optional<Student> existingStudentOptional = studentRepository.findById(registrationModel.getId());
		Optional<Slot> slot = slotRepository.findById(registrationModel.getSlotId());
		if (slot.isEmpty()) {
			return RegisterStudentResponse.builder().success(false).alreadyRegistered(false)
					.message("Slot id is not valid").build();
		}
		Student student;
		if (existingStudentOptional.isPresent()) {
			student = existingStudentOptional.get();

			// return here if want to override
			Optional<Registration> existingRegistration = Optional
					.ofNullable(registrationRepository.findByStudent(student));
			if (existingRegistration.isPresent()) {
				return RegisterStudentResponse.builder().success(false).alreadyRegistered(true)
						.existingSlot(existingRegistration.get().getSlot()).newSlot(slot.get()).student(student)
						.message("Already Registered").build();
			}

		} else {
			student = mapper.convertValue(registrationModel, Student.class);
			student = studentRepository.save(student);
		}

		Registration registration = new Registration();
		registration.setStudent(student);
		registration.setSlot(slot.get());
		registrationRepository.save(registration);

		Long avalableSeats = slot.get().getAvailableSeats() - 1;
		Slot slotEntity = slot.get();
		slotEntity.setAvailableSeats(avalableSeats);
		slotRepository.save(slotEntity);

		return RegisterStudentResponse.builder().success(true).message("Success").build();

	}

	@Override
	public void updateSlot(RegistrationUpdate registrationUpdate) {
		Optional<Student> student = studentRepository.findById(registrationUpdate.getStudentId());
		if (student.isPresent()) {
			Optional<Slot> slot = slotRepository.findById(registrationUpdate.getSlotId());
			if (slot.isPresent()) {
				Optional<Registration> registration = Optional
						.ofNullable(registrationRepository.findByStudent(student.get()));
				if (registration.isPresent()) {
					Registration actualRegistration = registration.get();
					actualRegistration.setSlot(slot.get());
					registrationRepository.save(actualRegistration);
				}
			}
		}
	}

	@Override
	public List<Registration> getStudentSlotDetails() {
		List<StudentSlotModel> studentSlotsList = new ArrayList<StudentSlotModel>();
		List<Registration> registrationsList = registrationRepository.findAll();
		log.info("RegistrationsList:" + registrationsList);

		if (!registrationsList.isEmpty()) {
			for (Registration r : registrationsList) {
				Student s = new Student();
				Slot slot = new Slot();
				StudentSlotModel studentSlotModel = new StudentSlotModel();
				s = r.getStudent();
				slot = r.getSlot();
				studentSlotModel.setStudents(s);
				studentSlotModel.setSlotDetails(slot);
				studentSlotsList.add(studentSlotModel);
			}
			log.info("Student Slots List:" + studentSlotsList);

		}
		
		return registrationsList;

	}
}
