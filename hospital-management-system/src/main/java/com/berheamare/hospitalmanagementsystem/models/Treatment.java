package com.berheamare.hospitalmanagementsystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Treatment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long treatmentId;
	private long patientId;
	private long doctorId;
	private String email;
	private String result;
	private String treatmentDate;
	private String assignedDate;
	private double bill;
	
	public Treatment() {
		
	}
	
	
	
	public Treatment(long patientId, long doctorId, String email, String result, String treatmentDate,
			String assignedDate, double bill) {
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.email = email;
		this.result = result;
		this.treatmentDate = treatmentDate;
		this.assignedDate = assignedDate;
		this.bill = bill;
	}



	public String getAssignedDate() {
		return assignedDate;
	}



	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}



	public Long getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(Long treatmentId) {
		this.treatmentId = treatmentId;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTreatmentDate() {
		return treatmentDate;
	}
	public void setTreatmentDate(String treatmentDate) {
		this.treatmentDate = treatmentDate;
	}
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getBill() {
		return bill;
	}
	public void setBill(double bill) {
		this.bill = bill;
	}

	
}
