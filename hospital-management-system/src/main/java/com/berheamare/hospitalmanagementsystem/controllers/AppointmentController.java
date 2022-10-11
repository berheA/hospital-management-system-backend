package com.berheamare.hospitalmanagementsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berheamare.hospitalmanagementsystem.models.Appointment;
import com.berheamare.hospitalmanagementsystem.repositories.AppointmentRepository;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/hms/")
public class AppointmentController {

	    @Autowired
		private AppointmentRepository appointmentRepository;
		

	    @GetMapping("allAppointments")
		public List<Appointment> getAllAppointments(){
	    	return this.appointmentRepository.findAll();
		}
				
		@GetMapping("appointment/{id}")
		public Appointment getAppointment(@PathVariable("id") Long id) {
			return this.appointmentRepository.findById(id).orElseThrow();
		}
		
		@PostMapping("addAppointment")
		public @Valid Appointment addAppointment(@Valid @RequestBody Appointment appointment) {
			return this.appointmentRepository.save(appointment);
			
		}
		@PutMapping("updateAppointment/{id}")
		public Appointment updateAppointment(@RequestBody Appointment appointment, @PathVariable("id") Long id) {
			
			Appointment existingAppointment=this.appointmentRepository.findById(id).orElseThrow();
			existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
			return this.appointmentRepository.save(existingAppointment);	
		}
		 @DeleteMapping("deleteAppointment/{id}")
		public void deleteAppointment(@PathVariable("id") Long id) {
			this.appointmentRepository.deleteById(id);
		 }
}
