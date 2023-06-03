package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	 Specialization findByName(String name);
}
