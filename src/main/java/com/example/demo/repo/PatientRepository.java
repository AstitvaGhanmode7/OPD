package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUsername(String username);
    boolean existsByUsername(String username);
    
}