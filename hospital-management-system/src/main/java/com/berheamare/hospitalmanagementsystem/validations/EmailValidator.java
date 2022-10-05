package com.berheamare.hospitalmanagementsystem.validations;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {

	@Override
	public boolean test(String t) {
		// TODO Auto-generated method stub
		// regex to validate email
		return true;
	}

}
