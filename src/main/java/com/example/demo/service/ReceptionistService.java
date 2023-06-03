package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Receptionist;

public interface ReceptionistService {
	void save(Receptionist receptionist);

	Receptionist findByUsername(String username);

	List<Receptionist> getAllReceptionist();

	long countReceptionists();
}
