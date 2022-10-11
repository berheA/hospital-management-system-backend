package com.berheamare.hospitalmanagementsystem.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

// holds patient information

//@Data
@Entity
@Table
public class Patient {
	@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long patientId;
private int age;
private String patientFirstName;
private String patientLastName;
private String email;
//@OneToOne(targetEntity = User.class, cascade =  CascadeType.ALL)
//private User user;

@OneToOne( cascade =  CascadeType.ALL)
private MedicalCenter MedicalCenter;

@OneToMany(targetEntity =Appointment.class , cascade =  CascadeType.ALL)
private List<Appointment> appointment;

private Long userId;

public Patient() {
	
}

public Patient(int age, 
		String patientFirstName,
		String patientLastName,
		String email,
		List<Appointment> appointment,
		MedicalCenter medicalCenter, 
		Long userId) {
	this.age = age;
	this.patientFirstName = patientFirstName;
	this.patientLastName = patientLastName;
	this.email = email;
	MedicalCenter = medicalCenter;
	this.appointment = appointment;
	this.userId = userId;
}



















public Long getUserId() {
	return userId;
}









public void setUserId(Long userId) {
	this.userId = userId;
}









public String getEmail() {
	return email;
}




public void setEmail(String email) {
	this.email = email;
}




public Long getPatientId() {
	return patientId;
}



public void setPatientId(Long patientId) {
	this.patientId = patientId;
}



public int getAge() {
	return age;
}



public void setAge(int age) {
	this.age = age;
}



public String getPatientFirstName() {
	return patientFirstName;
}



public void setPatientFirstName(String patientFirstName) {
	this.patientFirstName = patientFirstName;
}



public String getPatientLastName() {
	return patientLastName;
}



public void setPatientLastName(String patientLastName) {
	this.patientLastName = patientLastName;
}



public MedicalCenter getMedicalCenter() {
	return MedicalCenter;
}



public void setMedicalCenter(MedicalCenter medicalCenter) {
	MedicalCenter = medicalCenter;
}



public List<Appointment> getAppointment() {
	return appointment;
}


public void setAppointment(List<Appointment> appointment) {
	this.appointment = appointment;
}

}
