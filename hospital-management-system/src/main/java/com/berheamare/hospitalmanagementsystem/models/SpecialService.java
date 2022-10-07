package com.berheamare.hospitalmanagementsystem.models;

// Persists special services(like X-ray, CTScan) offered by the medical center
public class SpecialService {
	private Long specilaServiceId;
	private String serviceName;
	private Bill bill;
	private MedicalCenter medicalCenter;
}
