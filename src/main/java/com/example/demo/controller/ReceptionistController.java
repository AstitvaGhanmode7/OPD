package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Receptionist;
import com.example.demo.entity.Specialization;
import com.example.demo.repo.DoctorRepository;
import com.example.demo.repo.ReceptionistRepository;
import com.example.demo.service.DoctorService;
import com.example.demo.service.SpecializationService;


@Controller
@RequestMapping("/receptionist")
public class ReceptionistController {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private SpecializationService specializationService;
	
	@Autowired
	private ReceptionistRepository receptionistRepository;
	
	
	
	//PROFILE
			@GetMapping("/profile")
		    public String profile(@RequestParam("username") String username,Model model) {
				
				Receptionist receptionist =receptionistRepository.findByUsername(username);
		        // Add other required attributes
		        model.addAttribute("name", receptionist.getName());
		        model.addAttribute("id", receptionist.getId()); 
		        model.addAttribute("username", receptionist.getUsername()); 
	            model.addAttribute("password", receptionist.getPassword());
	            model.addAttribute("receptionist", receptionist);
				
		        model.addAttribute("receptionist", receptionist); 
		        
				Appointment appointment = new Appointment();
				
				model.addAttribute("appointment",appointment);
				
		        return "receptionist/profile";
		    }
			
	
	@GetMapping("/bookAppointmentForPatient")
    public String bookAppointment(@RequestParam("username") String username , Model model) {
		Receptionist receptionist = receptionistRepository.findByUsername(username);
		model.addAttribute("username", username);
        model.addAttribute("password", receptionist.getPassword());
        model.addAttribute("receptionist", receptionist);
        
		
		Appointment appointment = new Appointment();
		
		model.addAttribute("appointment",appointment);
		
        return "receptionist/bookAppointmentForPatient";
    }
	
	
	
	

}
