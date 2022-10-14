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
import com.berheamare.hospitalmanagementsystem.models.Treatment;
import com.berheamare.hospitalmanagementsystem.repositories.EmailSender;
import com.berheamare.hospitalmanagementsystem.repositories.TreatmentRepository;
import com.berheamare.hospitalmanagementsystem.services.TreatmentService;



	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RestController
	@RequestMapping("/hms/")
	public class TreatmentController {

		    @Autowired
			private TreatmentRepository treatmentRepository;
		    
		    @Autowired
		    private EmailSender emailSender;
          @Autowired
          private TreatmentService treatmentService;
		    @GetMapping("allTreatments")
			public List<Treatment> getAllTreatments(){
		    	return this.treatmentRepository.findAll();
			}
					
			@GetMapping("treatment/{id}")
			public Treatment getTreatment(@PathVariable("id") Long id) {
				return this.treatmentRepository.findById(id).orElseThrow();
			}
			
			@PostMapping("addTreatment")
			public @Valid Treatment addTreatment(@Valid @RequestBody Treatment treatment) {
				
				String link= "http://localhost:4200/login";
//				emailSender.send(  treatment.getEmail(), 
//						treatmentService.buildEmailCreateApptNotification(treatment.getTreatmentFirstName(), link));
//				
				return this.treatmentRepository.save(treatment);
				
			}
			@PutMapping("updateTreatment/{id}")
			public Treatment updateTreatment(@RequestBody Treatment treatment, @PathVariable("id") Long id) {
				String link= "http://localhost:4200/login";
//				emailSender.send(  treatment.getEmail(), 
//						treatmentService.buildEmailUpdateApptNotification(treatment.getTreatmentFirstName(), link));
//				
				Treatment existingTreatment=this.treatmentRepository.findById(id).orElseThrow();
				existingTreatment.setResult(treatment.getResult());
				existingTreatment.setBill(treatment.getBill());
				existingTreatment.setTreatmentDate(treatment.getTreatmentDate());
				return this.treatmentRepository.save(existingTreatment);	
			}
			 @DeleteMapping("deleteTreatment/{id}")
			public void deleteTreatment(@PathVariable("id") Long id) {
				 
				 //Treatment existingTreatment=this.treatmentRepository.findById(id).orElseThrow();
				 String link= "http://localhost:4200/login";
//					emailSender.send(  existingTreatment.getEmail(), 
//							treatmentService.buildEmailDeleteApptNotification(existingTreatment.getTreatmentFirstName(), link));
//					
					this.treatmentRepository.deleteById(id);
				
			 }
			 
			 
}
