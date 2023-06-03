package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repo.AdminRepository;

@Service
public class AdminServiceImpl implements  AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public void save(Admin admin) {
		// TODO Auto-generated method stub
		adminRepository.save(admin);
	}

	@Override
	public List<Admin> getAllAdmin() {
		// TODO Auto-generated method stub
		return adminRepository.findAll();
	}

	@Override
	public Admin getById(Long id) {
		// TODO Auto-generated method stub
		return adminRepository.getById(id);
	}

}
