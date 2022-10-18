package com.berheamare.hospitalmanagementsystem.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
// persists the MedicalCenter information where patients visit
public class MedicalCenter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long medicalCenterId;
private String medicalCenterName;
private int phone;
@NotEmpty
@Email
private String email;
private String medicalCenterCity;
private String medicalCenterState;
private String medicalCenterStreetAddress;
private int medicalCenterZip;
private String specialService;

public MedicalCenter() {
	
}
public MedicalCenter(String medicalCenterName, 
		int phone, String email,
		String medicalCenterCity,
		String medicalCenterState, 
		String medicalCenterStreetAddress,
		int medicalCenterZip,
		String specialService) {
	
	this.medicalCenterName = medicalCenterName;
	this.phone = phone;
	this.email = email;
	this.medicalCenterCity = medicalCenterCity;
	this.medicalCenterState = medicalCenterState;
	this.medicalCenterStreetAddress = medicalCenterStreetAddress;
	this.medicalCenterZip = medicalCenterZip;
	this.specialService = specialService;
}
public Long getMedicalCenterId() {
	return medicalCenterId;
}
public void setMedicalCenterId(Long medicalCenterId) {
	this.medicalCenterId = medicalCenterId;
}
public String getMedicalCenterName() {
	return medicalCenterName;
}
public void setMedicalCenterName(String medicalCenterName) {
	this.medicalCenterName = medicalCenterName;
}
public int getPhone() {
	return phone;
}
public void setPhone(int phone) {
	this.phone = phone;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMedicalCenterCity() {
	return medicalCenterCity;
}
public void setMedicalCenterCity(String medicalCenterCity) {
	this.medicalCenterCity = medicalCenterCity;
}
public String getMedicalCenterState() {
	return medicalCenterState;
}
public void setMedicalCenterState(String medicalCenterState) {
	this.medicalCenterState = medicalCenterState;
}
public String getMedicalCenterStreetAddress() {
	return medicalCenterStreetAddress;
}
public void setMedicalCenterStreetAddress(String medicalCenterStreetAddress) {
	this.medicalCenterStreetAddress = medicalCenterStreetAddress;
}
public int getMedicalCenterZip() {
	return medicalCenterZip;
}
public void setMedicalCenterZip(int medicalCenterZip) {
	this.medicalCenterZip = medicalCenterZip;
}
public String getSpecialService() {
	return specialService;
}
public void setSpecialService(String specialService) {
	this.specialService = specialService;
}


}
