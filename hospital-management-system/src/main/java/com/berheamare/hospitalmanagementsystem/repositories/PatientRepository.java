package com.berheamare.hospitalmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berheamare.hospitalmanagementsystem.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

}
