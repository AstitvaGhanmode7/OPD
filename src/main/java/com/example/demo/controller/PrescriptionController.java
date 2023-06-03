package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Prescription;
import com.example.demo.repo.DoctorRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.PrescriptionService;



@Controller
@RequestMapping("/prescription")
public class PrescriptionController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private PrescriptionService prescriptionService;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	
	@PostMapping("/save")
	public String savePrescription(@RequestParam("appointmentId") Long appointmentId,
	                               @RequestParam("details") String details,
	                               @RequestParam("username") String username,Model model) {
		Doctor doctor = doctorRepository.findByUsername(username);
        // Add other required attributes
        model.addAttribute("name", doctor.getName());
        model.addAttribute("p_id", doctor.getId()); 
        model.addAttribute("username", doctor.getUsername()); 
        model.addAttribute("password", doctor.getPassword());
		Appointment appointment = appointmentService.findById(appointmentId);
	    Prescription prescription = new Prescription();
	    prescription.setDetails(details);
	    prescription.setAppointment(appointment);
	    prescriptionService.save(prescription);
	    appointment.setStatus("Completed");
	    appointmentService.update(appointment);
	    return "redirect:/doctor/appointmentHistory?username=" + username;
	}
	
	@GetMapping("/create")
	public String createPrescription(@RequestParam("appointmentId") Long appointmentId, @RequestParam("username") String username,Model model) {
	    
		Doctor doctor = doctorRepository.findByUsername(username);
        // Add other required attributes
        model.addAttribute("name", doctor.getName());
        model.addAttribute("p_id", doctor.getId()); 
        model.addAttribute("username", doctor.getUsername()); 
        model.addAttribute("password", doctor.getPassword());
		// Logic for retrieving appointment details
		Appointment appointment = appointmentService.findById(appointmentId);
		// Pass the appointment object to the view
	    model.addAttribute("appointmentId", appointmentId);
	    model.addAttribute("username", username);
	    // Return the prescription creation page
	    return "doctor/createPrescription";
	}
	
	  @GetMapping("/open")
	    public String openPrescription(@RequestParam("appointmentId") Long appointmentId, Model model) {
	      
		  
		  Appointment appointment = appointmentService.findById(appointmentId);
	        Prescription prescription = appointment.getPrescription();
	        
	        if(prescription == null) {
	        	return "patient/prescriptionNotCreated";
	        }
	        // Pass the prescription object to the view
	        model.addAttribute("prescription", prescription);
	        
	        // Get the patient's appointments for the appointment history table
	        List<Appointment> appointments = appointment.getPatient().getAppointments();
	        
	        // Pass the appointments as a model attribute
	        model.addAttribute("appointments", appointments);
	        model.addAttribute("appointmentId", appointmentId);
	        
	        // Return the prescription page
	        return "patient/prescriptionPage";
	    }
	
	  /*
	@GetMapping("/open")
	public String openPrescription(@RequestParam("appointmentId") Long appointmentId, Model model) {
	    Appointment appointment = appointmentService.findById(appointmentId);
	    Prescription prescription = appointment.getPrescription();
	    
	    // Pass the prescription object to the view
	    model.addAttribute("prescription", prescription);
	    
	    // Return the prescription page
	    return "patient/prescriptionPage";
	}
	*/
	
}
