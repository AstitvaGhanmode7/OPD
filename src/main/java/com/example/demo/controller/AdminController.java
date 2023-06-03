package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.entity.Specialization;
import com.example.demo.repo.AdminRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.SpecializationService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private SpecializationService specializationService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	
	@GetMapping("/profile")
	public String createProfile(@RequestParam("username1")String username1, Model model) {
		
		Admin admin = adminRepository.findByUsername(username1);
		
		model.addAttribute("admin",admin);
		model.addAttribute("id",admin.getId());
		model.addAttribute("username1", admin.getUsername()); 
		model.addAttribute("password", admin.getPassword());
		
		return "admin/profile";
	}
	

	
	@GetMapping("/createSpecialization")
	public String createSpecialization(@RequestParam("username1") String username1,Model model) {
		
		Admin admin = adminRepository.findByUsername(username1);
        // Add other required attributes
        model.addAttribute("name", admin.getName());
        model.addAttribute("id", admin.getId()); 
        model.addAttribute("username1", admin.getUsername()); 
        model.addAttribute("password", admin.getPassword());
		
        model.addAttribute("admin", admin); 
		Specialization specialization = new Specialization();
		
		model.addAttribute("specialization",specialization);
		 List<Specialization> specializations = specializationService.getAllSpecializations();
	        model.addAttribute("specializations", specializations);
		
		return "admin/createSpecialization";
	}
	
	
	
	@PostMapping("/saveSpecializationForm")
	public String saveSpecializationForm(@ModelAttribute("specialization") Specialization specialization, RedirectAttributes redirectAttributes,Model model,@RequestParam("username1") String username1) {
	    List<Specialization> specializationList = specializationService.getAllSpecializations();
	    
	    if (specializationService.getSpecializationByName(specialization.getName()) != null) {
	    	 Admin admin = adminRepository.findByUsername(username1);
		        // Add other required attributes
		        model.addAttribute("name", admin.getName());
		        model.addAttribute("id", admin.getId()); 
		        model.addAttribute("username1", admin.getUsername()); 

	            model.addAttribute("password", admin.getPassword());
				
		        model.addAttribute("admin", admin); 
	    	String message = "Specialization is already present. Enter a new specialization.";
	        redirectAttributes.addFlashAttribute("message", message);
	        return "redirect:/admin/createSpecialization?username1="+username1;
	    }
	    Admin admin = adminRepository.findByUsername(username1);
        // Add other required attributes
        model.addAttribute("name", admin.getName());
        model.addAttribute("id", admin.getId()); 
        model.addAttribute("username1", admin.getUsername()); 
        model.addAttribute("password", admin.getPassword());
		
        model.addAttribute("admin", admin); 
	    String message1 = "Specialization Added Successfully !";
        redirectAttributes.addFlashAttribute("message1", message1);
	   
	    specializationService.save(specialization);
	    return "redirect:/admin/createSpecialization?username1="+username1;
	}
	
	
	 @GetMapping("/createDoctor")
	    public String showDoctorPage(@RequestParam("username1") String username1 ,Model model) {
		 
		 Admin admin = adminRepository.findByUsername(username1);
	        // Add other required attributes
	        model.addAttribute("name", admin.getName());
	        model.addAttribute("id", admin.getId()); 
	        model.addAttribute("username1", admin.getUsername()); 
	        model.addAttribute("password", admin.getPassword());
			
	        model.addAttribute("admin", admin); 
	        Doctor doctor = new Doctor();
	        model.addAttribute("doctor", doctor);
	        model.addAttribute("specializations", specializationService.getAllSpecializations());
	        return "admin/createDoctor";
	    }
	 
	  @PostMapping("/saveDoctorForm")
			public String saveDoctorForm(@ModelAttribute("doctor") Doctor doctor, @RequestParam("specialization.id") Long specializationId,RedirectAttributes redirectAttributes,@RequestParam("username1") String username1,Model model ) {
			    List<Doctor> listDoctor = doctorService.getAllDoctor();
			    System.out.println(username1);
			    Admin admin = adminRepository.findByUsername(username1);
			    
		        // Add other required attributes
		        model.addAttribute("username1", admin.getUsername()); 
		        model.addAttribute("password", admin.getPassword());
				
		        model.addAttribute("admin", admin); 
			    
			    for (Doctor existingDoctor : listDoctor) {
			        if (existingDoctor.getUsername().equals(doctor.getUsername())) {
			 	        // Add other required attributes
			 	       
			            String message = "Doctor E-mail is already present. Enter a new E-mail.";
			            redirectAttributes.addFlashAttribute("message", message);
			            return "redirect:/admin/createDoctor?username1="+username1;
			        }
			    }

			    // Retrieve the specialization entity based on the ID
			    Specialization specialization = specializationService.getSpecializationById(specializationId);

			    // Set the selected specialization for the doctor
			    doctor.setSpecialization(specialization);

			    doctorService.save(doctor);
			    return "redirect:/admin/createDoctor?username1="+username1;
			}
		    
	
	 /*
	@PostMapping("/saveDoctorForm")
	public String saveDoctorForm(@ModelAttribute("doctor") Doctor doctor, @RequestParam("specialization.id") Long specializationId) {
		 System.out.println("saveDoctorForm method called"); 
		// Set the doctor role for the user
		   

		    // Retrieve the specialization entity based on the ID
		    Specialization specialization = specializationService.getSpecializationById(specializationId);

		    // Set the selected specialization for the doctor
		    doctor.setSpecialization(specialization);

		    // Save the doctor entity
		    doctorService.save(doctor);
		return "redirect:/admin/createDoctor";
	}
	*/
	
}
