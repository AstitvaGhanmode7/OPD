package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.entity.Receptionist;
import com.example.demo.entity.Specialization;
import com.example.demo.repo.AdminRepository;
import com.example.demo.repo.DoctorRepository;
import com.example.demo.repo.PatientRepository;
import com.example.demo.repo.ReceptionistRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;
import com.example.demo.service.ReceptionistService;
import com.example.demo.service.SpecializationService;

@Controller
public class HomeController {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminRepository adminRepository;
		
	@Autowired
	private SpecializationService specializationService;
	
	@GetMapping("/")
	public String showHomePage() {
		return "homePage";
	}
	
	@GetMapping("/homePage")
	public String showhomePage() {
		return "redirect:/";
	}
	
	@GetMapping("/aboutus")
	public String showAboutus() {
	
		return "aboutus";
	}
	
	@GetMapping("/contactus")
	public String showContactus() {
	
		return "contactus";
	}
	
	
	@GetMapping("/patientPage")
	public String showPatientPage() {
	
		return "patientPage";
	}
	
	@GetMapping("/register")
	public String showPatientPage(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient",patient);
		
		return "register";
	}
	/*
	@PostMapping("/savePatientForm")
	public String savePatientForm(@ModelAttribute("patient") Patient patient) {
		 // Set the patient role for the user
	   
	    // Save the patient entity
	    patientService.save(patient);
		return "redirect:/register";
	}
	*/
	
	
	@PostMapping("/savePatientForm")
	public String savePatientForm(@ModelAttribute("patient") Patient patient, RedirectAttributes redirectAttributes) {
	    List<Patient> listPatient = patientService.getAllPatient();

	    for (Patient existingPatient : listPatient) {
	        if (existingPatient.getUsername().equals(patient.getUsername())) {
	            String message = "Patient username is already present. Enter a new username.";
	            redirectAttributes.addFlashAttribute("message", message);
	            return "redirect:/register";
	        }
	    }

	    String message = "Patient Registered Successfully";
        redirectAttributes.addFlashAttribute("message2", message);
	    patientService.save(patient);
	    return "redirect:/register";
	}
	
	@GetMapping("/signin")
	public String showLoginPage() {
	
		return "signin";
	}
	
	 @GetMapping("/loginPatient")
	    public String loginPatient(@RequestParam("username") String username,
	                               @RequestParam("password") String password,
	                               RedirectAttributes redirectAttributes,
	                               Model model) {
	        // Here, you can implement the logic to search for the username and password in the database
	        // You can use a service or repository to perform the search
//System.out.println(username+password);
	        if (patientIsCredentialsValid(username, password)) {
	            // If the credentials are valid, you can add the necessary data to the model or session for the patient's home page
	            model.addAttribute("username", username);
	            model.addAttribute("password", password);
	            Patient patient = patientRepository.findByUsername(username);
	            // Add other required attributes
	            model.addAttribute("name", patient.getName());
	            model.addAttribute("id", patient.getId()); // Pass the patient's name as a model attribute
	            // Return the patient's home page
	            return "patient/patientHome";
	        } else {
	            // If the credentials are not valid, add an error message to the redirect attributes and redirect back to the login page
	            String message = "Invalid username or password";
	            redirectAttributes.addFlashAttribute("message", message);
	        	//redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
	            return "redirect:/signin";
	        }
	    }

	    private boolean patientIsCredentialsValid(String username, String password) {
	    	 Patient patient = patientRepository.findByUsername(username);

	         if (patient != null && patient.getPassword().equals(password)) {
	             return true;
	         }

	         return false;
	    }
	    
	    //EXTRASS
	    
	
	//DOCTOR API
	    
	    @GetMapping("/doctorPage")
		public String showDoctorPage() {
		
			return "doctorPage";
		}
	    
	    @GetMapping("/doctorSignin")
		public String showDoctorLoginPage() {
		
			return "doctorSignin";
		}
	    
