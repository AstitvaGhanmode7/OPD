package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Receptionist;
import com.example.demo.repo.ReceptionistRepository;

@Service
public class ReceptionistServiceImpl implements ReceptionistService {

	@Autowired
	private ReceptionistRepository receptionistRepository;
	
	@Override
	public void save(Receptionist receptionist) {
		// TODO Auto-generated method stub
		receptionistRepository.save(receptionist);
	}

	@Override
	public Receptionist findByUsername(String username) {
		// TODO Auto-generated method stub
		return receptionistRepository.findByUsername(username);
	}

	@Override
	public List<Receptionist> getAllReceptionist() {
		// TODO Auto-generated method stub
		return receptionistRepository.findAll();
	}

	@Override
	public long countReceptionists() {
		// TODO Auto-generated method stub
		return receptionistRepository.count();
	}

}
