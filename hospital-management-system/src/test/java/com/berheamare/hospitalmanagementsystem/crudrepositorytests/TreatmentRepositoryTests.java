package com.berheamare.hospitalmanagementsystem.crudrepositorytests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.berheamare.hospitalmanagementsystem.models.Treatment;
import com.berheamare.hospitalmanagementsystem.repositories.TreatmentRepository;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class TreatmentRepositoryTests {

	  @Autowired
	    private TreatmentRepository repository;
	@Test
	// @Rollback(false) to disable roll back to the data will be committed
	// to the database and available for the next test.
	@Rollback(false)
	// JUnit doesn’t run test methods in the order they appear in the code
	// So @Order  helps us to keep data for the subsequence tests.
	 @Order(1)
  public void testSaveNewTreatment() {
		
		
		Treatment savedTreatment =repository.save(new Treatment(
				22L, 44L, "emails@doctors.net", "Flu", 
				"10/19/2022",
				"10/18/2022", 
				334.0));
		
	     
// I use assertThat() method from AssertJ library for more readability than 
//	using JUnit’s assertion methods		                           
	    assertThat(savedTreatment.getTreatmentId()).isGreaterThan(0);	   

  }

	@Test
	@Order(2)
//	 This test method to test finding a treatment by Id
	public void testFindTreatmentById() {
	    Treatment treatment = repository.findById(1L).orElseThrow();    
	    assertThat(treatment.getTreatmentId()).isEqualTo(1L);
	}
	
	@Test
	@Order(3)
	//this test method to test retrieving all treatments
	public void testListTreatments() {
	    List<Treatment> treatments = (List<Treatment>) repository.findAll();
	    assertThat(treatments).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	//first we get the treatment by its id, then update its treatment date. 
	//Then we get the treatment again, and compare the treatment date
	public void testUpdateTreatment() {
	    Treatment treatment = repository.findById(1L).orElseThrow();
	    treatment.setTreatmentDate("10/20/2022"); 
	    repository.save(treatment);	     
	    Treatment updatedTreatment = repository.findById(1L).orElseThrow();   
	    assertThat(updatedTreatment.getTreatmentDate()).isEqualTo("10/20/2022");
	}
	
	
	//This test method is for testing delete a treatment by its Id
	@Test
	@Rollback(false)
	@Order(5)
	public void testDeleteTreatment() {
	    Treatment treatment = repository.findById(1L).orElseThrow();
	    repository.deleteById(treatment.getTreatmentId());	     
	    Optional<Treatment> deletedTreatment = repository.findById(1L);     
	    assertEquals(deletedTreatment, Optional.empty());     
	}
}