	    @GetMapping("/doctorRegister")
	    public String showDoctorPage(Model model) {
	        Doctor doctor = new Doctor();
	        model.addAttribute("doctor", doctor);
	        model.addAttribute("specializations", specializationService.getAllSpecializations());
	        return "doctorRegister";
	    }
	    
	    
	    @PostMapping("/saveDoctorForm")
		public String saveDoctorForm(@ModelAttribute("doctor") Doctor doctor, RedirectAttributes redirectAttributes) {
		    List<Doctor> listDoctor = doctorService.getAllDoctor();

		    for (Doctor existingDoctor : listDoctor) {
		        if (existingDoctor.getUsername().equals(doctor.getUsername())) {
		            String message = "Doctor username is already present. Enter a new username.";
		            redirectAttributes.addFlashAttribute("message", message);
		            return "redirect:/register";
		        }
		    }

		    String message = "Admin Registered Successfully";
	        redirectAttributes.addFlashAttribute("message2", message);
		    doctorService.save(doctor);
		    return "redirect:/register";
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
			return "redirect:/doctorRegister";
		}
	*/
	
	    @GetMapping("/loginDoctor")
	    public String loginDoctor(@RequestParam("username") String username,
	                               @RequestParam("password") String password,
	                               RedirectAttributes redirectAttributes,
	                               Model model) {
	        // Here, you can implement the logic to search for the username and password in the database
	        // You can use a service or repository to perform the search
//System.out.println(username+password);
	        if (doctorIsCredentialsValid(username, password)) {
	            // If the credentials are valid, you can add the necessary data to the model or session for the patient's home page
	        	model.addAttribute("username", username);
	            model.addAttribute("password", password);
	            Doctor doctor = doctorRepository.findByUsername(username);
	            // Add other required attributes
	            model.addAttribute("name", doctor.getName());
	            model.addAttribute("id", doctor.getId()); // Pass the patient's name as a model attribute
	            // Return the patient's home page
	            return "doctor/doctorHome";
	        } else {
	            // If the credentials are not valid, add an error message to the redirect attributes and redirect back to the login page
	        	 String message = "Invalid username or password";
		            redirectAttributes.addFlashAttribute("message", message);
	        	//redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
	            return "redirect:/doctorSignin";
	        }
	    }

	    private boolean doctorIsCredentialsValid(String username, String password) {
	    	 Doctor doctor = doctorRepository.findByUsername(username);

	         if (doctor != null && doctor.getPassword().equals(password)) {
	             return true;
	         }

	         return false;
	    }
	
	    
	    //ADMIN PAGE
	    
	    
	    
	    @GetMapping("/adminPage")
		public String showAdminPage() {
		
			return "adminPage";
		}
	    
	    @GetMapping("/adminSignin")
		public String showAdminLoginPage() {
		
			return "adminSignin";
		}
	    
	    @GetMapping("/adminRegister")
	    public String showAdminRegisterPage(Model model) {
	       
	    	Admin admin = new Admin();
	        model.addAttribute("admin", admin);
	        
	        return "adminRegister";
	    }
	    
	    
	    /*
	    @PostMapping("/saveAdminForm")
		public String saveAdminForm(@ModelAttribute("admin") Admin admin) {
			
			    adminService.save(admin);
			return "redirect:/adminRegister";
		}
		*/
	    
