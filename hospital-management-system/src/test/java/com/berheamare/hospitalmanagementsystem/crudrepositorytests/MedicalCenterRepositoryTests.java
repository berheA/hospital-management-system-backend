package com.berheamare.hospitalmanagementsystem.crudrepositorytests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import com.berheamare.hospitalmanagementsystem.models.MedicalCenter;
import com.berheamare.hospitalmanagementsystem.repositories.MedicalCenterRepository;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class MedicalCenterRepositoryTests {

	  @Autowired
	    private MedicalCenterRepository repository;
	@Test
	// @Rollback(false) to disable roll back to the data will be committed
	// to the database and available for the next test.
	@Rollback(false)
	// JUnit doesn’t run test methods in the order they appear in the code
	// So @Order  helps us to keep data for the subsequence tests.
	 @Order(1)
  public void testSaveNewMedicalCenter() {
		
		
		MedicalCenter savedMedicalCenter =repository.save(new MedicalCenter("Highline Hospital", 
				2067777, "hiline@hospital.com",
				"Des Moines",
				"WA", 
				"Java 8 Street Spring boot",
				23444,
				"X-RAY"));
		
	     
// I use assertThat() method from AssertJ library for more readability than 
//	using JUnit’s assertion methods		                           
	    assertThat(savedMedicalCenter.getMedicalCenterId()).isGreaterThan(0);	   

  }

	@Test
	@Order(2)
//	 This test method to test finding a medicalCenter by Id
	public void testFindMedicalCenterById() {
	    MedicalCenter medicalCenter = repository.findById(1L).orElseThrow();    
	    assertThat(medicalCenter.getMedicalCenterId()).isEqualTo(1L);
	}
	
	@Test
	@Order(3)
	//this test method to test retrieving all medicalCenters
	public void testListMedicalCenters() {
	    List<MedicalCenter> medicalCenters = (List<MedicalCenter>) repository.findAll();
	    assertThat(medicalCenters).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	//first we get the medicalCenter by its email, then update its firstName. 
	//Then we get the medicalCenter again, and compare the firstName
	public void testUpdateMedicalCenter() {
	    MedicalCenter medicalCenter = repository.findById(1L).orElseThrow();
	    medicalCenter.setMedicalCenterName("Cascade Hospital"); 
	    repository.save(medicalCenter);	     
	    MedicalCenter updatedMedicalCenter = repository.findById(1L).orElseThrow();   
	    assertThat(updatedMedicalCenter.getMedicalCenterName()).isEqualTo("Cascade Hospital");
	}
	
	
	//This test method is for testing delete a medicalCenter by its Id
	@Test
	@Rollback(false)
	@Order(5)
	public void testDeleteMedicalCenter() {
	    MedicalCenter medicalCenter = repository.findById(1L).orElseThrow();
	    repository.deleteById(medicalCenter.getMedicalCenterId());	     
	    Optional<MedicalCenter> deletedMedicalCenter = repository.findById(1L);     
	    assertEquals(deletedMedicalCenter, Optional.empty());     
	}
}
