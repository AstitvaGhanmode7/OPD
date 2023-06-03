package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.entity.Receptionist;
import com.example.demo.entity.Specialization;
import com.example.demo.repo.AppointmentRepository;
import com.example.demo.repo.DoctorRepository;
import com.example.demo.repo.ReceptionistRepository;
import com.example.demo.repo.SpecializationRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;


import org.springframework.ui.Model;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
	
	
	 	@Autowired
	    private AppointmentRepository appointmentRepository;
	 	
	 	@Autowired
	    private PatientService patientService;

		
		@Autowired
		private SpecializationRepository specializationRepository;
		
		@Autowired
		private ReceptionistRepository receptionistRepository;

		@Autowired
		private DoctorRepository doctorRepository;
			
		
		 @PostMapping("/saveAppointment")
		    public String saveAppointment(Model model, @ModelAttribute("appointment") Appointment appointment,@RequestParam("patientId") Long patientId,@RequestParam("doctorId") Long doctorId,@RequestParam("username") String username) {
		        
			 Receptionist receptionist =receptionistRepository.findByUsername(username);
		        // Add other required attributes
		        model.addAttribute("name", receptionist.getName());
		        model.addAttribute("id", receptionist.getId()); 
		        model.addAttribute("username", receptionist.getUsername()); 
	            model.addAttribute("password", receptionist.getPassword());
	            model.addAttribute("receptionist", receptionist);
	            System.out.println(receptionist.getUsername());
		    	Patient patient = patientService.findById(patientId);
		    	
		    	// Set the p_id value to the appointment
		        appointment.setPatient(patient);
		        
		        //model.addAttribute("username",patient.getUsername());

		        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
		        
		       
		            appointment.setDoctor(doctor);
		           
		    	// Save the appointment to the database
		        appointmentRepository.save(appointment);

		        // Redirect to a success page or any other appropriate page
		        return "redirect:/receptionist/bookAppointmentForPatient?username="+username;
		    }
	 	
	    // ...
/*
	    @PostMapping("/saveAppointment")
	    public String saveAppointment(Model model, @ModelAttribute("appointment") Appointment appointment,@RequestParam("p_id") Long p_id,@RequestParam("doctorId") Long doctorId) {
	        
	    	
	    	Patient patient = patientService.findById(p_id);
	    	
	    	// Set the p_id value to the appointment
	        appointment.setPatient(patient);
	        
	        model.addAttribute("username",patient.getUsername());

	        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
	        
	        if (doctor != null) {
	            appointment.setDoctor(doctor);
	            appointmentRepository.save(appointment);
	        }
	        
	    	// Save the appointment to the database
	        appointmentRepository.save(appointment);

	        // Redirect to a success page or any other appropriate page
	        return "redirect:/patient/bookAppointment?username=" +patient.getUsername();
	    }
	
	  */
		
		
	    /*
	@Autowired 
	private PatientService patientService;
    
	@Autowired
	private AppointmentService appointmentService; 
	
	@PostMapping("/saveAppointment")
	public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
		
		String username = authentication.getName();
		Patient patient = patientService.getPatientByUsername(authentication.getName());
		
		Long id = patient.getId();
		
		appointment.setPatient(patient);   
		
		appointmentService.saveAppointment(appointment);
		
		return "redirect:patient/bookAppointment";
	}
	/*
	 @GetMapping("/patientHome")
	    public String showPatientHomePage(Model model, Authentication authentication) {
		 String username = authentication.getName();   
		 Patient patient = patientService.getPatientByUsername(authentication.getName());
	        model.addAttribute("patient", patient);
	        model.addAttribute("name", patient.getName());
	        return "patient/patientHome";
	    }
	  */  
	
	
}