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

import com.berheamare.hospitalmanagementsystem.models.User;
import com.berheamare.hospitalmanagementsystem.repositories.UserRepository;
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class UserRepositoryTests {

	  @Autowired
	    private UserRepository repository;
	@Test
	// @Rollback(false) to disable roll back to the data will be committed
	// to the database and available for the next test.
	@Rollback(false)
	// JUnit doesn’t run test methods in the order they appear in the code
	// So @Order  helps us to keep data for the subsequence tests.
	 @Order(1)
  public void testSaveNewUser() {
		
		User savedUser =repository.save(new User("Ezra", "Williams", 
				                           "addberhe@gmail.com", 
				                           "pass", "Admin"));;
	     
// I use assertThat() method from AssertJ library for more readability than 
//	using JUnit’s assertion methods		                           
	    assertThat(savedUser.getId()).isGreaterThan(0);
		   

  }

	@Test
	@Order(2)
//	 This test method to test finding a user by email
	public void testFindUserByEmail() {
	    User user = repository.findByEmail("addberhe@gmail.com").orElseThrow();    
	    assertThat(user.getEmail()).isEqualTo("addberhe@gmail.com");
	}
	
	@Test
	@Order(3)
	//this test method to test retrieving all users
	public void testListUsers() {
	    List<User> users = (List<User>) repository.findAll();
	    assertThat(users).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	@Order(4)
	//first we get the user by its email, then update its firstName. 
	//Then we get the user again, and compare the firstName
	public void testUpdateUser() {
	    User user = repository.findByEmail("addberhe@gmail.com").orElseThrow();
	    user.setFirstName("Bably"); 
	    repository.save(user);	     
	    User updatedUser = repository.findByEmail("addberhe@gmail.com").orElseThrow();   
	    assertThat(updatedUser.getFirstName()).isEqualTo("Bably");
	}
	
	
	//This test method is for testing delete a user by its Id
	@Test
	@Rollback(false)
	@Order(5)
	public void testDeleteUser() {
	    User user = repository.findByEmail("addberhe@gmail.com").orElseThrow();
	    repository.deleteById(user.getId());	     
	    Optional<User> deletedUser = repository.findByEmail("addberhe@gmail.com");     
	    assertEquals(deletedUser, Optional.empty());     
	}
}
