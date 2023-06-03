package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Specialization;

public interface SpecializationService {
	
	 List<Specialization> getAllSpecializations();
	 Specialization getSpecializationById(Long id);
	    void deleteSpecialization(Long id);
	    Specialization getSpecializationByName(String name);
	    void save(Specialization specialization);
    
}
