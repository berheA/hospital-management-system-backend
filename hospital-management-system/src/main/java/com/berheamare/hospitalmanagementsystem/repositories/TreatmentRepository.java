package com.berheamare.hospitalmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berheamare.hospitalmanagementsystem.models.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

}
