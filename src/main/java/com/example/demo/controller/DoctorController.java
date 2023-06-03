package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.repo.DoctorRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService; 
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private AppointmentService appointmentService;
	
	 public DoctorController(DoctorService doctorService) {
	        this.doctorService = doctorService;
	    }
	
	@GetMapping("/doctorHome")
    public String showDoctorHomePage(Model model) {
	/*
		String username = authentication.getName();   
	 Doctor doctor = doctorService.getDoctorByUsername(authentication.getName());
     
        model.addAttribute("doctor", doctor);
        model.addAttribute("name", doctor.getName());

        model.addAttribute("id", doctor.getId());
         */
		
        return "doctor/doctorHome";
    }
	
	//PROFILE
		@GetMapping("/profile")
	    public String profile(@RequestParam("username") String username,Model model) {
			
			Doctor doctor = doctorRepository.findByUsername(username);
	        // Add other required attributes
	        model.addAttribute("name", doctor.getName());
	        model.addAttribute("p_id", doctor.getId()); 
	        model.addAttribute("username", doctor.getUsername()); 
            model.addAttribute("password", doctor.getPassword());
			
	        model.addAttribute("doctor", doctor); 
	        
			Appointment appointment = new Appointment();
			
			model.addAttribute("appointment",appointment);
			
	        return "doctor/profile";
	    }
		
    
	@GetMapping("/appointmentHistory")
    public String showAppointmentHistory(Model model, @RequestParam("username") String username) {
        // Assuming you have a method to retrieve the appointment history for the given patient username
     System.out.println(username);  
     Doctor doctor= doctorRepository.findByUsername(username);
        // Add other required attributes
        model.addAttribute("name", doctor.getName());
        model.addAttribute("id", doctor.getId()); 
        model.addAttribute("username", doctor.getUsername()); 
        model.addAttribute("password", doctor.getPassword());
        model.addAttribute("doctor", doctor); 
        
     
     Long doctorId = doctor.getId();
	 List<Appointment> appointments = appointmentService.getAllAppointmentByDoctorId(doctorId);
        
        model.addAttribute("appointments", appointments);
        
        return "doctor/appointmentHistory";
    }
    
	
	
}
