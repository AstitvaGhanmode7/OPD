package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Patient;

public interface PatientService {
	Patient findById(Long id);
    Patient findByUsername(String username);
    void save(Patient patient);
    void update(Patient patient);
    void delete(Long id);
    Patient getPatientByUsername(String username);
	boolean usernameExists(String username);
	List<Patient> getAllPatient();
	long countPatients();
}
