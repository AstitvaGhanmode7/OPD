package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Patient;
import com.example.demo.repo.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	 @Autowired
	 private PatientRepository patientRepository;

	 
	 public PatientServiceImpl(PatientRepository patientRepository) {
	        this.patientRepository = patientRepository;
	    }

	    @Override
	    public Patient findById(Long id) {
	        return patientRepository.findById(id).orElse(null);
	    }

	    @Override
	    public Patient findByUsername(String username) {
	        return patientRepository.findByUsername(username);
	    }

	    @Override
	    public void save(Patient patient) {
	        patientRepository.save(patient);
	    }

	    @Override
	    public void update(Patient patient) {
	        patientRepository.save(patient);
	    }

	    @Override
	    public void delete(Long id) {
	        patientRepository.deleteById(id);
	    }

		@Override
		public Patient getPatientByUsername(String username) {
			// TODO Auto-generated method stub
			 return patientRepository.findByUsername(username);
		}

		 @Override
		    public boolean usernameExists(String username) {
		        return patientRepository.existsByUsername(username);
		    }

		@Override
		public List<Patient> getAllPatient() {
			// TODO Auto-generated method stub
			return patientRepository.findAll();
		}

		@Override
		public long countPatients() {
			// TODO Auto-generated method stub
			return patientRepository.count();
		}
}
