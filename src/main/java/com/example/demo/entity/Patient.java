package com.example.demo.entity;




import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class Patient {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private Date DOB;
    
    private String gender;
    
    private String phoneNo;
    
    private String bloodGrp;
    
    private String address;
    
    private String username;
    
    private String password;
    
   
 // Relationship with Appointment entity (One-to-Many)
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
    
	
}
