package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Doctor;
import com.example.demo.entity.Specialization;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Doctor findByUsername(String username);
	
	List<Doctor> findBySpecialization(String specialization); 
	
	List<Doctor> findBySpecializationId(Long s_id); 
	
}
