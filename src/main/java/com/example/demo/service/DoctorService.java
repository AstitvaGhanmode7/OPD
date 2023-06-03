package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(Long id);
    Doctor save(Doctor doctor);
    void deleteDoctor(Long id);
    Doctor getDoctorByUsername(String username);
	List<Doctor> findBySpecialization(String specialization);
	List<Doctor> findBySpecializationId(Long s_id);
	List<Doctor> getAllDoctor();
	long countDoctors();
}