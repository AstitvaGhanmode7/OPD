package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Prescription;
import com.example.demo.repo.PrescriptionRepository;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@Override
	public void save(Prescription prescription) {
		// TODO Auto-generated method stub
		prescriptionRepository.save(prescription);
	}

}
