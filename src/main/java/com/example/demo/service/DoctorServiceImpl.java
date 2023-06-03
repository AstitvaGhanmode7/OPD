package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Doctor;
import com.example.demo.repo.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

	@Override
	public Doctor getDoctorByUsername(String username) {
		// TODO Auto-generated method stub
		return doctorRepository.findByUsername(username);
	}

	@Override
	public List<Doctor> findBySpecialization(String specialization) {
		// TODO Auto-generated method stub
		
		return doctorRepository.findBySpecialization(specialization);
	}

	@Override
	public List<Doctor> findBySpecializationId(Long s_id) {
		// TODO Auto-generated method stub
		return doctorRepository.findBySpecializationId(s_id);
	}

	@Override
	public List<Doctor> getAllDoctor() {
		// TODO Auto-generated method stub
		return doctorRepository.findAll();
	}

	@Override
	public long countDoctors() {
		// TODO Auto-generated method stub
		return doctorRepository.count();
	}
}