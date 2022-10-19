package com.berheamare.hospitalmanagementsystem.servicetests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.berheamare.hospitalmanagementsystem.models.Token;
import com.berheamare.hospitalmanagementsystem.repositories.TokenRepository;
import com.berheamare.hospitalmanagementsystem.services.TokenService;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTests {
	
	private TokenService tokenService;
	@Mock
	private TokenRepository tokenRepository;
	
	private Token token;
	
	@BeforeEach
    public void setUp() {
        tokenRepository = Mockito.mock(TokenRepository.class);
        token= new Token();
        tokenService = new TokenService(tokenRepository);
    }
	
	@Test
	 // JUnit test for saveToken method
    public void saveTokenSeviceTest(){
        Mockito.when(tokenRepository.save(token)).thenReturn(new Token());
        assertNotNull(tokenService.saveToken(token));
}

	@Test
	// JUnit test for findByToken method
    public void findByTokenServiceTest(){
		Mockito.when(tokenRepository.findByToken("tctststs"))
		.thenReturn(Optional.of(token));
        assertNotNull(tokenService.getToken("tctststs"));
    }
}
