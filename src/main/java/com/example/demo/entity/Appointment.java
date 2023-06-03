package com.example.demo.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
public class Appointment {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    //private LocalDateTime dateTime;
	 
	 private LocalDate date;
	    private LocalTime time;
	    
	    private String status; 
	 
	    // Relationship with Patient entity (Many-to-One)
	    @ManyToOne
	    @JoinColumn(name = "patient_id")
	    private Patient patient;
	    
	    // Relationship with Doctor entity (Many-to-One)
	    @ManyToOne
	    @JoinColumn(name = "doctor_id")
	    private Doctor doctor;
	    
	    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
	    private Prescription prescription;
	
}
