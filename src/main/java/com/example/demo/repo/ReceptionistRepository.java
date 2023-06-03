package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Receptionist;

@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Long>{

	Receptionist findByUsername(String username);

}