	    @PostMapping("/saveAdminForm")
		public String saveAdminForm(@ModelAttribute("admin") Admin admin, RedirectAttributes redirectAttributes) {
		    List<Admin> listAdmin = adminService.getAllAdmin();

		    for (Admin existingAdmin : listAdmin) {
		        if (existingAdmin.getUsername().equals(admin.getUsername())) {
		            String message = "Admin username is already present. Enter a new Username.";
		            redirectAttributes.addFlashAttribute("message", message);
		            return "redirect:/adminRegister";
		        }
		    }

		    
		    adminService.save(admin);
		    return "redirect:/adminRegister";
		}
	
	    
	    @GetMapping("/loginAdmin")
	    public String loginAdmin(@RequestParam("username") String username,
	                               @RequestParam("password") String password,
	                               RedirectAttributes redirectAttributes,
	                               Model model) {
	        // Here, you can implement the logic to search for the username and password in the database
	        // You can use a service or repository to perform the search
//System.out.println(username+password);
	        if (adminIsCredentialsValid(username, password)) {
	            // If the credentials are valid, you can add the necessary data to the model or session for the patient's home page
	            model.addAttribute("username1", username);
	            model.addAttribute("password", password);
	            Admin admin = adminRepository.findByUsername(username);
	            // Add other required attributes
	            model.addAttribute("name", admin.getName());
	            model.addAttribute("id", admin.getId()); // Pass the patient's name as a model attribute
	            // Return the patient's home page
	            
	            long countPatients =  patientService.countPatients();
	            long countDoctors =  doctorService.countDoctors();
	            long countReceptionists =  receptionistService.countReceptionists();
	            
	            model.addAttribute("countPatients",countPatients);
	            model.addAttribute("countDoctors",countDoctors);
	            model.addAttribute("countReceptionists",countReceptionists);
	            
	            
	            return "admin/adminHome";
	        } else {
	            // If the credentials are not valid, add an error message to the redirect attributes and redirect back to the login page
	        	 String message = "Invalid username or password";
		            redirectAttributes.addFlashAttribute("message", message);
	        	//redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
	            return "redirect:/adminSignin";
	        }
	    }

	    private boolean adminIsCredentialsValid(String username, String password) {
	    	Admin admin = adminRepository.findByUsername(username);

	         if (admin != null && admin.getPassword().equals(password)) {
	             return true;
	         }

	         return false;
	    }
	
	    
	    @GetMapping("/search")
		public String doSearchDoctor(@RequestParam("name") String name, Model model) {
		    
		    List<Doctor> doctors = doctorService.getAllDoctors();
		    Specialization specialization2 = specializationService.getSpecializationByName(name);
		    Long s_id = specialization2.getId();
		    List<Doctor> doctors2 = new ArrayList();
		    
		    for (Doctor doctor : doctors) {
		        if (specialization2.getId().equals(doctor.getSpecialization().getId())) {
		            // Match found, you can perform your desired logic here
		        	 doctors2.add(doctor); // Exit the loop since a match is found
		        }
		    }
		    /*
			List<Doctor> doctors = doctorService.findBySpecializationId(specialization2.getId());
		    System.out.println(doctors);
		    */
		    //model.addAttribute("specialization", specialization);
		   model.addAttribute("doctors", doctors2);
		    return "redirect:admin/bookAppointmentByPatient2";
		}
	    
	    
	    
	    //RECEPTIONIST PAGE

	    @Autowired
	    private ReceptionistService receptionistService;
	    
	    @Autowired
	    private ReceptionistRepository receptionistRepository;
	    
	    @GetMapping("/receptionistPage")
		public String showReceptionistPage() {
		
			return "receptionistPage";
		}
	    
	    @GetMapping("/receptionistRegister")
		public String showReceptionistPage(Model model) {
	    	Receptionist receptionist = new Receptionist();
			model.addAttribute("receptionist",receptionist);
			
			return "receptionistRegister";
		}
	    /*
	    @PostMapping("/saveReceptionistForm")
		public String saveReceptionistForm(@ModelAttribute("receptionist") Receptionist receptionist) {
			 // Set the patient role for the user
		    
		   
		    // Save the patient entity
		    receptionistService.save(receptionist);
			return "redirect:/receptionistRegister";
		}
		*/
	    
