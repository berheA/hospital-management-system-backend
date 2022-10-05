package com.berheamare.hospitalmanagementsystem.services;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berheamare.hospitalmanagementsystem.models.Token;
import com.berheamare.hospitalmanagementsystem.repositories.TokenRepository;

@Service
public class TokenService {

	@Autowired
	
	private final TokenRepository tokenRepository;
	
	public TokenService( TokenRepository tokenRepository) {
		
		this.tokenRepository=tokenRepository;
	}
	
	
	public void saveConfirmationToken(Token token) {
		tokenRepository.save(token);
		
	}
	
	
	  public Optional<Token> getToken(String token) {
	        return tokenRepository.findByToken(token);
	    }

	    public int setConfirmedAt(String token) {
	        return tokenRepository.updateConfirmedAt(
	                token, LocalDateTime.now());
	    }
}

