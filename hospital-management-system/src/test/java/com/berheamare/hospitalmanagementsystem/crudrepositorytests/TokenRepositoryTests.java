package com.berheamare.hospitalmanagementsystem.crudrepositorytests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.berheamare.hospitalmanagementsystem.models.Token;
import com.berheamare.hospitalmanagementsystem.models.User;
import com.berheamare.hospitalmanagementsystem.repositories.TokenRepository;
import com.berheamare.hospitalmanagementsystem.repositories.UserRepository;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class TokenRepositoryTests {

	  @Autowired
	    private TokenRepository repository;
	  @Autowired
	  private UserRepository userRepository;
	@Test
	// @Rollback(false) to disable roll back to the data will be committed
	// to the database and available for the next test.
	@Rollback(false)
	// JUnit doesn’t run test methods in the order they appear in the code
	// So @Order  helps us to keep data for the subsequence tests.
	 @Order(1)
  public void testSaveNewToken() {
		
		LocalDateTime now = LocalDateTime.now();
		User user =new User("Ezra", "Williams", 
                "addberhe@gmail.com", 
                "pass", "Admin");
		// user must be saved before the token is generated
		userRepository.save(user);
		Token savedToken =repository.save(
				new Token("ttttatatavvvvaccccacacac",
						now,now,
						user
						));
	     
// I use assertThat() method from AssertJ library for more readability than 
//	using JUnit’s assertion methods		                           
	    assertThat(savedToken.getId()).isGreaterThan(0);	   

  }

	@Test
	@Order(2)
//	 This test method to test finding a token by Id
	public void testFindToken() {
	    Token token = repository.findByToken("ttttatatavvvvaccccacacac").orElseThrow();    
	    assertThat(token.getToken()).isEqualTo("ttttatatavvvvaccccacacac");
	}
	
	
}
