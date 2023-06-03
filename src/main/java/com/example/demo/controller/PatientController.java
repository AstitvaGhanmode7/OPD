package com.example.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.entity.Specialization;
import com.example.demo.repo.DoctorRepository;
import com.example.demo.repo.PatientRepository;
import com.example.demo.repo.SpecializationRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;
import com.example.demo.service.SpecializationService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private DoctorService doctorService;
		
	@Autowired
	private SpecializationRepository specializationRepository;
	
	@Autowired
	private AppointmentService appointmentService;
/*
	@GetMapping("/bookAppointment")
    public String bookAppointment(Model model, @RequestParam("username") String username) {
		
		Appointment appointment = new Appointment();
		
		model.addAttribute("appointment",appointment);
		
		Patient patient = patientRepository.findByUsername(username);
        // Add other required attributes
        model.addAttribute("name", patient.getName());
        model.addAttribute("p_id", patient.getId()); 
        model.addAttribute("username", patient.getUsername()); 
		
        model.addAttribute("patient", patient); 
        
       
		
        return "patient/bookAppointment";
    }
    */
	
	@GetMapping("/bookAppointment")
    public String bookAppointment(Model model) {
		
		Appointment appointment = new Appointment();
		
		model.addAttribute("appointment",appointment);
		
        return "patient/bookAppointment";
    }
	
	//PROFILE
	@GetMapping("/profile")
    public String profile(@RequestParam("username") String username,Model model) {
		
		Patient patient = patientRepository.findByUsername(username);
        // Add other required attributes
        model.addAttribute("name", patient.getName());
        model.addAttribute("p_id", patient.getId()); 
        model.addAttribute("username", patient.getUsername()); 
		
        model.addAttribute("patient", patient); 
        
		Appointment appointment = new Appointment();
		
		model.addAttribute("appointment",appointment);
		
        return "patient/profile";
    }
	
	
	
	//LIST OF DOCTORS
	
	@GetMapping("/doctorsList")
    public String doctorsList(Model model, @RequestParam("username") String username) {
        // Add necessary code to fetch and prepare data for the doctorsList page
		Patient patient = patientRepository.findByUsername(username);
        // Add other required attributes
        model.addAttribute("name", patient.getName());
        model.addAttribute("p_id", patient.getId()); 
        model.addAttribute("username", patient.getUsername()); 
		
        model.addAttribute("patient", patient); 
        // For example:
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        
        return "patient/doctorsList";
    }
    
	 @GetMapping("/appointmentHistory")
	    public String showAppointmentHistory(Model model, @RequestParam("username") String username) {
	        // Assuming you have a method to retrieve the appointment history for the given patient username
	     System.out.println(username);  
	     Patient patient = patientRepository.findByUsername(username);
	        // Add other required attributes
	        model.addAttribute("name", patient.getName());
	        model.addAttribute("p_id", patient.getId()); 
	        model.addAttribute("username", patient.getUsername()); 
			
	        model.addAttribute("patient", patient); 
	        
	     
	     Long patientId = patient.getId();
		 List<Appointment> appointments = appointmentService.getAllAppointmentByPatientId(patientId);
	        
	        model.addAttribute("appointments", appointments);
	        
	        return "patient/appointmentHistory";
	    }

	
	/*
		@Autowired
	 	private PatientService patientService;

		@Autowired
	 	private DoctorService doctorService;
		
		@Autowired
	 	private SpecializationService specializationService;
		
	    public PatientController(PatientService patientService) {
	        this.patientService = patientService;
	    }

	    @GetMapping("/patientHome")
	    public String showPatientHomePage(Model model, Authentication authentication) {
		 String username = authentication.getName();   
		 Patient patient = patientService.getPatientByUsername(authentication.getName());
		 System.out.println(patient.getName());
	        model.addAttribute("patient", patient);
	        model.addAttribute("name", patient.getName());

	        model.addAttribute("id", patient.getId());
	        return "patient/patientHome";
	    }

	   
	    
	    @GetMapping("/bookAppointment")
	    public String bookAppointment(Model model) {
	    	/*
	    	Appointment appointment = new Appointment();
	    	
	    	List<Specialization> specializations = specializationService.getAllSpecializations(); // Assuming you have a method in DoctorService to retrieve all specializations
	        model.addAttribute("specializations", specializations);
	    	
	    	model.addAttribute(appointment);
	    	
	        return "patient/bookAppointment";
	    }
	    
	    @GetMapping("/appointmentHistory")
	    public String appointmentHistory(Model model) {
	        // Add necessary attributes to the model if required
	        return "patient/appointmentHistory";
	    }
	    
	    
	    
	    
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Patient> getPatient(@PathVariable("id") Long id) {
	        Patient patient = patientService.findById(id);
	        return ResponseEntity.ok(patient);
	    }

	    @PostMapping
	    public ResponseEntity<Void> createPatient(@RequestBody Patient patient) {
	        patientService.save(patient);
	        return ResponseEntity.status(HttpStatus.CREATED).build();
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Void> updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) {
	        patient.setId(id);
	        patientService.update(patient);
	        return ResponseEntity.ok().build();
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
	        patientService.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	*/
}
