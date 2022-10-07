package com.berheamare.hospitalmanagementsystem.models;

import java.time.LocalDate;

// persists all appointments to enable to track past visits
public class Appointment {
	private Long appointmentId;
	private Patient patient;
	private MedicalCenter medicalCenter;
	private LocalDate appointmentDate;
	private String appointmentStatus;
}
