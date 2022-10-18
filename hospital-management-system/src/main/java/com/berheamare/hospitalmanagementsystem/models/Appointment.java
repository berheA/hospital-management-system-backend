package com.berheamare.hospitalmanagementsystem.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

// persists all appointments to enable to track past visits

@Entity
@Table
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointmentId;
	@NotEmpty
	private String appointmentDate;
	private String appointmentStatus;
	//@OneToMany(targetEntity = MedicalCenter.class, cascade = CascadeType.ALL)
	private String medicalCenter;
	
	
	public Appointment() {
		
	}
	public Appointment(String appointmentDate, String appointmentStatus, String medicalCenter) {
		super();
		this.appointmentDate = appointmentDate;
		this.appointmentStatus = appointmentStatus;
		this.medicalCenter = medicalCenter;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public String getMedicalCenter() {
		return medicalCenter;
	}
	public void setMedicalCenter(String medicalCenter) {
		this.medicalCenter = medicalCenter;
	}
		
}