	    @PostMapping("/saveReceptionistForm")
		public String saveAdminForm(@ModelAttribute("receptionist") Receptionist receptionist, RedirectAttributes redirectAttributes) {
		    List<Receptionist> listReceptionist = receptionistService.getAllReceptionist();

		    for (Receptionist existingReceptionist : listReceptionist) {
		        if (existingReceptionist.getUsername().equals(receptionist.getUsername())) {
		            String message = "Receptionist username is already present. Enter a new Username.";
		            redirectAttributes.addFlashAttribute("message", message);
		            return "redirect:/receptionistRegister";
		        }
		    }

		    
		    receptionistService.save(receptionist);
		    return "redirect:/receptionistRegister";
		}
	    
	    @GetMapping("/receptionistSignin")
		public String showReceptionistLoginPage() {
		
			return "receptionistSignin";
		}
		
	    @GetMapping("/loginReceptionist")
	    public String loginReceptionist(@RequestParam("username") String username,
	                               @RequestParam("password") String password,
	                               RedirectAttributes redirectAttributes,
	                               Model model) {
	       
	        if (receptionistIsCredentialsValid(username, password)) {
	            // If the credentials are valid, you can add the necessary data to the model or session for the patient's home page
	            model.addAttribute("username", username);
	            model.addAttribute("password", password);
	            Receptionist receptionist = receptionistRepository.findByUsername(username);
	            // Add other required attributes
	            model.addAttribute("name", receptionist.getName());
	            model.addAttribute("id", receptionist.getId()); // Pass the patient's name as a model attribute
	            // Return the patient's home page
	            return "receptionist/receptionistHome";
	        } else {
	        	 String message = "Invalid username or password";
		            redirectAttributes.addFlashAttribute("message", message);
	            //redirectAttributes.addFlashAttribute("errorMessage", "Invalid username or password");
	            return "redirect:/receptionistSignin";
	        }
	    }

	    private boolean receptionistIsCredentialsValid(String username, String password) {
	    	Receptionist receptionist = receptionistRepository.findByUsername(username);

	         if (receptionist != null && receptionist.getPassword().equals(password)) {
	             return true;
	         }

	         return false;
	    }
	    
	    
	
	/*
	@GetMapping("/public")
	public ResponseEntity<String> publicUser(){
		return ResponseEntity.ok("Yes, I am public user");
	}
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private SpecializationService specializationService;
	
	@Autowired
	private RoleRepository roleRepository;

	@GetMapping("/")
	public String showHomePage() {
		return "homePage";
	}
	
	@GetMapping("/homePage")
	public String showhomePage() {
		return "redirect:/";
	}
	
	@GetMapping("/signin")
	public String showLoginPage() {
	
		return "signin";
	}
	
	@GetMapping("/doctorSignin")
	public String showDoctorLoginPage() {
	
		return "doctorSignin";
	}
	
	@GetMapping("/patientPage")
	public String showPatientPage() {
	
		return "patientPage";
	}
	
	@GetMapping("/doctorPage")
	public String showDoctorPage() {
	
		return "doctorPage";
	}
	
	
	@GetMapping("/logout")
    public String logoutPatientHomePage() {
        return "redirect:/";
    }
	
	
	@GetMapping("/register")
	public String showPatientPage(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient",patient);
		
		return "register";
	}
	
	 @GetMapping("/doctorRegister")
	    public String showDoctorPage(Model model) {
	        Doctor doctor = new Doctor();
	        model.addAttribute("doctor", doctor);
	        model.addAttribute("specializations", specializationService.getAllSpecializations());
	        return "doctorRegister";
	    }
	
	@PostMapping("/savePatientForm")
	public String savePatientForm(@ModelAttribute("patient") Patient patient) {
		 // Set the patient role for the user
	    
	   
	    // Save the patient entity
	    patientService.save(patient);
		return "redirect:/register";
	}
	
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
		return "redirect:/doctorRegister";
	}
	
	
	@GetMapping("/defaultRedirect")
    public String defaultRedirect(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("PATIENT"))) {
            return "patient/patientHome";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("DOCTOR"))) {
            return "doctor/doctorHome";
        }
        // Handle other roles or scenarios as needed
        return "redirect:/";
    }
	*/
}
