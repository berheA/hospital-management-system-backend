package com.berheamare.hospitalmanagementsystem.crudrepositorytests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.berheamare.hospitalmanagementsystem.models.Appointment;
import com.berheamare.hospitalmanagementsystem.models.MedicalCenter;
import com.berheamare.hospitalmanagementsystem.models.Patient;
import com.berheamare.hospitalmanagementsystem.repositories.PatientRepository;
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class PatientRepositoryTests {

	  @Autowired
	    private PatientRepository repository;
	@Test
	// @Rollback(false) to disable roll back to the data will be committed
	// to the database and available for the next test.
	@Rollback(false)
	// JUnit doesn’t run test methods in the order they appear in the code
	// So @Order  helps us to keep data for the subsequence tests.
	 @Order(1)
  public void testSaveNewPatient() {
		MedicalCenter medicalCenter=new MedicalCenter(
				"Highline Hospital", 
				2067777, "hiline@hospital.com",
				"Des Moines",
				"WA", 
				"Java 8 Street Spring boot",
				23444,
				"X-RAY");
		List<Appointment> appointmentList= new ArrayList<>();
		Patient savedPatient =repository.save(new Patient(23,
				"Calvin",
				"La", 
				"calvin@smartnet.com",
				appointmentList,
				medicalCenter, 
				1L));
		
	     
// I use assertThat() method from AssertJ library for more readability than 
//	using JUnit’s assertion methods		                           
	    assertThat(savedPatient.getPatientId()).isGreaterThan(0);
		   

  }

	@Test
	@Order(2)
//	 This test method to test finding a patient by Id
	public void testFindPatientById() {
	    Patient patient = repository.findById(1L).orElseThrow();    
	    assertThat(patient.getPatientId()).isEqualTo(1L);
	}
	
	@Test
	@Order(3)
	//this test method to test retrieving all patients
	public void testListPatients() {
	    List<Patient> patients = (List<Patient>) repository.findAll();
	    assertThat(patients).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	//first we get the patient by its id, then update its firstName. 
	//Then we get the patient again, and compare the firstName
	public void testUpdatePatient() {
	    Patient patient = repository.findById(1L).orElseThrow();
	    patient.setPatientFirstName("Berhe"); 
	    repository.save(patient);	     
	    Patient updatedPatient = repository.findById(1L).orElseThrow();   
	    assertThat(updatedPatient.getPatientFirstName()).isEqualTo("Berhe");
	}
	
	
	//This test method is for testing delete a patient by its Id
	@Test
	@Rollback(false)
	@Order(5)
	public void testDeletePatient() {
	    Patient patient = repository.findById(1L).orElseThrow();
	    repository.deleteById(patient.getUserId());	     
	    Optional<Patient> deletedPatient = repository.findById(1L);     
	    assertEquals(deletedPatient, Optional.empty());     
	}
}
