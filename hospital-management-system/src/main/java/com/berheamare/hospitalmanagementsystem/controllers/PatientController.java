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
import com.berheamare.hospitalmanagementsystem.models.Patient;
import com.berheamare.hospitalmanagementsystem.repositories.EmailSender;
import com.berheamare.hospitalmanagementsystem.repositories.PatientRepository;
import com.berheamare.hospitalmanagementsystem.services.PatientService;



	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RestController
	@RequestMapping("/hms/")
	public class PatientController {

		    @Autowired
			private PatientRepository patientRepository;
		    
		    @Autowired
		    private EmailSender emailSender;
          @Autowired
          private PatientService patientService;
		    @GetMapping("allPatients")
			public List<Patient> getAllPatients(){
		    	return this.patientRepository.findAll();
			}
					
			@GetMapping("patient/{id}")
			public Patient getPatient(@PathVariable("id") Long id) {
				return this.patientRepository.findById(id).orElseThrow();
			}
			
			@PostMapping("addPatient")
			public @Valid Patient addPatient(@Valid @RequestBody Patient patient) {
				
				String link= "http://localhost:4200/login";
//				emailSender.send(  patient.getEmail(), 
//						patientService.buildEmailCreateApptNotification(patient.getPatientFirstName(), link));
//				
				return this.patientRepository.save(patient);
				
			}
			@PutMapping("updatePatient/{id}")
			public Patient updatePatient(@RequestBody Patient patient, @PathVariable("id") Long id) {
				String link= "http://localhost:4200/login";
//				emailSender.send(  patient.getEmail(), 
//						patientService.buildEmailUpdateApptNotification(patient.getPatientFirstName(), link));
//				
				Patient existingPatient=this.patientRepository.findById(id).orElseThrow();
				existingPatient.setAppointment(patient.getAppointment());
				return this.patientRepository.save(existingPatient);	
			}
			 @DeleteMapping("deletePatient/{id}")
			public void deletePatient(@PathVariable("id") Long id) {
				 
				 Patient existingPatient=this.patientRepository.findById(id).orElseThrow();
				 String link= "http://localhost:4200/login";
//					emailSender.send(  existingPatient.getEmail(), 
//							patientService.buildEmailDeleteApptNotification(existingPatient.getPatientFirstName(), link));
//					
					this.patientRepository.deleteById(id);
				
			 }
			 
			 
}
