package com.berheamare.hospitalmanagementsystem.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

// Persists special services(like X-ray, CTScan) offered by the medical center

@Entity
@Table
public class SpecialService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long specialServiceId;
	private String serviceName;
//	@ManyToMany(  cascade = CascadeType.ALL)
//	private List<MedicalCenter> medicalCenter;
	public SpecialService() {
	}
	public SpecialService(String serviceName, List<MedicalCenter> medicalCenter) {
		super();
		this.serviceName = serviceName;
		//this.medicalCenter = medicalCenter;
	}
	public Long getSpecialServiceId() {
		return specialServiceId;
	}
	public void setSpecialServiceId(Long specilaServiceId) {
		this.specialServiceId = specilaServiceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
//	public List<MedicalCenter> getMedicalCenter() {
//		return medicalCenter;
//	}
//	public void setMedicalCenter(List<MedicalCenter> medicalCenter) {
//		this.medicalCenter = medicalCenter;
//	}
	
	
	
}
