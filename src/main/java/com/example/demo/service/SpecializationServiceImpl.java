package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Specialization;
import com.example.demo.repo.SpecializationRepository;

@Service
public class SpecializationServiceImpl implements SpecializationService{
	
	 private final SpecializationRepository specializationRepository;

	    public SpecializationServiceImpl(SpecializationRepository specializationRepository) {
	        this.specializationRepository = specializationRepository;
	    }

	    @Override
	    public List<Specialization> getAllSpecializations() {
	        return specializationRepository.findAll();
	    }
	    

	    @Override
	    public Specialization getSpecializationById(Long id) {
	        return specializationRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("Specialization not found"));
	    }

	    @Override
	    public void save(Specialization specialization) {
	        specializationRepository.save(specialization);
	    }

	    @Override
	    public void deleteSpecialization(Long id) {
	        specializationRepository.deleteById(id);
	    }

		@Override
		public Specialization getSpecializationByName(String name) {
			// TODO Auto-generated method stub
			return specializationRepository.findByName(name);
		}

		  
}
