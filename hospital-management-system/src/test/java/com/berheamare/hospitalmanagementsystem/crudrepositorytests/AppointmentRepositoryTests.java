package com.berheamare.hospitalmanagementsystem.crudrepositorytests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.berheamare.hospitalmanagementsystem.models.Appointment;
import com.berheamare.hospitalmanagementsystem.repositories.AppointmentRepository;
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class AppointmentRepositoryTests {

	  @Autowired
	    private AppointmentRepository repository;
	@Test
	// @Rollback(false) to disable roll back to the data will be committed
	// to the database and available for the next test.
	@Rollback(false)
	// JUnit doesn’t run test methods in the order they appear in the code
	// So @Order  helps us to keep data for the subsequence tests.
	 @Order(1)
  public void testSaveNewAppointment() {
		
		Appointment savedAppointment =repository.save(
				new Appointment("10/19/2022", "", "Higline Hospital"));
	     
// I use assertThat() method from AssertJ library for more readability than 
//	using JUnit’s assertion methods		                           
	    assertThat(savedAppointment.getAppointmentId()).isGreaterThan(0);
		   

  }

	@Test
	@Order(2)
//	 This test method to test finding a appointment by appointment date
	public void testFindAppointmentByAppointmentDate() {
	    Appointment appointment = repository.findByAppointmentDate("10/19/2022").orElseThrow();    
	    assertThat(appointment.getAppointmentDate()).isEqualTo("10/19/2022");
	}
	
	@Test
	@Order(3)
	//this test method to test retrieving all appointments
	public void testListAppointments() {
	    List<Appointment> appointments = (List<Appointment>) repository.findAll();
	    assertThat(appointments).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	//first we get the appointment by its email, then update its firstName. 
	//Then we get the appointment again, and compare the firstName
	public void testUpdateAppointment() {
	    Appointment appointment = repository.findByAppointmentDate("10/19/2022").orElseThrow();
	    appointment.setAppointmentDate("11/25/2022"); 
	    repository.save(appointment);	     
	    Appointment updatedAppointment = repository.findByAppointmentDate("11/25/2022").orElseThrow();   
	    assertThat(updatedAppointment.getAppointmentDate()).isEqualTo("11/25/2022");
	}
	
	
	//This test method is for testing delete a appointment by its id
	@Test
	@Rollback(false)
	@Order(5)
	public void testDeleteAppointment() {
	    Appointment appointment = repository.findByAppointmentDate("11/25/2022").orElseThrow();
	    repository.deleteById(appointment.getAppointmentId());	     
	    Optional<Appointment> deletedAppointment = repository.findByAppointmentDate("11/25/2022");     
	    assertEquals(deletedAppointment, Optional.empty());     
	}
}
