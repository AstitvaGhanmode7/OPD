package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Admin;

public interface AdminService {
	void save(Admin admin);

	List<Admin> getAllAdmin();

	Admin getById(Long id);
}
