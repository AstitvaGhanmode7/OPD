package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Appointment;

public interface AppointmentService {
	 List<Appointment> getAllAppointments();
	    Optional<Appointment> getAppointmentById(Long id);
	    Appointment saveAppointment(Appointment appointment);
	    void deleteAppointment(Long id);
	    List<Appointment> getAppointmentHistory(String username);
	    List<Appointment> getAllAppointmentByPatientId(Long patientId);
		List<Appointment> getAllAppointmentByDoctorId(Long doctorId);
		Appointment findById(Long appointmentId);
		void update(Appointment appointment);
}
