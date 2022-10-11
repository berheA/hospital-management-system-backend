package com.berheamare.hospitalmanagementsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berheamare.hospitalmanagementsystem.models.MedicalCenter;
import com.berheamare.hospitalmanagementsystem.repositories.MedicalCenterRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/hms/")
public class MedicalCenterController {
    @Autowired
	private MedicalCenterRepository medicalCenterRepository;
	

    @GetMapping("allMedicalCenters")
	public List<MedicalCenter> getAllMedicalCenters(){
    	return this.medicalCenterRepository.findAll();
	}
	
	
	@GetMapping("medicalCenter/{id}")
	public MedicalCenter getMedicalCenter(@PathVariable("id") Long id) {
		return this.medicalCenterRepository.findById(id).orElseThrow();
	}
	
	@PostMapping("addMedicalCenter")
	public @Valid MedicalCenter addMedicalCenter(@Valid @RequestBody MedicalCenter medicalCenter) {
		return this.medicalCenterRepository.save(medicalCenter);
		
	}
	@PutMapping("updateMedicalCenter/{id}")
	public MedicalCenter updateMedicalCenter(@RequestBody MedicalCenter medicalCenter, @PathVariable("id") Long id) {
		
		MedicalCenter existingMedicalCenter=this.medicalCenterRepository.findById(id).orElseThrow();
		existingMedicalCenter.setMedicalCenterName(medicalCenter.getMedicalCenterName());
		existingMedicalCenter.setEmail(medicalCenter.getEmail());
		existingMedicalCenter.setPhone(medicalCenter.getPhone());
		existingMedicalCenter.setMedicalCenterState(medicalCenter.getMedicalCenterState());
		existingMedicalCenter.setMedicalCenterCity(medicalCenter.getMedicalCenterCity());
		existingMedicalCenter.setMedicalCenterStreetAddress(medicalCenter.getMedicalCenterStreetAddress());
		existingMedicalCenter.setMedicalCenterZip(medicalCenter.getMedicalCenterZip());
		return this.medicalCenterRepository.save(existingMedicalCenter);	
	}
	 @DeleteMapping("deleteMedicalCenter/{id}")
	public void deleteMedicalCenter(@PathVariable("id") Long id) {
		this.medicalCenterRepository.deleteById(id);
	 }
		
	}