package com.berheamare.hospitalmanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berheamare.hospitalmanagementsystem.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
