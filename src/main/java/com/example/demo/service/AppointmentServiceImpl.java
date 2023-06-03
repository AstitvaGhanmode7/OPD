package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Appointment;
import com.example.demo.repo.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

	@Override
	public List<Appointment> getAppointmentHistory(String username) {
		// TODO Auto-generated method stub
		return appointmentRepository.findByPatientUsername(username);
	}

	@Override
	public List<Appointment> getAllAppointmentByPatientId(Long patientId) {
		// TODO Auto-generated method stub
		
		
		return appointmentRepository.findByPatientId(patientId);
	}

	@Override
	public List<Appointment> getAllAppointmentByDoctorId(Long doctorId) {
		// TODO Auto-generated method stub
		return appointmentRepository.findByDoctorId(doctorId);
	}

	@Override
	public Appointment findById(Long appointmentId) {
		// TODO Auto-generated method stub
		return appointmentRepository.findById(appointmentId).orElse(null);
	}

	@Override
	public void update(Appointment appointment) {
		// TODO Auto-generated method stub
		  // Retrieve the existing appointment from the database
	    Appointment existingAppointment = appointmentRepository.findById(appointment.getId()).orElse(null);

	    // Update only the status
	    existingAppointment.setStatus(appointment.getStatus());

	    // Save the updated appointment
	    appointmentRepository.save(existingAppointment);
		
	}
}
