package com.berheamare.hospitalmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berheamare.hospitalmanagementsystem.models.MedicalCenter;

public interface MedicalCenterRepository extends JpaRepository<MedicalCenter, Long> {

}
