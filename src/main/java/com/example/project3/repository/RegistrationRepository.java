package com.example.project3.repository;

import com.example.project3.entity.Registration;
import com.example.project3.entity.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	Registration findByStudent(Student student);
}
